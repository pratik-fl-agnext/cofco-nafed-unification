package com.agnext.unification.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.agnext.unification.common.Constants;
import com.agnext.unification.config.ApplicationContext;
import com.agnext.unification.model.AcceptanceModel;
import com.agnext.unification.model.AvgScanCountModel;
import com.agnext.unification.model.HistoryMoistureWrapperModel;
import com.agnext.unification.model.LabResults;
import com.agnext.unification.model.MetaDataModel;
import com.agnext.unification.model.MoistureDataModel;
import com.agnext.unification.model.MoistureMeterResultModel;
import com.agnext.unification.model.NanoScanModel;
import com.agnext.unification.model.QualityMapModel;
import com.agnext.unification.model.QualityRules;
import com.agnext.unification.model.ScanCountModel;
import com.agnext.unification.model.ScanHistoryResponseModel;
import com.agnext.unification.model.ScanModel;
import com.agnext.unification.model.ScanResponseModel;
import com.agnext.unification.model.VarianceModel;
import com.agnext.unification.repository.cofco.CofcoScmScanRepository;
import com.agnext.unification.repository.nafed.NafedScmScanRepository;
import com.agnext.unification.service.ScanService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * scan controller
 *
 */
@RestController
@RequestMapping(value = "api/scan", produces = "application/json")
public class ScanController {
    private static Logger logger = LoggerFactory.getLogger(ScanController.class);

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    ScanService scanService;
    
    @Autowired
    ApplicationContext appContext;
    
    @Autowired
    NafedScmScanRepository nafedScanRepo;
    
    @Autowired
    CofcoScmScanRepository cofcoScanRepo;

    @PostMapping("/post-visio")
    public ResponseEntity<?> saveNanoScan(MultipartHttpServletRequest request,
	    @RequestParam(value = "data", required = true) String data,
	    @RequestParam(value = "analyses", required = false) String analyses) {

	logger.info(" NanoScanController.saveNanoScan() ");

	logger.info("Data:  " + data);
    logger.info("Post Data Analytics :  " + analyses);

	try {
	    ScanModel postData = objectMapper.readValue(data, ScanModel.class);

//	    logger.info("Post Data:  " + postData);

	    Boolean status = scanService.saveScan(postData, request.getFileNames(), request, analyses);
	    ScanResponseModel response = new ScanResponseModel();

	    if (status) {
		response.setMessage("Scan Successful");
		return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
		logger.info("Client doesn't entered Category");
		response.setMessage("Client doesn't has entered category");
		return new ResponseEntity<>(response, HttpStatus.OK);
	    }

	} catch (Exception e) {
	    logger.error(" NanoScanController.saveNanoScan() ", e.getMessage());
	    return scanService.handleException(e);
	}

    }

    @PutMapping()
    public ResponseEntity<?> updateNanoScan(MultipartHttpServletRequest request,
	    @RequestParam(value = "data", required = true) String data) {

	logger.info(" NanoScanController.updateNanoScan() ");

	logger.info("Data:  " + data);

	try {
	    ScanModel postData = objectMapper.readValue(data, ScanModel.class);

	    logger.info("Post Data:  " + postData);
	    scanService.updateScan(postData, request.getFileNames(), request);
	    ScanResponseModel response = new ScanResponseModel();
	    response.setMessage("Scan Successful");
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error(" NanoScanController.updateNanoScan() ", e.getMessage());
	    return scanService.handleException(e);
	}

    }

    @GetMapping(value = "/filter")
    public ResponseEntity<?> getScansByFilter(@RequestParam(value = "region_id", required = false) Long regionId,
	    @RequestParam(value = "commodity_id", required = false) Long commodityId,
	    @RequestParam(value = "analysis_name", required = true) String analysisName,
	    @RequestParam(value = "inst_center_id", required = false) Long ccId,
	    @RequestParam(value = "date_from", required = false) Long dateFrom,
	    @RequestParam(value = "customer_id", required = false) Long customerId,
	    @RequestParam(value = "device_serial_number", required = false) String deviceSerialNo,
	    @RequestParam(value = "device_type", required = false) String deviceType,
	    @RequestParam(value = "date_to", required = false) Long dateTo) {

	logger.info(" NanoScanController.getScansByFilter ");
	List<NanoScanModel> response;
	try {
	    response = scanService.getScansQualityByFilter(regionId, commodityId, ccId, dateFrom, dateTo, analysisName,
		    deviceType, deviceSerialNo, customerId);

	    if (response == null || response.size() == 0)
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error(" NanoScanController.getScansByFilter ", e.getMessage());
	    return scanService.handleException(e);
	}

    }

    @GetMapping(value = "/filter/quality")
    public ResponseEntity<?> getQualityByFilter(@RequestParam(value = "region_id", required = false) String regionId,
	    @RequestParam(value = "commodity_id", required = false) Long commodityId,
	    @RequestParam(value = "analysis_name", required = false) String analysisName,
	    @RequestParam(value = "inst_center_id", required = false) String ccId,
	    @RequestParam(value = "date_from", required = false) Long dateFrom,
	    @RequestParam(value = "date_to", required = false) Long dateTo) {

	logger.info(" NanoScanController.getQualityByFilter ");
	List<Double> response;
	try {
	    response = scanService.getQualityByFilter(regionId, commodityId, ccId, dateFrom, dateTo, analysisName);

	    if (response == null || response.size() == 0)
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error(" NanoScanController.getQualityByFilter ", e.getMessage());
	    return scanService.handleException(e);
	}

    }

    @GetMapping(value = "/filter/quality/overtime")
    public ResponseEntity<?> getQualityOverTime(@RequestParam(value = "region_id", required = false) Long regionId,
	    @RequestParam(value = "commodity_id", required = false) Long commodityId,
	    @RequestParam(value = "analysis_name", required = false) String analysisName,
	    @RequestParam(value = "inst_center_id", required = false) Long ccId,
	    @RequestParam(value = "date_from", required = false) Long dateFrom,
	    @RequestParam(value = "customer_id", required = false) Long customerId,
	    @RequestParam(value = "device_serial_number", required = false) String deviceSerialNo,
	    @RequestParam(value = "date_to", required = false) Long dateTo) {

	logger.info(" NanoScanController.getQualityOverTime ");
	List<ScanCountModel> response;
	try {
	    response = scanService.getQualityOverTime(regionId, commodityId, ccId, dateFrom, dateTo, analysisName,
		    deviceSerialNo, customerId);

	    if (response == null || response.size() == 0)
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error(" NanoScanController.getQualityOverTime ", e.getMessage());
	    return scanService.handleException(e);
	}

    }

    @GetMapping(value = "/filter/daily/scan/scount")
    public ResponseEntity<?> getDailyScanCount(@RequestParam(value = "region_id", required = false) Long regionId,
	    @RequestParam(value = "commodity_id", required = false) Long commodityId,
	    @RequestParam(value = "analysis_name", required = false) String analysisName,
	    @RequestParam(value = "category_id", required = false) Long categoryId,
	    @RequestParam(value = "inst_center_id", required = false) Long ccId,
	    @RequestParam(value = "date_from", required = false) Long dateFrom,
	    @RequestParam(value = "device_type", required = false) String deviceType,
	    @RequestParam(value = "device_type_id", required = false) Long deviceTypeId,
	    @RequestParam(value = "customer_id", required = false) Long customerId,
	    @RequestParam(value = "device_serial_number", required = false) String deviceSerialNo,
	    @RequestParam(value = "date_to", required = false) Long dateTo,
	    @RequestParam(value = "state_id", required = false) Long stateId,
	    @RequestParam(value = "district_name", required = false) String districtName) {

	logger.info(" NanoScanController.getDailyScanCount ");
	AvgScanCountModel response;
	try {
	    logger.info(
		    "getDailyScanCount :{} , commodityId  :{}, ccId :{}, dateFrom :{}, "
			    + "dateTo:{}, analysisName:{}, customerId: {}, categoryID: {}"
			    + "deviceType:{} , deviceTypeId:{}, deviceSerialNo:{}",
		    regionId, commodityId, ccId, dateFrom, dateTo, analysisName, customerId, categoryId, deviceType,
		    deviceTypeId, deviceSerialNo);
	    response = scanService.getDailyScanCount(regionId, commodityId, ccId, dateFrom, dateTo, analysisName,
		    customerId, categoryId, deviceType, deviceTypeId, deviceSerialNo, stateId, districtName);
	    if (response == null)
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error(" NanoScanController.getDailyScanCount ", e.getMessage());
	    return scanService.handleException(e);
	}

    }
    //
    //	@GetMapping(value = "/filter/accepted-average")
    //	public ResponseEntity<?> getAcceptedAVGPerDay(@RequestParam(value = "region_id", required = false) Long regionId,
    //			@RequestParam(value = "commodity_id", required = false) Long commodityId,
    //			@RequestParam(value = "customer_id", required = false) Long customerId,
    //			@RequestParam(value = "category_id", required = false) Long categoryId,
    //			@RequestParam(value = "inst_center_id", required = false) Long installationCenterId,
    //			@RequestParam(value = "device_type", required = false) String deviceType,
    //			@RequestParam(value = "device_type_id", required = false) Long deviceTypeId,
    //			@RequestParam(value = "device_serial_number", required = false) String deviceSerialNo,
    //			@RequestParam(value = "date_from", required = false) Long dateFrom,
    //			@RequestParam(value = "date_to", required = false) Long dateTo) {
    //		logger.info(" NanoScanController.getAcceptedAVGPerDay ");
    //		AcceptanceModel response;
    //		try {
    //			response = scanService.getAcceptedAVGPerDay(regionId, commodityId, dateFrom, dateTo, installationCenterId,
    //					customerId, categoryId, deviceType, deviceTypeId, deviceSerialNo);
    //
    //			if (response == null)
    //				return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    //
    //			return new ResponseEntity<>(response, HttpStatus.OK);
    //		} catch (Exception e) {
    //			logger.error(" NanoScanController.getAcceptedAVGPerDay ", e.getMessage());
    //			return scanService.handleException(e);
    //		}
    //	}

    @GetMapping(value = "/filter/accepted-average")
    public ResponseEntity<?> getAcceptedAVGPerDay(@RequestParam(value = "region_id", required = false) Long regionId,
	    @RequestParam(value = "commodity_id", required = false) Long commodityId,
	    @RequestParam(value = "customer_id", required = false) Long customerId,
	    @RequestParam(value = "category_id", required = false) Long categoryId,
	    @RequestParam(value = "inst_center_id", required = false) Long installationCenterId,
	    @RequestParam(value = "device_type", required = false) String deviceType,
	    @RequestParam(value = "device_type_id", required = false) Long deviceTypeId,
	    @RequestParam(value = "device_serial_number", required = false) String deviceSerialNo,
	    @RequestParam(value = "date_from", required = false) Long dateFrom,
	    @RequestParam(value = "date_to", required = false) Long dateTo,
	    @RequestParam(value = "state_id", required = false) Long stateId,
	    @RequestParam(value = "district_name", required = false) String districtName) {
	logger.info(" NanoScanController.getAcceptedAVGPerDay ");
	AcceptanceModel response;
	try {
	    response = scanService.getAcceptedAVGPerDay(regionId, commodityId, dateFrom, dateTo, installationCenterId,
		    customerId, categoryId, deviceType, deviceTypeId, deviceSerialNo, stateId, districtName);

	    if (response == null)
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error(" NanoScanController.getAcceptedAVGPerDay ", e.getMessage());
	    return scanService.handleException(e);
	}
    }

    @GetMapping(value = "/filter/variance-average")
    public ResponseEntity<?> getVarianceAVGPerDay(@RequestParam(value = "region_id", required = false) Long regionId,
	    @RequestParam(value = "commodity_id", required = false) Long commodityId,
	    @RequestParam(value = "category_id", required = false) Long categoryId,
	    @RequestParam(value = "inst_center_id", required = false) Long installationCenterId,
	    @RequestParam(value = "customer_id", required = false) Long customerId,
	    @RequestParam(value = "device_type", required = false) String deviceType,
	    @RequestParam(value = "device_type_id", required = false) Long deviceTypeId,
	    @RequestParam(value = "device_serial_number", required = false) String deviceSerialNo,
	    @RequestParam(value = "date_from", required = false) Long dateFrom,
	    @RequestParam(value = "date_to", required = false) Long dateTo,
	    @RequestParam(value = "state_id", required = false) Long stateId,
	    @RequestParam(value = "district_name", required = false) String districtName) {
	logger.info(" NanoScanController.getVarianceAVGPerDay ");
	VarianceModel response;
	try {
	    response = scanService.getVarianceAVGPerDay(regionId, commodityId, dateFrom, dateTo, installationCenterId,
		    customerId, categoryId, deviceType, deviceTypeId, deviceSerialNo, stateId, districtName);

	    if (response == null)
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error(" NanoScanController.getVarianceAVGPerDay ", e.getMessage());
	    return scanService.handleException(e);
	}
    }

    @GetMapping("/history")
    public ResponseEntity<?> getScanHistory(@RequestParam(value = "p", required = false) Integer pageNumber,
	    @RequestParam(value = "l", required = false) Integer limit,
	    @RequestParam(value = "customer_id", required = false) Long customerId,
	    @RequestParam(value = "commodity_id", required = false) Long commodityId,
	    @RequestParam(value = "inst_center_id", required = false) Long ccId,
	    @RequestParam(value = "date_from", required = false) Long dateFrom,
	    @RequestParam(value = "date_to", required = false) Long dateTo,
	    @RequestParam(value = "farmer_id", required = false) Long farmerId,
	    @RequestParam(value = "device_type", required = false) String deviceType,
	    @RequestParam(value = "device_type_id", required = false) Long deviceTypeId,
	    @RequestParam(value = "device_serial_number", required = false) String deviceSerialNumber,
	    @RequestParam(value = "user_type", required = false) String userType,
	    @RequestParam(value = "commodity_category_id", required = false) Long commCategoryId,
	    @RequestParam(value = "sort_by", required = false) String sortBy,
	    @RequestParam(value = "sort_type", required = false) String sortType,
	    @RequestParam(value = "state_id", required = false) Long stateId) {

	logger.info("ScanController.getScanHistory ");

	ScanHistoryResponseModel response;
	try {
	    String urlId = Constants.URL.getIdUsingUrl(appContext.getRequestContext().getRequestURL());

	    
	    response = scanService.getScanHistoryTest(pageNumber, limit, customerId, commodityId, ccId, dateFrom,
		    dateTo, farmerId, deviceType, deviceTypeId, deviceSerialNumber, userType, commCategoryId, sortBy,
		    sortType, stateId, nafedScanRepo);

	    if (response == null)
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("ScanController.getScanHistory exception is ", e.getMessage());
	    return scanService.handleException(e);
	}
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getScanDetailByScanId(@RequestParam("scan_id") Long scanId) {

	logger.info("ScanController.getScanDetailByScanId ");

	ScanModel response;
	try {
	    response = scanService.getScanDetailByScanId(scanId);
	    if (response == null)
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("ScanController.getScanDetailByScanId exception is ", e.getMessage());

	    return scanService.handleException(e);
	}

    }

    @GetMapping("/metadata")
    public ResponseEntity<?> getScanMetaDataByScanId(@RequestParam("scan_id") Long scanId) {

	logger.info("ScanController.getScanMetaDataByScanId ");

	MetaDataModel response;
	try {
	    response = scanService.getScanMetaDataByScanId(scanId, nafedScanRepo);
	    if (response == null) {
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("ScanController.getScanDetailByScanId exception is ", e.getMessage());

	    return scanService.handleException(e);
	}

    }

    @GetMapping(value = "/filter/quality/grade")
    public ResponseEntity<?> getQualityGrade(@RequestParam(value = "region_id", required = false) String regionId,
	    @RequestParam(value = "commodity_id", required = false) Long commodityId,
	    @RequestParam(value = "analysis_name", required = false) String analysisName,
	    @RequestParam(value = "inst_center_id", required = false) String ccId,
	    @RequestParam(value = "date_from", required = false) Long dateFrom,
	    @RequestParam(value = "date_to", required = false) Long dateTo) {

	logger.info(" NanoScanController.getQualityGrade ");
	List<QualityRules> response;
	try {
	    response = scanService.getQualityGrade(regionId, commodityId, ccId, dateFrom, dateTo, analysisName);

	    if (response == null || response.size() == 0)
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error(" NanoScanController.getQualityGrade ", e.getMessage());
	    return scanService.handleException(e);
	}

    }

    @PutMapping(value = "/approve")
    public ResponseEntity<?> approveScan(@RequestBody ScanModel scanModel) {

	logger.info(" ScanController.approveScan " + scanModel);

	try {
	    scanService.approveScan(scanModel);
	    return new ResponseEntity<>(HttpStatus.OK);
	} catch (Exception e) {
	    logger.error(" NanoScanController.approveScan ", e.getMessage());
	    return scanService.handleException(e);
	}

    }

    @GetMapping(value = "/filter/quality-map")
    public ResponseEntity<?> getQualityMapByFilter(@RequestParam(value = "region_id", required = false) String regionId,
	    @RequestParam(value = "commodity_id", required = false) Long commodityId,
	    @RequestParam(value = "analysis_name", required = false) String analysisName,
	    @RequestParam(value = "inst_center_id", required = false) String ccId,
	    @RequestParam(value = "date_from", required = false) Long dateFrom,
	    @RequestParam(value = "date_to", required = false) Long dateTo) {

	logger.info(" NanoScanController.getQualityMapByFilter ");
	List<QualityMapModel> response;
	try {
	    response = scanService.getQualityMapByFilter(commodityId, dateFrom, dateTo, analysisName);

	    if (response == null || response.size() == 0)
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error(" NanoScanController.getQualityMapByFilter ", e.getMessage());
	    return scanService.handleException(e);
	}

    }

    @PutMapping(value = "/lab/{scan_id}")
    public ResponseEntity<?> saveLabScanResults(@PathVariable("scan_id") Long scanId,
	    @RequestParam(value = "analytics", required = false) String analytics) {

	logger.info(" NanoScanController.saveLabScanResults() ");

	try {
	    Boolean status = scanService.saveLabScanResults(analytics, scanId);
	    ScanResponseModel response = new ScanResponseModel();

	    if (status) {
		response.setMessage("Lab Results Stored successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
		logger.info("Client doesn't entered Category");
		response.setMessage("Client doesn't has entered category");
		return new ResponseEntity<>(response, HttpStatus.OK);
	    }

	} catch (Exception e) {
	    logger.error(" NanoScanController.saveLabScanResults() ", e.getMessage());
	    return scanService.handleException(e);
	}

    }

    @GetMapping(value = "/lab/results")
    public ResponseEntity<?> getLabDetails(@RequestParam(value = "p", required = false) Integer pageNumber,
	    @RequestParam(value = "l", required = false) Integer limit,
	    @RequestParam(value = "device_serial_number", required = false) String deviceSerialNumber,
	    @RequestParam(value = "commodity_id", required = false) Long commodityId) {

	logger.info("ScanController.getLabDetails ");

	LabResults response;
	try {
	    response = scanService.getLabDetails(deviceSerialNumber, commodityId, pageNumber, limit);
	    if (response == null)
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("ScanController.getLabDetails exception is ", e.getMessage());

	    return scanService.handleException(e);
	}

    }

    @PostMapping(value = "/moisture-meter")
    public ResponseEntity<?> saveMoistureMeterResult(@RequestBody MoistureMeterResultModel moistureMeterResultModel) {
	logger.info(" ScanController.saveMoistureMeterResult ");
	try {
	    scanService.saveMoistureMeterResult(moistureMeterResultModel);
	    return new ResponseEntity<>(HttpStatus.CREATED);
	} catch (Exception e) {
	    logger.error(" ScanController.saveMoistureMeterResult ", e.getMessage());
	    return scanService.handleException(e);
	}
    }

    @GetMapping(value = "/moisture-by-operator")
    public ResponseEntity<?> getMoistureByOperatorId(@RequestParam(value = "p", required = false) Integer pageNumber,
	    @RequestParam(value = "l", required = false) Integer limit,
	    @RequestParam(value = "operator_id", required = false) Long operatorId) {

	logger.info("ScanController.getMoistureByOperatorId ");

	MoistureDataModel response;
	try {
	    response = scanService.getMoistureByOperatorId(operatorId, pageNumber, limit);
	    if (response == null)
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("ScanController.getMoistureByOperatorId exception is ", e.getMessage());

	    return scanService.handleException(e);
	}

    }

    @GetMapping("/history-moisture")
    public ResponseEntity<?> getScanHistoryAndMoisture(@RequestParam(value = "p", required = false) Integer pageNumber,
	    @RequestParam(value = "l", required = false) Integer limit,
	    @RequestParam(value = "customer_id", required = false) Long customerId,
	    @RequestParam(value = "commodity_id", required = false) Long commodityId,
	    @RequestParam(value = "inst_center_id", required = false) Long ccId,
	    @RequestParam(value = "date_from", required = false) Long dateFrom,
	    @RequestParam(value = "date_to", required = false) Long dateTo,
	    @RequestParam(value = "farmer_id", required = false) Long farmerId,
	    @RequestParam(value = "device_type", required = false) String deviceType,
	    @RequestParam(value = "device_type_id", required = false) Long deviceTypeId,
	    @RequestParam(value = "device_serial_number", required = false) String deviceSerialNumber,
	    @RequestParam(value = "user_type", required = false) String userType,
	    @RequestParam(value = "commodity_category_id", required = false) Long commCategoryId) {
	logger.info("ScanController.getScanHistoryAndMoisture ");
	try {
	    HistoryMoistureWrapperModel response = scanService.getScanHistoryAndMoisture(pageNumber, limit, customerId,
		    commodityId, ccId, dateFrom, dateTo, farmerId, deviceType, deviceTypeId, deviceSerialNumber,
		    userType, commCategoryId);

	    if (response == null)
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("ScanController.getScanHistoryAndMoisture exception is ", e.getMessage());
	    return scanService.handleException(e);
	}
    }
    
    @GetMapping("/duplicate")
    public ResponseEntity<?> checkDuplicateSampleId(@RequestParam(value = "sample_id") String sampleId,
	    @RequestParam(value = "commodity_id") Long commodityId) {
	logger.debug("ScanController.checkDuplicateSampleId with sampleId: {} and commodityID: {}", sampleId,commodityId);
	try {
	     Boolean response = scanService.isDuplicateSampleId(sampleId, commodityId);
	     	     
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("ScanController.checkDuplicateSampleId exception is ", e.getMessage());
	    return scanService.handleException(e);
	}
    }
    
    @PutMapping("/image-status-update")
    public ResponseEntity<?> updateImageStatus(@RequestParam(value = "sample_id") String sampleId,
	    @RequestParam(value = "image_unique_id") String imageUniqueId) {
	logger.debug("ScanController.updateImageStatus with sampleId: {} and imageUniqueId: {}", sampleId,imageUniqueId);
	try {
	     Boolean response = scanService.updateImageStatus(sampleId, imageUniqueId);
	     	     
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("ScanController.updateImageStatus exception is ", e.getMessage());
	    return scanService.handleException(e);
	}
    }
}
