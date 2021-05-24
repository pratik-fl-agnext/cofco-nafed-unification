package com.agnext.unification.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.agnext.unification.assembler.EntityToVOAssembler;
import com.agnext.unification.assembler.VOToEntityAssembler;
import com.agnext.unification.common.CommodityAnalyticsMap;
import com.agnext.unification.common.Constants;
import com.agnext.unification.communication.Notifications;
import com.agnext.unification.communication.RestTemplateCall;
import com.agnext.unification.config.AnalyticsVariations;
import com.agnext.unification.config.PropertiesAccessModel;
import com.agnext.unification.config.RequestContext;
import com.agnext.unification.entity.nafed.CityEntity;
import com.agnext.unification.entity.nafed.CommodityVarietyEntity;
import com.agnext.unification.entity.nafed.CountryEntity;
import com.agnext.unification.entity.nafed.CustomerAddressEntity;
import com.agnext.unification.entity.nafed.CustomerEntity;
import com.agnext.unification.entity.nafed.DcmCommodity;
import com.agnext.unification.entity.nafed.DcmDevice;
import com.agnext.unification.entity.nafed.MoistureMeterResult;
import com.agnext.unification.entity.nafed.PlotEntity;
import com.agnext.unification.entity.nafed.QualityGradeRules;
import com.agnext.unification.entity.nafed.ScanEntity;
import com.agnext.unification.entity.nafed.ScanLocation;
import com.agnext.unification.entity.nafed.ScanResultEntity;
import com.agnext.unification.entity.nafed.StateEntity;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.AcceptanceModel;
import com.agnext.unification.model.AnalysisResultVO;
import com.agnext.unification.model.Analytics;
import com.agnext.unification.model.AvgScanCountModel;
import com.agnext.unification.model.CommodityVarianceModel;
import com.agnext.unification.model.CordinateModel;
import com.agnext.unification.model.DateComparator;
import com.agnext.unification.model.HistoryMoistureWrapperModel;
import com.agnext.unification.model.ImageModel;
import com.agnext.unification.model.LabResultModelNewModel;
import com.agnext.unification.model.LabResults;
import com.agnext.unification.model.MetaDataModel;
import com.agnext.unification.model.MoistureDataModel;
import com.agnext.unification.model.MoistureMeterResultModel;
import com.agnext.unification.model.NanoScanModel;
import com.agnext.unification.model.PhysicalScanModel;
import com.agnext.unification.model.PlotModel;
import com.agnext.unification.model.QualityMapModel;
import com.agnext.unification.model.QualityRules;
import com.agnext.unification.model.ScanCountModel;
import com.agnext.unification.model.ScanHistoryResponseModel;
import com.agnext.unification.model.ScanModel;
import com.agnext.unification.model.ScanResultModel;
import com.agnext.unification.model.ScansModel;
import com.agnext.unification.model.VarianceModel;
import com.agnext.unification.model.WeightConverterModel;
import com.agnext.unification.repository.nafed.CityRepository;
import com.agnext.unification.repository.nafed.CommodityVarietyRepository;
import com.agnext.unification.repository.nafed.CountryRepository;
import com.agnext.unification.repository.nafed.CustomerAddressRepository;
import com.agnext.unification.repository.nafed.CustomerRepository;
import com.agnext.unification.repository.nafed.DcmCommodityRepository;
import com.agnext.unification.repository.nafed.DeviceRepository;
import com.agnext.unification.repository.nafed.FilterRepository;
import com.agnext.unification.repository.nafed.MoistureMeterResultRepository;
import com.agnext.unification.repository.nafed.PlotRepository;
import com.agnext.unification.repository.nafed.QualityRulesRepository;
import com.agnext.unification.repository.nafed.ScanLocationRepository;
import com.agnext.unification.repository.nafed.ScanResultEntityRepository;
import com.agnext.unification.repository.nafed.ScmScanRepository;
import com.agnext.unification.repository.nafed.StateManagerOperatorRepository;
import com.agnext.unification.repository.nafed.StateRepository;
import com.agnext.unification.repository.nafed.StatusRepository;
import com.agnext.unification.repository.nafed.UserRepository;
import com.agnext.unification.utility.GeneratePhysicalScanPdf;
import com.agnext.unification.utility.Utility;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import com.itextpdf.html2pdf.HtmlConverter;
//import com.itextpdf.html2pdf.HtmlConverter;

@Service
@Transactional
public class ScanService extends GenericService {

    private static Logger logger = LoggerFactory.getLogger(ScanService.class);

    @Value("${module-builder.aiCustomer}")
    private String customerAdminEmail;

    @Autowired
    ScmScanRepository scanRepo;

    @Autowired
    DcmCommodityRepository commodityRepository;

    @Autowired
    DeviceRepository dcmDeviceRepo;

    @Autowired
    ScanLocationRepository scanLocationRepo;

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    FilterRepository filterRepo;

    @Autowired
    QualityRulesRepository qualityRulesRepo;

    @Autowired
    RequestContext requestContext;

    @Autowired
    Notifications notify;

    @Autowired
    UserRepository userRepo;

    @Autowired
    RestTemplateCall restTemplateCalls;

    @Autowired
    StatusRepository statusRepo;

    @Autowired
    PlotRepository plotRepo;

    @Autowired
    CityRepository cityRepo;

    @Autowired
    StateRepository stateRepo;

    @Autowired
    CountryRepository countryRepo;

    @Autowired
    CustomerAddressRepository customerAddressRepo;

    @Autowired
    PropertiesAccessModel properties;

    @Autowired
    CustomerRepository custRepo;

    @Autowired
    ScanResultEntityRepository resultRepo;

    @Autowired
    MoistureMeterResultRepository moistureMeterRepo;

    @Autowired
    CommodityVarietyRepository varietyRepo;

    @Autowired
    StateManagerOperatorRepository smoRepo;

    @Autowired
    MoistureHistoryResponse moistureHistoryResponse;
    
    @Autowired
    CommodityAnalyticsMap commodityAnalyticsMap;

    @Autowired
	AnalyticsVariations analyticsVariations;

    public Long setOperatorId() {
	Long operatorId = null;
	logger.info(" Inside setUserId method ");
	System.out.println(" Request Context : " + applicationContext.getRequestContext());

	Set<String> roles = applicationContext.getRequestContext().getRoles();
	if (roles.iterator().next().equalsIgnoreCase(Constants.CustomerType.OPERATOR)) {
	    operatorId = applicationContext.getRequestContext().getUserId();
	}
	return operatorId;
    }

    public ScanLocation saveLocation(String locationName, String whouse, Long stateId) {

	ScanLocation location = new ScanLocation();
	location.setLocationName(locationName);
	if (whouse != null && whouse != "") {
	    location.setWarehouseName(whouse);
	} else {
	    location.setWarehouseName(locationName);
	}

	location.setStatus(statusRepo.getOne(Long.valueOf(8)));
	StateEntity state = stateRepo.getOne(stateId);
	location.setState(state);
	ScanLocation res = scanLocationRepo.save(location);
	return res;
    }

    public void updateScan(ScanModel postData, Iterator<String> fileNames, MultipartHttpServletRequest request)
	    throws IMException {

	ScanEntity scn = scanRepo.getOne(postData.getId());
	if (scn == null) {
	    throw new IMException(Constants.ErrorCode.NO_RECORD_FORUND, Constants.ErrorMessage.NO_RECORD_FORUND);
	}
	//scn.setBatchId(postData.getBatchId());
	// scn.setInstCenterTypeId(postData.getInstCenterTypeId());
	// scn.setCommodityCategoryId(postData.getCommodityCategoryId());
	// scn.setCommodityName(postData.getCommodityName());
	// scn.setCommodityVarietyId(postData.getCommodityVarietyId());
	// scn.setCustomerId(customerId);
	// scn.setRegionId(postData.getRegionId());
	// scn.setDeviceSerialNo(postData.getDeviceSerialNo());
	// scn.setCreatedOn(Long.valueOf(Instant.now().getEpochSecond() + "000"));
	Optional.ofNullable(postData.getLotId()).ifPresent(scn::setLotId);
	// Optional.ofNullable(postData.getCommodityId()).ifPresent(scn::setCommodityId);
	// Optional.ofNullable(postData.getDeviceTypeId()).ifPresent(scn::setDeviceTypeId);
	// Optional.ofNullable(postData.getDeviceType()).ifPresent(scn::setDeviceType);
	// Optional.ofNullable(postData.getInstCenterId()).ifPresent(scn::setInstallatonCenterId);
	// Optional.ofNullable(postData.getTotalCount()).ifPresent(scn::setTotalCount);
	// Optional.ofNullable(postData.getAssistId()).ifPresent(scn::setAssistId);
	// Optional.ofNullable(postData.getSectionId()).ifPresent(scn::setSectionId);
	// Optional.ofNullable(postData.getSessionId()).ifPresent(scn::setSeasonId);
	// Optional.ofNullable(postData.getAreaCovered()).ifPresent(scn::setAreaCovered);
	// Optional.ofNullable(postData.getTotalWeight()).ifPresent(scn::setTotalWeight);
	WeightConverterModel weightDetails = new WeightConverterModel();
	weightDetails = Utility.postWeightConverter(postData.getWeight(), postData.getQuantityUnit());
	logger.debug("********* Weight : " + weightDetails.getWeight() + " Weight unit : " + weightDetails.getUnit());
	scn.setWeight(weightDetails.getWeight());
	scn.setQuantityUnit(weightDetails.getUnit());
	Optional.ofNullable(postData.getFarmerId()).ifPresent(scn::setFarmerId);

	// Optional.ofNullable(postData.getAgentcode()).ifPresent(scn::setAgentcode);
	// Optional.ofNullable(postData.getQualityScore()).ifPresent(scn::setQualityScore);
	Optional.ofNullable(postData.getVendorCode()).ifPresent(scn::setVendorCode);
	Optional.ofNullable(postData.getQuantity()).ifPresent(scn::setQuantity);
	//		Optional.ofNullable(postData.getQuantityUnit()).ifPresent(scn::setQuantityUnit);
	Optional.ofNullable(postData.getFarmerCode()).ifPresent(scn::setFarmerCode);
	Optional.ofNullable(postData.getSampleId()).ifPresent(scn::setSampleId);
	// Optional.ofNullable(postData.getVarietyId()).ifPresent(scn::setVarietyId);
	Optional.ofNullable(postData.getAcceptedBags()).ifPresent(scn::setAcceptedBags);
	Optional.ofNullable(postData.getRejectedBags()).ifPresent(scn::setRejectedBags);
	scanRepo.save(scn);
    }

    public Boolean saveScan(ScanModel postData, Iterator<String> fileNames, MultipartHttpServletRequest request,
	    String analyses) throws IMException, JsonProcessingException {

	String name = postData.getCustomerName();
	if (name != null && postData.getCustomerName().equals("AryaAIModel")) {
	    return saveAIModelData(postData, analyses);
	}

	else {
	    String token = null;
	    Long clientId = null;
	    Long adminId = null;
	    Long operatorId = null;
	    ScanEntity scn = null;
	    Long stateAdminId = null;
	    DcmCommodity commodityId=null;
	    Long userId = requestContext.getUserId();
	    if(postData.getCommodityName()!=null && !postData.getCommodityName().trim().equalsIgnoreCase("") ) {
	     commodityId = commodityRepository.getCommodityName(postData.getCommodityName().trim());
	    }else {
	    	throw new IMException("COM-001", "Commodity not provided ");
	    }
	    
	    stateAdminId = smoRepo.findStateManagerIdByOperatorId(requestContext.getUserId());

	    if (postData.getSampleId() != null && !postData.getSampleId().isEmpty() && commodityId !=null) {
	    	//TODO : Change the single object to list for avoiding the query generate multiple results issue
		scn = scanRepo.findBySampleIdAndCommodityIdAndIsValid(postData.getSampleId(),commodityId.getId(),Boolean.TRUE);
		if (scn == null) {
		    scn = new ScanEntity();
		} else {

		    if (scn.getIsValid() == Boolean.TRUE) {
			throw new IMException(Constants.ErrorCode.SAMPLE_ID_EXISTS,
				Constants.ErrorMessage.SAMPLE_ID_EXISTS);
		    } else {
			List<ScanResultEntity> scanResultEntities = resultRepo.findByScanEntityId(scn.getId());
			if (!scanResultEntities.isEmpty()) {
			    resultRepo.deleteAll(scanResultEntities);
			}
		    }
		}
	    }
	    // New Parameters added
	    scn.setDeviceModelNo(postData.getDeviceModelNo());
	    scn.setModeOfScan(postData.getModeOfScan());

	    Optional.ofNullable(postData.getBatchId()).ifPresent(scn::setBatchId);
	    // UUID scanId = UUID.randomUUID();
	    scn.setIsValid(Boolean.TRUE);
	    Optional.ofNullable(postData.getInstCenterTypeId()).ifPresent(scn::setInstCenterTypeId);
	    if (postData.getOperatorId() != null) {
		scn.setOperatorId(postData.getOperatorId());
		if(postData.getOperatorId() !=requestContext.getUserId()) {
			logger.debug("*** Operator id in scan data and request Mismateched, Request operator Id : "+requestContext.getUserId()+" while Post request operator's id is : "+postData.getOperatorId());
		}

	    } else {

		scn.setOperatorId(userId);
	    }

	    // Image changes
	    Optional.ofNullable(postData.getImageUniqueId()).ifPresent(scn::setImageUniqueId);
	    if(postData.getIsImageUrlValid() !=null) {
	    scn.setIsImageUrlValid(postData.getIsImageUrlValid());
	    }else {
	    	scn.setIsImageUrlValid(Boolean.FALSE);
	    }


	    // Long customerId = serverContext.getRequestContext().getCustomerId();
	  
	    scn.setCommodityId(commodityId.getId());
	    scn.setCommodityCategoryId(commodityId.getDcmCommodityCategory().getId());
	    DcmDevice device= null;
	    if(postData.getDeviceSerialNo() !=null && !postData.getDeviceSerialNo().isEmpty()) {
			device = dcmDeviceRepo.findBySerialNumber(postData.getDeviceSerialNo());
	    }else {
	    	throw new IMException("DIVNOTFOUND001", " Plz provide device serial number in the request data ");
	    }
			if (device == null) {
				throw new IMException("DIVNOTFOUND002", "Device details could not be extracted by device serial number "+postData.getDeviceSerialNo());
			}
			if(device.getUserId() !=userId) {
				logger.debug(" **************************************");
				logger.debug(" *** User from token and scanned device Mismatched for model : "+postData);
			}

	    scn.setCommodityName(commodityId.getCommodityName());
	    scn.setCommodityVarietyId(postData.getCommodityVarietyId());
	    // scn.setRegionId(device.getDcmCommercialLocation().getDcmRegion().getId());
	    ScanLocation location=null;
	    //Code commented where location is being extarcted from user id in token
//	    if (device !=null && device.getScanLocation() != null && device.getScanLocation().getId() !=null) {
//		 location = scanLocationRepo.getOne(device.getScanLocation().getId());
//	    }else if(device ==null ) {
//
//	    }
//	    if(postData.getWarehouseName() !=null && !postData.getWarehouseName().isEmpty()) {
//	    location= scanLocationRepo.findByLocationName(postData.getWarehouseName().trim());
//	    }
//		if (location != null) {
//		    scn.setInstallatonCenterId(location.getId());
//		} else if (location == null) {

		    ScanLocation locationNotSent = scanLocationRepo.findByIdAndStatusStatusId(
			    device.getScanLocation().getId(), Constants.STATUS.ACTIVE.getId());

			logger.debug(" **************************************");
		    logger.debug(" *** Location is extracted from device here :  '"+locationNotSent+"'" );
		    if(locationNotSent !=null && !locationNotSent.getLocationName().equalsIgnoreCase(postData.getWarehouseName())) {
		    	 logger.debug(" *** Location in post request and sent device location mismatched, Post request warehouse name :'"+postData.getWarehouseName()+"' and device's warehouse name : '"+locationNotSent.getLocationName()+"'" );
		    }
		    if (locationNotSent != null) {
			scn.setInstallatonCenterId(locationNotSent.getId());
		    }
//	    if (postData.getWarehouseName() != null) {
//		ScanLocation location = scanLocationRepo.findByLocationName(postData.getWarehouseName().trim());
//		if (location != null) {
//		    scn.setInstallatonCenterId(location.getId());
//		} else if (location == null) {
//		    ScanLocation locationNotSent = scanLocationRepo.findByIdAndStatusStatusId(
//			    device.getScanLocation().getId(), Constants.STATUS.ACTIVE.getId());
//		    if (locationNotSent != null) {
//			scn.setInstallatonCenterId(locationNotSent.getId());
//		    }
//		}
//
//		//		else {
//		//		    ScanLocation loc = saveLocation(postData.getLocation().trim(), postData.getwHouse(),
//		//			    postData.getStateId());
//		//		    scn.setInstallatonCenterId(loc.getId());
//		//		}
//	    } else if (postData.getWarehouseName() == null || postData.getWarehouseName().equals("")) {
//		ScanLocation location = scanLocationRepo.findByIdAndStatusStatusId(device.getScanLocation().getId(),
//			Constants.STATUS.ACTIVE.getId());
//		if (location != null) {
//		    scn.setInstallatonCenterId(location.getId());
//		}
//	    }
	    CustomerEntity customer = customerRepo.findByCustomerName(postData.getCustomerName());
	    if (customer != null && customer.getCustomerId() != null) {
		scn.setCustomerId(customer.getCustomerId());
	    } else {
		System.out.println(" request   Contex : " + requestContext);
		System.out.println(" ********* " + requestContext.getCustomerId());
		scn.setCustomerId(requestContext.getCustomerId());
	    }
	    scn.setDeviceSerialNo(postData.getDeviceSerialNo());
			if (postData.getCreatedOn() != null) {
				logger.info(
						"Posting created_on and created_on_date value from scan request :" + postData.getCreatedOn());
				scn.setCreatedOn(postData.getCreatedOn());
				scn.setCreatedOnDate(Utility.getCurrentDate(postData.getCreatedOn()));
			} else {

				scn.setCreatedOn(Long.valueOf(Instant.now().getEpochSecond() + "000"));
				Optional.ofNullable(Utility.getCurrentDate()).ifPresent(scn::setCreatedOnDate);
				logger.info("Posting created_on and created_on_date value default value  :" + scn.getCreatedOn() + " & "
						+ scn.getCreatedOnDate());
			}
	    if (postData.getApproval() != null) {
		scn.setApproval(postData.getApproval());
	    } else if (postData.getApproval() == null) {
		scn.setApproval(0);
	    }

	    Optional.ofNullable(postData.getSocietyName()).ifPresent(scn::setSocietyName);
	    Optional.ofNullable(postData.getLotId()).ifPresent(scn::setLotId);
	    Optional.ofNullable(postData.getWeighbridgeName()).ifPresent(scn::setWeighbridgeName);
	    Optional.ofNullable(postData.getGRNNumber()).ifPresent(scn::setGRNNumber);
	    Optional.ofNullable(postData.getCropYear()).ifPresent(scn::setCropYear);
	    logger.debug("Logger Debug for getDCMDeviceType:{}", device);
	    if (device.getDcmDeviceType() != null) {
		logger.debug("Logger Debug for getDCMDeviceType, device ID:{}", device.getDcmDeviceType().getId());
		Optional.ofNullable(device.getDcmDeviceType().getId()).ifPresent(scn::setDeviceTypeId);
		logger.debug("Logger Debug for getDCMDeviceType:{}, device Type Description ",
			device.getDcmDeviceType().getDeviceTypeDesc());
		Optional.ofNullable(device.getDcmDeviceType().getDeviceTypeDesc()).ifPresent(scn::setDeviceType);
	    }
	    scn.setPlotId(20L);
	    WeightConverterModel weightDetails = new WeightConverterModel();
	    weightDetails = Utility.postWeightConverter(postData.getWeight(), postData.getQuantityUnit());
	    System.out.println(
		    "********* Weight : " + weightDetails.getWeight() + " Weight unit : " + weightDetails.getUnit());
	    scn.setSampleWeight(weightDetails.getWeight());
	    scn.setSampleWeightUnit(weightDetails.getUnit());

	    scn.setWeight(postData.getQuintalQuantity());
	    scn.setQuantityUnit("Quintal");

	    //		if (postData.getQuantityUnit() != null && !postData.getQuantityUnit().isEmpty()) {
	    //			scn.setQuantityUnit(postData.getQuantityUnit());
	    //			if (postData.getQuantityUnit().equalsIgnoreCase("kg")) {
	    //				Optional.ofNullable(postData.getQuantity() / 1000).ifPresent(scn::setQuantity);
	    //			} else {
	    //				Optional.ofNullable(postData.getQuantity()).ifPresent(scn::setQuantity);
	    //			}
	    //		} else {
	    //			Optional.ofNullable(postData.getQuantity()).ifPresent(scn::setQuantity);
	    //			scn.setQuantityUnit("Quintals");
	    //		}
	    Optional.ofNullable("A").ifPresent(scn::setGrade);
	    Optional.ofNullable(1).ifPresent(scn::setAccepted);
	    Optional.ofNullable(Long.valueOf(10)).ifPresent(scn::setVariance);
//	    Optional.ofNullable(Utility.getCurrentDate()).ifPresent(scn::setCreatedOnDate);
//	    Optional.ofNullable(Long.valueOf(Instant.now().getEpochSecond() + "000")).ifPresent(scn::setCreatedOn);

	    Optional.ofNullable(postData.getWarehouseName()).ifPresent(scn::setLocation);
	    Optional.ofNullable(postData.getTruckGrossWeight()).ifPresent(scn::setTruckGrossWeight);
	    Optional.ofNullable(postData.getTruckNumber()).ifPresent(scn::setTruckNumber);
	    Optional.ofNullable(postData.getTruckNetWeight()).ifPresent(scn::setTruckNetWeight);
	    Optional.ofNullable(postData.getTruckTareWeight()).ifPresent(scn::setTruckTareWeight);
	    Optional.ofNullable(postData.getBag()).ifPresent(scn::setBag);
	    Optional.ofNullable(postData.getCadNo()).ifPresent(scn::setCadNo);
	    Optional.ofNullable(postData.getGatePass()).ifPresent(scn::setGatePass);
	    Optional.ofNullable(postData.getSlipNo()).ifPresent(scn::setSlipNo);
	    Optional.ofNullable(postData.getChamberNumber()).ifPresent(scn::setChamberNumber);
	    Optional.ofNullable(postData.getPackingSize()).ifPresent(scn::setPackingSize);

	    Optional.ofNullable(postData.getAvgWeightPerBag()).ifPresent(scn::setAvgWeightPerBag);
	    Optional.ofNullable(postData.getStackNumber()).ifPresent(scn::setStackNumber);

	    Optional.ofNullable(postData.getAreaCovered()).ifPresent(scn::setAreaCovered);
	    // Optional.ofNullable(postData.getTotalWeight()).ifPresent(scn::se);
	    //		Optional.ofNullable(postData.getWeight()).ifPresent(scn::setWeight);

	    Optional.ofNullable(postData.getWeightAmt()).ifPresent(scn::setWeightAmt);
	    Optional.ofNullable(postData.getVendorId()).ifPresent(scn::setVendorId);

	    // Optional.ofNullable(postData.getAgentcode()).ifPresent(scn::setAgentcode);
	    Optional.ofNullable(postData.getQualityScore()).ifPresent(scn::setQualityScore);
	    Optional.ofNullable(postData.getVendorCode()).ifPresent(scn::setVendorCode);

	    //		Optional.ofNullable(postData.getQuantityUnit()).ifPresent(scn::setQuantityUnit);
	    Optional.ofNullable(postData.getFarmerCode()).ifPresent(scn::setFarmerCode);
	    Optional.ofNullable(postData.getSampleId()).ifPresent(scn::setSampleId);
	    Optional.ofNullable(postData.getVarietyId()).ifPresent(scn::setVarietyId);
	    Optional.ofNullable(postData.getAmount()).ifPresent(scn::setAmount);
	    Optional.ofNullable(postData.getSectionId()).ifPresent(scn::setSectionId);
	    Optional.ofNullable(postData.getReferenceId()).ifPresent(scn::setRefernceId);
	    Optional.ofNullable(postData.getImageUniqueId()).ifPresent(scn::setImageUniqueId);
	    Optional.ofNullable(postData.getRemark()).ifPresent(scn::setRemark);
	    Optional.ofNullable(postData.getVariety()).ifPresent(scn::setVarietyId);
	    Optional.ofNullable(stateAdminId).ifPresent(scn::setStateAdmin);
	    Optional.ofNullable(postData.getAcceptedBags()).ifPresent(scn::setAcceptedBags);
	    Optional.ofNullable(postData.getRejectedBags()).ifPresent(scn::setRejectedBags);

	    //check for custom variety: 17L = Custom variety
	    if(postData.getVariety() == Constants.CustomVariety.CUSTOM_VARIETY_ID){
		scn.setVarietyName(postData.getVarietyName());
	    }else{
		CommodityVarietyEntity varietyEntity = varietyRepo.getOne(postData.getVariety());
		if (varietyEntity != null) {
		    Optional.ofNullable(varietyEntity.getVarietyName()).ifPresent(scn::setVarietyName);
		}
	    }
	    // scn.setCreatedBy(userId);
	    List<ScanResultEntity> analysisResultList = new ArrayList();
	    List<Analytics> quality = new ArrayList();

	    Type typeOfT = new TypeToken<Collection<Analytics>>() {
	    }.getType();
	    quality = new Gson().fromJson(analyses, typeOfT);
	    Analytics ana = new Analytics();
	    for (Analytics a : quality) {
		System.out.println(a.getAnalysisName());

		ScanResultEntity ar = new ScanResultEntity();

		if (a.getAnalysisName().equalsIgnoreCase("density")) {

		    scn.setDensity(a.getValues());
		    System.out.println(" a.getValues() : " + a.getValues());

		} else {

		    ar.setAnalysisName(a.getAnalysisName().toLowerCase());
		    if (a.getTotalAmount() != null && !a.getTotalAmount().isEmpty()) {
			ar.setResult(new BigDecimal(a.getTotalAmount()));
		    }
		    ar.setScan(scn);
		    analysisResultList.add(ar);
		}
	    }
	    scn.setResults(analysisResultList);
	    ScanEntity scanData = scanRepo.save(scn);

	    //check if same sample_id exists in moisture meter result table or not
	    //Code below is changed to handle moisture result duplicates
	    List<MoistureMeterResult> moistureEntityList= new ArrayList<MoistureMeterResult>();
	    MoistureMeterResult entitiesExists=null;
	    		moistureEntityList= moistureMeterRepo.findBySampleIdAndCommodityName(postData.getSampleId(), postData.getCommodityName());
	    		if(moistureEntityList !=null && !moistureEntityList.isEmpty() && moistureEntityList.size()>0) {

	 			   Boolean escapeCheck=Boolean.TRUE;
	 				for(MoistureMeterResult moisture:moistureEntityList) {
	 					if(moisture !=null && escapeCheck && moisture.getMoisture() !=null) {
	 						entitiesExists=moisture;
	 						escapeCheck=Boolean.FALSE;
	 					}

	 				}
	 		   }
	    		if (entitiesExists != null) {

		if (!postData.getCommodityName().equalsIgnoreCase(entitiesExists.getCommodityName())) {
		    throw new IMException(Constants.ErrorCode.COMMODITY_NOT_MATCHED,
			    Constants.ErrorMessage.COMMODITY_NOT_MATCHED);
		}

		Boolean isMoistureUpdated = false;
		if (scanData != null && scanData.getResults() != null && scanData.getResults().size() != 0) {
		    ScanResultEntity entityForMoisture = new ScanResultEntity();

		    for (ScanResultEntity sre : scanData.getResults()) {
			if (sre != null && sre.getAnalysisName() != null
				&& sre.getAnalysisName().equalsIgnoreCase("moisturecontent")) {
			    sre.setResult(new BigDecimal(entitiesExists.getMoisture()));
			    sre.setScan(scanData);
			    resultRepo.save(sre);
			    isMoistureUpdated = true;
			} else {

			}

		    }

		}
		// save moisture
		if (isMoistureUpdated.booleanValue() != true) {
		    ScanResultEntity entityForMoisture = new ScanResultEntity();
		    entityForMoisture.setAnalysisName("moisturecontent");
		    entityForMoisture.setResult(new BigDecimal(entitiesExists.getMoisture()));
		    entityForMoisture.setScan(scn);
		    resultRepo.save(entityForMoisture);
		}
		//save temperature
		if (entitiesExists.getTemperature() != null) {
		    //		ScanResultEntity entityForTemp = new ScanResultEntity();
		    //		entityForTemp.setAnalysisName("temperature");
		    //		entityForTemp.setResult(new BigDecimal(entitiesExists.getTemperature()));
		    //		entityForTemp.setScan(scn);
		    //		resultRepo.save(entityForTemp);
		}
	    }

	    if (scanData != null) {
		ScansModel queue = new ScansModel();
		queue.setCommodity(postData.getCommodityId());
		queue.setPartnerCode(postData.getPartnerCode());
		queue.setSampleId(postData.getSampleId());
		queue.setLotId(postData.getLotId());
		queue.setProdId(postData.getProdId());
		queue.setScanId(scanData.getId().toString());
		queue.setDeviceSerialNum(postData.getDeviceSerialNo());
		queue.setScanStatusId(scanData.getApproval());
		if (scanData.getApproval() == 0) {
		    queue.setScanStatusDesc("Pending");
		} else if (scanData.getApproval() == 1) {
		    queue.setScanStatusDesc("Approved");
		} else if (scanData.getApproval() == 2) {
		    queue.setScanStatusDesc("Rejected");
		} else {
		    queue.setScanStatusDesc("Undefine Status");
		}
		//			queue.setFarmerId(scn.getFarmerId());
		// queue.setUserId(userId.toString());

		//			queue.setUserId(userId);
		if (postData.getOperatorId() != null) {
		    queue.setOperatorId(postData.getOperatorId());
		} else {
		    queue.setOperatorId(requestContext.getUserId());
		}
		adminId = null;
		//		userRepo.getAdminId(Constants.STATUS.DELETED.getId());
		queue.setAdminId(adminId);
		if (postData.getClientId() != null) {
		    clientId = postData.getClientId();//userRepo.findByCustomerCustomerId(postData.getClientId()).getUserId();
		    queue.setClientId(clientId);
		} else {
		    System.out.println(" Notification cannot be sent to client as client id is not present ");
		}
		List<Analytics> quality1 = new ArrayList<>();

		quality1.add(ana);

		queue.setAnalyticList(quality1);

		//			if (token != null && !token.isEmpty()) {
		//				queue.setToken(token);
		//				
		//			}
		//notify.send(queue);
	    }
	    return true;
	}
    }

    public List<NanoScanModel> getScansQualityByFilter(Long regionId, Long commodityId, Long ccId, Long dateFrom,
	    Long dateTo, String analysisName, String deviceType, String deviceSerialNo, Long customerId2)
	    throws IMException {
	List<NanoScanModel> quality = new ArrayList<>();
	//		Long customerId = serverContext.getRequestContext().getCustomerId();
	Long operatorId = setOperatorId();
	Long customerId = setCustomerId(customerId2);

	NanoScanModel qual = new NanoScanModel();
	BigDecimal avg = filterRepo.getAvgQuality(regionId, commodityId, ccId, analysisName, dateFrom, dateTo,
		deviceType, customerId, deviceSerialNo, operatorId);
	BigDecimal max = filterRepo.getMaxQuality(regionId, commodityId, ccId, analysisName, dateFrom, dateTo,
		deviceType, customerId, deviceSerialNo, operatorId);
	BigDecimal min = filterRepo.getMinQuality(regionId, commodityId, ccId, analysisName, dateFrom, dateTo,
		deviceType, customerId, deviceSerialNo, operatorId);
	// DecimalFormat df = new DecimalFormat("#.00");
	if (avg != null) {
	    qual.setAvgQuality(Utility.formatDecimal(avg));
	}
	if (max != null) {
	    qual.setMaxQuality(Utility.formatDecimal(max));
	}
	if (min != null) {
	    qual.setMinQuality(Utility.formatDecimal(min));
	}
	quality.add(qual);
	return quality;

    }

    private List<AnalysisResultVO> convert(List<ScanResultEntity> results) {
	List<AnalysisResultVO> response = new ArrayList<AnalysisResultVO>();
	for (ScanResultEntity result : results) {
	    AnalysisResultVO aResult = new AnalysisResultVO();
	    aResult.setResult(String.format("%.2f", result.getResult()) + "");
	    aResult.setAnalysisType(result.getAnalysisName());
	    response.add(aResult);
	}
	return response;
    }

    public List<Double> getQualityByFilter(String regionId, Long commodityId, String ccId, Long dateFrom, Long dateTo,
	    String analysisName) {

	Long operatorId = setOperatorId();
	List<Double> res = new ArrayList<>();
	List<Double> quality = filterRepo.getQualityByFilter(regionId, commodityId, ccId, analysisName, dateFrom,
		dateTo, operatorId);
	for (Double q : quality) {
	    if (q != null) {
		res.add(q);
	    }
	}
	return res;

    }

    public List<ScanCountModel> getQualityOverTimeBasedOnAmountoftea(String regionId, Long commodityId, String ccId,
	    Long dateFrom, Long dateTo, String analysisName) throws ParseException, IMException {
	return null;

    }

    public List<ScanCountModel> getQualityOverTime(Long regionId, Long commodityId, Long ccId, Long dateFrom,
	    Long dateTo, String analysisName, String deviceSerialNo, Long customerId2)
	    throws ParseException, IMException {

	List<Long> dates = new ArrayList<>();
	Long operatorId = setOperatorId();
	Long customerId = setCustomerId(customerId2);
	if (analysisName.equals("amount_of_tea")) {
	    // return
	    // getQualityOverTimeBasedOnAmountoftea(regionId,commodityId,ccId,dateFrom,
	    // dateTo,analysisName);
	}
	List<ScanCountModel> list = new ArrayList<>();
	List<Object[]> avg = filterRepo.getAvgQualityPerDay(regionId, commodityId, ccId, analysisName, dateFrom, dateTo,
		customerId, deviceSerialNo, operatorId);

	for (Object[] o : avg) {
	    ScanCountModel count = new ScanCountModel();
	    count.setScanDate(Utility.formatDateToString1((Date) o[2]));
	    count.setDateDone((Long) o[3]);
	    count.setQualityAvg(Utility.formatDecimal(Utility.getBigDecimalValue(o[1])));
	    count.setScanCount((Long) o[0]);
	    list.add(count);
	}

	return list;

    }

    public AvgScanCountModel getDailyScanCount(Long regionId, Long commodityId, Long installationCenterId,
	    Long dateFrom, Long dateTo, String analysisName, Long customerId2, Long categoryId, String deviceType,
	    Long deviceTypeId, String deviceSerialNo, Long stateId, String districtName) throws ParseException, IMException {

	Long customerId = setCustomerId(customerId2);
	Long operatorId = setOperatorId();
	Long stateManager = requestContext.getStateAdmin();

	List<Long> dates = new ArrayList<>();
	AvgScanCountModel totalScans = new AvgScanCountModel();
	List<Long> dateist = filterRepo.getScanDatesForAcceptance(regionId, commodityId, installationCenterId, null,
		dateFrom, dateTo, customerId, categoryId, deviceType, deviceTypeId, deviceSerialNo, operatorId,
		stateManager, stateId, districtName);
	if (dateist == null || dateist.isEmpty()) {
	    return null;
	}

	Long totalScanCount = filterRepo.getScanCountPerDay(regionId, commodityId, installationCenterId, null, dateFrom,
		dateTo, customerId, categoryId, deviceType, deviceTypeId, deviceSerialNo, operatorId, stateManager,
		stateId, districtName);
	List<Object[]> commodityList = filterRepo.getCommodityIds(regionId, commodityId, installationCenterId, dateFrom,
		dateTo, customerId, categoryId, deviceType, deviceTypeId, deviceSerialNo, operatorId, stateManager,
		stateId, districtName);
	// Set<Long> commodity = new LinkedHashSet<Long>(commodityList);
	List<CommodityVarianceModel> commodityVarianceList = new ArrayList<>();
	for (Object[] c : commodityList) {
	    CommodityVarianceModel var = new CommodityVarianceModel();
	    var.setCommodityName((String) c[1]);
	    var.setScanCount((Long) c[0]);
	    commodityVarianceList.add(var);
	}
	totalScans.setCommodityScanList(commodityVarianceList);
	totalScans.setTotalScanCount(totalScanCount);
	for (Long d : dateist) {
	    if (d != null) {
		dates.add(d);
	    }
	}
	List<String> scDateq = new ArrayList<>();
	Set<Long> scanDate = new HashSet<>(dates);
	Collections.sort(dates, new DateComparator());
	for (Long sDate : dates) {
	    Date currentDate = new Date(sDate);
	    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	    scDateq.add(df.format(currentDate));
	}
	Set<String> scDate = new LinkedHashSet<String>(scDateq);
	List<ScanCountModel> list = new ArrayList<>();
	// List<Long> totalScan = filterRepo.scanCountGroupByDate(regionId, commodityId,
	// installationCenterId, null, dateFrom,
	// dateTo, customerId, categoryId,deviceType,deviceTypeId);
	// for (Long cont : totalScan) {
	// String myDate1 = sDate;
	// Date date11 = new SimpleDateFormat("MM/dd/yyyy").parse(myDate1);
	// long millisFrom = date11.getTime();
	// date11.toInstant().getEpochSecond();
	//
	// Long startFrom = Long.valueOf(date11.toInstant().getEpochSecond() + "000");
	//
	// Long endTo = startFrom + 86400000;
	//
	//
	//
	// ScanCountModel count = new ScanCountModel();
	// count.setScanDate(sDate);
	//
	// count.setScanCount(totalScan);
	// list.add(count);
	// }
	for (String sDate : scDate) {
	    String myDate1 = sDate;
	    Date date11 = new SimpleDateFormat("MM/dd/yyyy").parse(myDate1);
	    long millisFrom = date11.getTime();
	    date11.toInstant().getEpochSecond();

	    Long startFrom = Long.valueOf(date11.toInstant().getEpochSecond() + "000");

	    Long endTo = startFrom + 86400000;
	    Long totalScan = filterRepo.getScanCountPerDay(regionId, commodityId, installationCenterId, null, startFrom,
		    endTo, customerId, categoryId, deviceType, deviceTypeId, deviceSerialNo, operatorId, stateManager,
		    stateId, districtName);

	    ScanCountModel count = new ScanCountModel();
	    count.setScanDate(sDate);

	    count.setScanCount(totalScan);
	    list.add(count);
	}
	totalScans.setList(list);
	return totalScans;
    }

    public AcceptanceModel getAcceptedAVGPerDay(Long regionId, Long commodityId, Long dateFrom, Long dateTo,
	    Long installationCenterId, Long customerId2, Long categoryId, String deviceType, Long deviceTypeId,
	    String deviceSerialNo, Long stateId, String districtName) throws ParseException, IMException {

	Long customerId = setCustomerId(customerId2);
	Long operatorId = setOperatorId();
	List<Long> dates = new ArrayList<>();
	BigDecimal totalAccept = new BigDecimal(0);
	BigDecimal totalA = new BigDecimal(0);
	Long avgRate = new Long(0L);
	Long rate = new Long(0L);
	Long scanCount = new Long(0L);
	Long stateAdmin = requestContext.getStateAdmin();

	//	Long totalAccept = new Long(0L);
	//	Long totalA = new Long(0L);
	AcceptanceModel acceptedRes = new AcceptanceModel();
	Long allScans = filterRepo.getAllScanCount(regionId, commodityId, installationCenterId, null, dateFrom, dateTo,
		customerId, categoryId, deviceType, deviceTypeId, deviceSerialNo, operatorId, stateAdmin, stateId, districtName);

	List<Long> dateist = filterRepo.getScanDatesForAcceptanceByCategory(regionId, commodityId, installationCenterId,
		null, dateFrom, dateTo, customerId, categoryId, deviceType, deviceTypeId, deviceSerialNo, operatorId,
		stateAdmin, stateId, districtName);
	if (dateist == null || dateist.isEmpty()) {
	    return null;
	}
	Long totalAccepted = filterRepo.getTotalScanAcceptance(regionId, commodityId, installationCenterId, dateFrom,
		dateTo, customerId, categoryId, deviceType, deviceTypeId, deviceSerialNo, operatorId, stateAdmin,
		stateId, districtName);
	BigDecimal totalAcceptedNo= new BigDecimal(totalAccepted);
	BigDecimal totalScansNo= new BigDecimal(dateist.size());
	if (totalAcceptedNo != null && totalScansNo!=null) {
		totalA = (totalAcceptedNo.multiply(BigDecimal.valueOf(100))).divide(totalScansNo,2, RoundingMode.HALF_UP) ;
	}
	List<Object[]> commodityList = filterRepo.getAcceptedSumByCommodity(regionId, commodityId, installationCenterId,
		dateFrom, dateTo, customerId, categoryId, deviceType, deviceTypeId, deviceSerialNo, operatorId,
		stateAdmin, stateId, districtName);
	List<CommodityVarianceModel> commodityVarianceList = new ArrayList<>();
	for (Object[] c : commodityList) {
		Long totalCommodity = filterRepo.getCommodityApproved( (Long) c[3], installationCenterId, dateFrom, dateTo,
				customerId, categoryId, deviceSerialNo, operatorId, stateAdmin, stateId, districtName);
	
	    CommodityVarianceModel var = new CommodityVarianceModel();
		BigDecimal totalAcceptedCommodityNo= new BigDecimal((Long) c[0]);
		BigDecimal totalCommodityNo= new BigDecimal(totalCommodity);
	    if (totalCommodity != null && (Long) c[0] !=null && totalCommodity !=0L) {
		totalAccept = totalAcceptedCommodityNo.multiply(new BigDecimal(100)).divide(totalCommodityNo,2, RoundingMode.HALF_UP);
	    }else {
	    	totalAccept=new BigDecimal(0);
	    }
	    var.setCommodityName((String) c[2]);
	    var.setAcceptance(totalAccept);
	    commodityVarianceList.add(var);
	}
//	System.out.println();
//	System.out.println();
//	System.out.println();
//	System.out.println();
//	System.out.println();
//	logger.debug(" allScans : "+allScans);
////	logger.debug("dateist : "+ dateist);
//	logger.debug("dateist size : "+ dateist.size());
//	logger.debug("totalAccepted : "+totalAccepted);
//	logger.debug(" totalA "+totalA);
//	
//	for (Object[] c : commodityList) {
//		logger.debug("(Long) c[0] : "+(Long) c[0]);
//		logger.debug("(Long) c[1] : "+(Long) c[1]);
//		logger.debug(" (String) c[2] "+(String) c[2]);
//	}
//
//
//	System.out.println();
//	System.out.println();
//	System.out.println();

	acceptedRes.setCommodityAcceptedList(commodityVarianceList);
	acceptedRes.setTotalAcceptedRate(totalA);
	for (Long d : dateist) {
	    if (d != null) {
		dates.add(d);
	    }
	}
	List<String> scDateq = new ArrayList<>();
	Set<Long> scanDate = new HashSet<>(dates);
	// List<Long> dateList = new ArrayList<>(scanDate);

	Collections.sort(dates, new DateComparator());
	// System.out.print(dates);
	for (Long sDate : dates) {

	    Date currentDate = new Date(sDate);
	    // System.out.print(currentDate);
	    // System.out.print(currentDate.toInstant().getEpochSecond());
	    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	    // System.out.print(df.format(currentDate));
	    scDateq.add(df.format(currentDate));

	    // scDate.add(df.format(currentDate));
	}
	Set<String> scDate = new LinkedHashSet<String>(scDateq);
	List<ScanCountModel> list = new ArrayList<>();
	for (String sDate : scDate) {
	    String myDate1 = sDate;
	    Date date11 = new SimpleDateFormat("MM/dd/yyyy").parse(myDate1);
	    // System.out.println(myDate1 + "\t" + date11);

	    long millisFrom = date11.getTime();
	    date11.toInstant().getEpochSecond();
	    // System.out.print(date11.toInstant().getEpochSecond());
	    Long startFrom = Long.valueOf(date11.toInstant().getEpochSecond() + "000");

	    Long endTo = startFrom + 86400000;
	    List<Object[]> rateAndCount = filterRepo.getScanAcceptanceRateAndCountPerDay(regionId, commodityId,
		    installationCenterId, startFrom, endTo, customerId, categoryId, deviceType, deviceTypeId,
		    deviceSerialNo, operatorId, stateAdmin, stateId, districtName);
	    if (rateAndCount != null && !rateAndCount.isEmpty() && rateAndCount.size() != 0) {
		rate = (Long) rateAndCount.get(0)[1];
		scanCount = (Long) rateAndCount.get(0)[0];
	    }
	    if (rate != null && allScans != null) {
		avgRate = (rate * 100) / allScans;
	    }
	    ScanCountModel count = new ScanCountModel();
	    count.setScanDate(sDate);
	    count.setAvgAcceptance(avgRate);
	    count.setScanCount(scanCount);
	    list.add(count);
	}
	acceptedRes.setList(list);
	return acceptedRes;
    }

    public VarianceModel getVarianceAVGPerDay(Long regionId, Long commodityId, Long dateFrom, Long dateTo,
	    Long installationCenterId, Long customerId2, Long categoryId, String deviceType, Long deviceTypeId,
	    String deviceSerialNo, Long stateId, String districtName) throws ParseException, IMException {

	Long customerId = setCustomerId(customerId2);
	Long operatorId = setOperatorId();
	Long stateAdmin = requestContext.getStateAdmin();

	VarianceModel varianceRes = new VarianceModel();
	ScanCountModel scan = new ScanCountModel();
	List<Long> dates = new ArrayList<>();

	List<Long> dateist = filterRepo.getScanDatesForAcceptanceByCategory(regionId, commodityId, installationCenterId,
		null, dateFrom, dateTo, customerId, categoryId, deviceType, deviceTypeId, deviceSerialNo, operatorId,
		stateAdmin, stateId, districtName);

	if (dateist == null || dateist.isEmpty()) {
	    return null;
	}
	Long totalVariance = filterRepo.getTotalScanVariance(regionId, commodityId, installationCenterId, dateFrom,
		dateTo, customerId, categoryId, deviceType, deviceTypeId, deviceSerialNo, operatorId, stateId, districtName);
	Long totalV = totalVariance / dateist.size();
	scan.setTotalAvgVariance(totalV);
	varianceRes.setTotalAvgVariance(totalV);
	List<Object[]> commodityList = filterRepo.getVarianceSumByCommodity(regionId, commodityId, installationCenterId,
		dateFrom, dateTo, customerId, categoryId, deviceType, deviceTypeId, deviceSerialNo, operatorId,
		stateId, districtName);
	// Set<Long> commodity = new LinkedHashSet<Long>(commodityList);
	List<CommodityVarianceModel> commodityVarianceList = new ArrayList<>();
	for (Object[] c : commodityList) {
	    CommodityVarianceModel var = new CommodityVarianceModel();

	    Long avgVariance = (Long) c[1];
	    Long totalScan = (Long) c[0];
	    Long commodityVariance = avgVariance / totalScan;
	    // String commodityNamee = restTemplateCalls.getCommodityName(c);
	    var.setCommodityName((String) c[2]);
	    var.setVariance(commodityVariance);
	    commodityVarianceList.add(var);
	}
	varianceRes.setCommodityVarianceList(commodityVarianceList);
	for (Long d : dateist) {
	    if (d != null) {
		dates.add(d);
	    }
	}
	List<String> scDateq = new ArrayList<>();
	Set<Long> scanDate = new HashSet<>(dates);
	// List<Long> dateList = new ArrayList<>(scanDate);

	Collections.sort(dates, new DateComparator());
	// System.out.print(dates);
	for (Long sDate : dates) {

	    Date currentDate = new Date(sDate);
	    // System.out.print(currentDate);
	    // System.out.print(currentDate.toInstant().getEpochSecond());
	    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	    // System.out.print(df.format(currentDate));
	    scDateq.add(df.format(currentDate));

	    // scDate.add(df.format(currentDate));
	}
	Set<String> scDate = new LinkedHashSet<String>(scDateq);
	List<ScanCountModel> list = new ArrayList<>();
	for (String sDate : scDate) {
	    String myDate1 = sDate;
	    Date date11 = new SimpleDateFormat("MM/dd/yyyy").parse(myDate1);
	    // System.out.println(myDate1 + "\t" + date11);

	    long millisFrom = date11.getTime();
	    date11.toInstant().getEpochSecond();
	    // System.out.print(date11.toInstant().getEpochSecond());
	    Long startFrom = Long.valueOf(date11.toInstant().getEpochSecond() + "000");

	    Long endTo = startFrom + 86400000;

	    List<Object[]> rateAndCount = filterRepo.getScanVarianceRateAndCountPerDay(regionId, commodityId,
		    installationCenterId, startFrom, endTo, customerId, categoryId, deviceType, deviceTypeId,
		    deviceSerialNo, operatorId, stateId, districtName);
	    Long avgvarianceRate = (Long) rateAndCount.get(0)[1] / (Long) rateAndCount.get(0)[0];

	    ScanCountModel count = new ScanCountModel();
	    count.setScanDate(sDate);
	    // count.setAvgAcceptance(avgRate);
	    count.setAvgVariance(avgvarianceRate);
	    // count.setQualityAvg(Utility.formatDecimal(avg));
	    count.setScanCount((Long) rateAndCount.get(0)[0]);
	    list.add(count);
	}
	varianceRes.setList(list);
	// variance.setTotalAvgVariance(totalAvgVariance);
	scan.setData(list);
	return varianceRes;
    }

    public ScanHistoryResponseModel getScanHistoryTest(Integer pageNumber, Integer limit, Long customerId2,
	    Long commodityId, Long ccId, Long dateFrom, Long dateTo, Long userId, String deviceType, Long deviceTypeId,
	    String deviceSerialNumber, String userType, Long commCategoryId, String sortByRequested,
	    String sortTypeRequested, Long stateId) throws IMException {

	logger.info("get scan history");
	List<ScanResultModel> response = new ArrayList<ScanResultModel>();
	ScanHistoryResponseModel finalResponseModel = new ScanHistoryResponseModel();

	Long customerId = setCustomerId(customerId2);
	Long operatorId = setOperatorId();

	String sortParam = "createdOn";
	String sortType = "desc";
	Sort sort = null;

	if (sortByRequested != null && !sortByRequested.equalsIgnoreCase("moisture")) {
	    sortParam = sortByRequested;

	    if (sortTypeRequested != null) {
		sortType = sortTypeRequested;
	    }
	}

	if (sortType.equalsIgnoreCase("desc")) {
	    sort = Sort.by(Direction.DESC, sortParam);
	} else {
	    sort = Sort.by(Direction.ASC, sortParam);
	}

	Pageable pageable = PageRequest.of(Optional.ofNullable(pageNumber).orElse(Constants.ZERO),
		Optional.ofNullable(limit).orElse(Constants.RECORD_LIMIT), sort);

	if (userType != null && userType.equalsIgnoreCase("farmer")) {
	    customerId = null;
	}
	
	List<ScanEntity> results = scanRepo.getScanHistory(customerId, commodityId, ccId, dateFrom, dateTo, userId,
		deviceType, deviceTypeId, deviceSerialNumber, pageable, operatorId, commCategoryId, stateId);



	List<ScanEntity> resultsCount = scanRepo.getScanHistoryCount(customerId, commodityId, ccId, dateFrom, dateTo,
		userId, deviceType, deviceTypeId, deviceSerialNumber, operatorId, commCategoryId, stateId);

	if (requestContext.getUserEmail().equals("ai@agnext.in")) {

	    List<ScanEntity> resultsCountAI = scanRepo.getScanHistoryCount(customerId, commodityId, ccId, dateFrom,
		    dateTo, userId, deviceType, deviceTypeId, deviceSerialNumber, operatorId, commCategoryId, stateId);
	    
		List<ScanEntity> resultsAI = scanRepo.getScanHistoryAIModel(customerId, commodityId, ccId, dateFrom, dateTo,
				userId, deviceType, deviceTypeId, deviceSerialNumber, pageable, operatorId, commCategoryId, stateId);

	    for (ScanEntity result : resultsAI)
		response.add(convert(result));

	    finalResponseModel.setTotalCount(resultsCountAI.size());
	    finalResponseModel.setScanModelList(response);

	} else {

	    for (ScanEntity result : results)

		if (result != null)
		    response.add(convert(result));

	    finalResponseModel.setTotalCount(resultsCount.size());
	    finalResponseModel.setScanModelList(response);
	}
	System.out.println();
	logger.debug(" pageable "+pageable);
	System.out.println();
	return finalResponseModel;
    }

    private Long setCustomerId(Long customerId2) throws IMException {
	logger.info(" Inside setCustomerId method, the customer id to set is : " + customerId2);
	System.out.println(" Request Context : " + applicationContext.getRequestContext());
	Long customerId = null;
	if (Constants.CustomerType.CLIENT.equals(applicationContext.getRequestContext().getCustomerType())) {
	    if (customerId2 == null) {
		customerId = applicationContext.getRequestContext().getCustomerId();
	    } else {
		customerId = customerId2;
	    }

	} else if (Constants.CustomerType.CUSTOMER.equals(applicationContext.getRequestContext().getCustomerType())) {
	    customerId = null;

	} else if (requestContext.getRoles().contains("state_admin")) {
	    customerId = applicationContext.getRequestContext().getCustomerId();
	}
	return customerId;
    }

    private ScanResultModel convert(ScanEntity result) throws IMException {
	ScanResultModel response = new ScanResultModel();

	Optional.ofNullable(result.getVarietyId()).ifPresent(response::setVarietyId);
	Optional.ofNullable(result.getVarietyName()).ifPresent(response::setVarietyName);

	response.setBatchId(result.getBatchId());
	response.setCommodityId(result.getCommodityId());
	response.setDate_done(result.getCreatedOn());
	response.setDeviceType(result.getDeviceType());
	response.setDeviceSerialNo(result.getDeviceSerialNo());
	response.setScanId(result.getId());
	response.setDeviceType(result.getDeviceType());
	response.setDeviceTypeId(result.getDeviceTypeId());
	response.setCommodityName(result.getCommodityName());
	response.setSampleId(result.getSampleId());
	if (result.getCustomerId() != null) {
	    CustomerEntity customerEntity = customerRepo.getOne(result.getCustomerId());
	    response.setCustomerId(result.getCustomerId());
	    response.setCustomerName(customerEntity.getCustomerName());
	}

	WeightConverterModel weightDetails = new WeightConverterModel();
	weightDetails = Utility.fetchWeightConverter(result.getWeight(), result.getQuantityUnit());
	System.out.println("scanId : " + result.getId() + " ,  Weight : " + weightDetails.getWeight() + " : "
		+ result.getWeight() + " ,  unit : " + weightDetails.getUnit() + " : " + result.getQuantityUnit());
	response.setWeight(Utility.convertQuintalsToTons(result.getWeight()));
	response.setUnit(Constants.WEIGHT_UNIT);

	response.setApproval(result.getApproval());
	if (result.getApproval() == 0) {
	    response.setApprovalDesc("Pending");
	}

	else if (result.getApproval() == 1) {
	    response.setApprovalDesc("Approved");
	}

	else if (result.getApproval() == 2) {
	    response.setApprovalDesc("Rejected");
	} else {
	    response.setApprovalDesc("Undefined");
	}

	//		if (result.getQuantityUnit() != null && !result.getQuantityUnit().isEmpty())
	//			Optional.ofNullable(result.getQuantityUnit()).ifPresent(response::setUnit);
	//
	//		if (result.getApproval() != null) {
	//			response.setApproval(result.getApproval());
	//		}
	//		if (result.getQualityScore() != null) {
	//			response.setQualityScore((result.getQualityScore()).intValue());
	//		}
	//		Optional.ofNullable(result.getWeight()).ifPresent(response::setWeight);

	response.setAnalysisResultList(convert(result.getResults()));
List<MoistureMeterResult> moistureEntityList= new ArrayList<MoistureMeterResult>();
MoistureMeterResult moistureEntity= null;
	 moistureEntityList = moistureMeterRepo.findBySampleIdAndCommodityName(result.getSampleId(),result.getCommodityName());
	   if(moistureEntityList !=null && !moistureEntityList.isEmpty() && moistureEntityList.size()>0) {

		   Boolean escapeCheck=Boolean.TRUE;
			for(MoistureMeterResult moisture:moistureEntityList) {
				if(moisture !=null && escapeCheck && moisture.getMoisture() !=null) {
					moistureEntity=moisture;
					escapeCheck=Boolean.FALSE;
				}

			}
	   }
	if (moistureEntity != null) {
	    response.setMoisture(moistureEntity.getMoisture());
	    response.setTemperature(moistureEntity.getTemperature());
	    response.setIsMoistureResultManual(Boolean.FALSE);
	}else {
		
	 Double moisture=new Double(0);
	
	 for (AnalysisResultVO ar : response.getAnalysisResultList()) {
		if(ar.getAnalysisType() !=null && ar.getAnalysisType().trim().equalsIgnoreCase("moisture")|| ar.getAnalysisType().trim().equalsIgnoreCase("moisturecontent")) {
			moisture=new Double(ar.getResult());
		}
	}
	 
		response.setMoisture(moisture);
		response.setIsMoistureResultManual(Boolean.TRUE);
	}
	return response;
    }

    public ScanModel getScanDetailByScanId(Long scanId) throws IMException, IOException {
	// logger.info("get scan result by scan id");
	ScanEntity scmScanEntity = scanRepo.getOne(scanId);
	//Multiple analytics handled
	Map<Long, List<String>> commoditiesAnalyticsMap= new HashMap<>();
	commoditiesAnalyticsMap=commodityAnalyticsMap.getMap();

	//List<String > aNames=commoditiesAnalyticsMap.get(scmScanEntity.getCommodityId());
	return EntityToVOAssembler.convertScmScan(scmScanEntity,commoditiesAnalyticsMap, analyticsVariations);

    }

    public MetaDataModel getScanMetaDataByScanId(Long scanId) throws IMException, IOException {
	ScanEntity scmScanEntity = scanRepo.getOne(scanId);
	return EntityToVOAssembler.convertModelForMetaData(scmScanEntity, customerRepo);
    }

    public List<QualityRules> getQualityGrade(String regionId, Long commodityId, String ccId, Long dateFrom,
	    Long dateTo, String analysisName) throws IMException {
	List<QualityRules> list = new ArrayList<>();

	Long operatorId = setOperatorId();
	List<QualityGradeRules> lis = qualityRulesRepo.getRuleList(commodityId);
	if (lis == null || lis.isEmpty()) {
	    return null;
	}
	for (QualityGradeRules q : lis) {
	    QualityRules qualityGrade = new QualityRules();
	    qualityGrade.setGrade(q.getGrade());
	    Long count = filterRepo.scanCountByQualityGrade(commodityId, Double.valueOf(q.getMaxVal()),
		    Double.valueOf(q.getMinVal()), q.getAnalysisCode(), null, operatorId);
	    qualityGrade.setAnalysis_code(q.getAnalysisCode());
	    qualityGrade.setScanCount(count);
	    list.add(qualityGrade);
	}
	return list;

    }

    public ResponseEntity generatePdfForScanResults(Long scanId, HttpServletResponse response) throws Exception {
	PhysicalScanModel vo = fetchScansByIdWithResults(scanId);
	Map<Long, List<String>> commoditiesAnalyticsMap= new HashMap<>();
	commoditiesAnalyticsMap=commodityAnalyticsMap.getMap();
	List<String>standardList=commoditiesAnalyticsMap.get(vo.getCommodityId());
	return GeneratePhysicalScanPdf.generatePhysicalScanPdf(vo, response, properties.getName(),standardList,analyticsVariations);
    }

    public PhysicalScanModel fetchScansByIdWithResults(Long scanId) throws IMException {
	ScanEntity lastScan = scanRepo.getById(scanId);
	CustomerEntity customerEntity = customerRepo.getOne(lastScan.getCustomerId());
	CustomerAddressEntity customerAddressEntity = customerAddressRepo
		.findByCustomerCustomerId(lastScan.getCustomerId());
	CityEntity cityEntity = cityRepo.getOne(Long.parseLong(customerAddressEntity.getCity()));
	StateEntity stateEntity = stateRepo.getOne(Long.parseLong(customerAddressEntity.getState()));
	CountryEntity countryEntity = countryRepo.getOne(Long.parseLong(customerAddressEntity.getCountry()));

	PhysicalScanModel scanVO = null;
	if (lastScan != null) {
	    scanVO = new PhysicalScanModel();

	    scanVO.setSampleId(lastScan.getSampleId());
	    scanVO.setVariety(lastScan.getVarietyName());
	    Optional.ofNullable(lastScan.getGrade()).ifPresent(scanVO::setGrade);
	    if (lastScan.getDeviceSerialNo() != null)
		scanVO.setDeviceSerialNumber(lastScan.getDeviceSerialNo());
	    if (lastScan.getCommodityId() != null)
		scanVO.setCommodityName(lastScan.getCommodityName());
	    scanVO.setCommodityId(lastScan.getCommodityId());
	    if (customerEntity != null)
		scanVO.setUsername(customerEntity.getCustomerName());
	    scanVO.setAddress(customerAddressEntity.getAddressLine1().trim() + ", " + cityEntity.getName().trim()
		    + " - " + String.valueOf(customerAddressEntity.getZipCode()).trim() + ", "
		    + stateEntity.getName().trim() + ", " + countryEntity.getName().trim());
	    if (lastScan.getTruckNumber() != null)
		scanVO.setTruckNumber(lastScan.getTruckNumber());
	    if (lastScan.getDeviceType() != null)
		scanVO.setDeviceType(lastScan.getDeviceType());

	    scanVO.setId(lastScan.getId());
	    if (lastScan.getBatchId() != null)
		scanVO.setBatchId(lastScan.getBatchId());
	    scanVO.setCreatedOn(lastScan.getCreatedOn());
	    // scanVO.setBarCode("cmweocmwemckwmxl");
	    if (lastScan.getRefernceId() != null)
		scanVO.setReferenceId(lastScan.getRefernceId());

	    WeightConverterModel weightDetails = new WeightConverterModel();
	    if (lastScan.getSampleWeight() != null) {
		weightDetails = Utility.fetchWeightConverter(lastScan.getSampleWeight(),
			lastScan.getSampleWeightUnit());
		scanVO.setSampleWeight(weightDetails.getWeight().setScale(2, BigDecimal.ROUND_HALF_EVEN));
	    }

	    if (lastScan.getSampleWeightUnit() != null)
		scanVO.setSampleWeightUnit(weightDetails.getUnit());

	    List<ScanResultEntity> scanResults = lastScan.getResults();
	    if (scanResults != null) {
		List<Analytics> resultList = new ArrayList<>();
		for (ScanResultEntity s : scanResults) {
		    Analytics analysis = new Analytics();
		    analysis.setAnalysisName(s.getAnalysisName());
		    analysis.setResult(s.getResult());
		    resultList.add(analysis);
		}
		scanVO.setsAnalysisResults(resultList);

	    }
	}
	return scanVO;
    }

    /**
     * Return Image List
     *
     * @param id
     * @param response
     * @return
     */
    public List<ImageModel> downloadFile(Long id, HttpServletResponse response) {
	Map<Integer, String> returnMap = new HashMap<>();
	String imageUniqueId = "";
	ScanEntity physicalScan = scanRepo.getById(id);
	if (physicalScan != null && physicalScan.getImageUniqueId() != null
		&& !physicalScan.getImageUniqueId().isEmpty()) {
	    imageUniqueId = physicalScan.getImageUniqueId();
	} else {
	    imageUniqueId = "16018953367WELK";
	}
	logger.debug("ImageUniquedIds : {}", imageUniqueId);
	String[] imageIds = imageUniqueId.split(":");
	List<ImageModel> modelList = new ArrayList<>();
	Arrays.stream(imageIds).forEach(a -> {
	    ImageModel image = new ImageModel();
	    setFileParams(image, a);
	    if (image.getImageId() != null) {
		modelList.add(image);
	    }
	});
	return modelList;
    }

    /**
     * Set FIle Params
     *
     * @param image
     * @param imageId
     */
    private void setFileParams(ImageModel image, String imageId) {
	String fileName = "";
	String finalURL = "";
	try {
	    finalURL = getFileUrl(imageId);
	    final HttpURLConnection httpConnection = getHttpConnection(finalURL);
	    int responseCode = httpConnection.getResponseCode();
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	    fileName = getFileName(httpConnection, finalURL);
	    
	    image.setImageId(imageId);
		image.setFileName(imageId + "_" + fileName);
		image.setFileExtension(".jpg");
		image.setUrl(finalURL);}
	    else {
	    	image=null;
	    }
	} catch (IOException e) {
	    logger.error("FileParams are not set for image with Id : {}", imageId);
	    e.printStackTrace();
	}
	
    }

    /**
     * Set File Names
     *
     * @param image
     * @param imageId
     */

    /**
     *
     *
     * @param id
     * @param response
     * @throws IOException
     */
    public void downloadImageById(String id, HttpServletResponse response) throws IOException {
	final int BUFFER_SIZE = 4096;
	logger.debug("Image Id to be downloaded : {}", id);
	String fileURL = getFileUrl(id);
	;
	HttpURLConnection httpConn = getHttpConnection(fileURL);
	int responseCode = httpConn.getResponseCode();

	// always check HTTP response code first
	if (responseCode == HttpURLConnection.HTTP_OK) {
	    String fileName = getFileName(httpConn, fileURL);
	    // opens input stream from the HTTP connection
	    InputStream inputStream = httpConn.getInputStream();
	    // opens an output stream to save into file
	    // FileOutputStream outputStream = new FileOutputStream(saveFilePath);
	    ServletOutputStream op = response.getOutputStream();
	    response.setContentType("application/octet-stream");
	    response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	    int bytesRead = -1;
	    byte[] buffer = new byte[BUFFER_SIZE];
	    while ((bytesRead = inputStream.read(buffer)) != -1) {
		op.write(buffer, 0, bytesRead);
	    }
	    op.flush();
	    inputStream.close();
	    logger.debug("File downloaded");
	} else {
	    logger.debug("No file to download. Server replied HTTP code: " + responseCode);
	}
	httpConn.disconnect();

    }

    /**
     * Get File Name
     *
     * @param httpConn
     * @param fileURL
     * @return
     */
    private String getFileName(HttpURLConnection httpConn, String fileURL) {
	String fileName = "";
	String disposition = httpConn.getHeaderField("Content-Disposition");
	String contentType = httpConn.getContentType();
	int contentLength = httpConn.getContentLength();
	logger.debug("Content-Type = {}", contentType);
	logger.debug("Content-Disposition ={} ", disposition);
	logger.debug("Content-Length = {}", contentLength);
	logger.debug("fileName = {}", fileName);

	if (disposition != null) {
	    // extracts file name from header field
	    int index = disposition.indexOf("filename=");
	    if (index > 0) {
		fileName = disposition.substring(index + 10, disposition.length() - 1);
	    }
	} else {
	    // extracts file name from URL
	    fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
	}
	return fileName;
    }

    /**
     * Return FIle URL
     * 
     * @param id
     * @return
     */
    private String getFileUrl(String id) {
	if (applicationContext.getRequestContext().getUserEmail().equals(customerAdminEmail)) {
	    return "https://agnext-jasmine.s3.us-east-2.amazonaws.com/visio_desktop/nafed_images/" + id
		    + "/scanned_image.jpg";
	} else {
	    return "https://agnext-jasmine.s3.us-east-2.amazonaws.com/visio_desktop/nafed_images/" + id
		    + "/scanned_image.jpg";
	}
    }

    /**
     * Return HttpURL
     * 
     * @param fileURL
     * @return
     * @throws IOException
     */
    private HttpURLConnection getHttpConnection(String fileURL) throws IOException {
	URL url = new URL(fileURL);
	logger.debug("URL is : {}", url.toString());
	HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
	return httpConn;
    }

    public void approveScan(ScanModel scanModel) throws JsonProcessingException, IMException {
	ScansModel statusChanges = new ScansModel();
	String oldStatusDesc = "";
	String newStatusDesc = "";
	ScanEntity scn = scanRepo.getOne(scanModel.getId());
	scn.setApproval(scanModel.getApproval());
	scn.setMessage(scanModel.getMessage());
	scn.setStatusChangedOn(Utility.getCurrentDateTime());
	ScanEntity changedScan = scanRepo.save(scn);

	oldStatusDesc = setStatusDesc(scn.getApproval());
	newStatusDesc = setStatusDesc(changedScan.getApproval());

	statusChanges.setOldScanStatusDesc(oldStatusDesc);
	statusChanges.setNewScanStatusDesc(newStatusDesc);
	statusChanges.setOperatorId(changedScan.getOperatorId());
	statusChanges.setDeviceSerialNum(changedScan.getDeviceSerialNo());
	statusChanges.setDeviceType(changedScan.getDeviceType());
	statusChanges.setScanId(changedScan.getId().toString());
	statusChanges.setScanStatusId(changedScan.getApproval());
	statusChanges.setScanStatusDesc(setStatusDesc(changedScan.getApproval()));

	System.out.println(" Customer type : " + applicationContext.getRequestContext().getCustomerType());

	if (changedScan != null && applicationContext.getRequestContext().getCustomerType()
		.equalsIgnoreCase(Constants.CustomerType.CLIENT)) {
	    logger.info(" Notification sending.......   ");
	    //	    notify.sendClientStatusChangeNotification(statusChanges);
	}

    }

    public List<QualityMapModel> getQualityMapByFilter(Long commodityId, Long dateFrom, Long dateTo,
	    String analysisName) throws IMException {
	List<QualityMapModel> polyData = new ArrayList<>();
	Long customerId = applicationContext.getRequestContext().getCustomerId();
	Long operatorId = setOperatorId();
	List<Object[]> plots = filterRepo.getPlotInstAndAvgByPlot(commodityId, analysisName, dateFrom, dateTo,
		customerId, operatorId);
	if (plots == null || plots.isEmpty()) {
	    return null;
	}
	for (Object[] data : plots) {
	    if ((Long) data[1] != null) {
		QualityMapModel map = new QualityMapModel();

		if (data[0] != null) {
		    map.setAvgQuality(Utility.formatDecimal(Utility.getBigDecimalValue(data[0])));
		}

		PlotEntity plotEntity = plotRepo.getOne((Long) data[1]);
		PlotModel plot = new PlotModel();
		if (plotEntity != null) {
		    plot.setPlotId(plotEntity.getId());
		    plot.setLatitude(plotEntity.getLat());
		    plot.setLongitude(plotEntity.getLang());
		}
		//PlotModel plot = restTemplateCalls.getPlotLatLng((Long) data[1]);
		if (plot != null) {
		    String[] lat = plot.getLatitude().split(",");
		    String[] lang = plot.getLongitude().split(",");
		    List<CordinateModel> coordinates = new ArrayList<>();
		    for (int i = 0; i < lat.length; i++) {
			// String data={"latitude:" + lat[i] + ",longitude:" + lang[i]};
			CordinateModel cordinates = new CordinateModel();
			cordinates.setLatitude(Double.valueOf(lat[i]));
			cordinates.setLongitude(Double.valueOf(lang[i]));
			coordinates.add(cordinates);
		    }
		    map.setCoordinates(coordinates);
		    // String centerName =
		    // restTemplateCalls.getCenterName(id.getInstallation_center_id());
		    map.setCenterName((String) data[2]);
		    map.setCollector("NA");
		    polyData.add(map);
		}

	    }

	}
	return polyData;

    }

    public String setStatusDesc(Integer statusId) {
	String queue = "";
	if (statusId == 0) {
	    queue = "Pending";
	} else if (statusId == 1) {
	    queue = "Approved";
	} else if (statusId == 2) {

	    queue = "Rejected";
	} else {
	    queue = "Undefine Status";
	}
	return queue;
    }

    private Boolean saveAIModelData(ScanModel postData, String analyses) throws IMException, JsonProcessingException {
	String token = null;
	Long clientId = null;
	Long adminId = null;
	Long operatorId = null;
	ScanEntity scn = new ScanEntity();
	scn.setBatchId(postData.getBatchId());
	// UUID scanId = UUID.randomUUID();
	scn.setInstCenterTypeId(postData.getInstCenterTypeId());
	if (postData.getOperatorId() != null) {
	    scn.setOperatorId(postData.getOperatorId());

	} else {
	    scn.setOperatorId(requestContext.getUserId());
	}

	// Long customerId = serverContext.getRequestContext().getCustomerId();
	DcmCommodity commodityId = commodityRepository.getCommodityName(postData.getCommodityName().trim());
	scn.setCommodityId(commodityId.getId());
	scn.setCommodityCategoryId(commodityId.getDcmCommodityCategory().getId());
	DcmDevice device = dcmDeviceRepo.findBySerialNumber(postData.getDeviceSerialNo());
	// Boolean clientCategoryCheck =
	// checkClientCategory(postData.getCommodityCategoryId(), customerId);
	//		if (!clientCategoryCheck) {
	//			logger.info("SaveScan :check Failed for CLient Category");
	//			return false;
	//		}
	// scn.setCommodityCategoryId(postData.getCommodityCategoryId());

	scn.setCommodityName(commodityId.getCommodityName());
	scn.setCommodityVarietyId(postData.getCommodityVarietyId());
	// scn.setRegionId(device.getDcmCommercialLocation().getDcmRegion().getId());
	ScanLocation location = scanLocationRepo.findByLocationName(postData.getLocation().trim());
	if (location != null) {
	    scn.setInstallatonCenterId(location.getId());
	} else {
	    ScanLocation loc = saveLocation(postData.getLocation().trim(), postData.getwHouse(), postData.getStateId());
	    scn.setInstallatonCenterId(loc.getId());
	    ;
	}
	CustomerEntity customer = customerRepo.findByCustomerName(postData.getCustomerName());
	if (customer != null && customer.getCustomerId() != null)
	    scn.setCustomerId(customer.getCustomerId());
	scn.setDeviceSerialNo(postData.getDeviceSerialNo());
	scn.setCreatedOn(Long.valueOf(Instant.now().getEpochSecond() + "000"));
	if (postData.getApproval() != null) {
	    scn.setApproval(postData.getApproval());
	} else if (postData.getApproval() == null) {
	    scn.setApproval(0);
	}

	Optional.ofNullable(postData.getLotId()).ifPresent(scn::setLotId);
	Optional.ofNullable(postData.getWeighbridgeName()).ifPresent(scn::setWeighbridgeName);
	Optional.ofNullable(postData.getGRNNumber()).ifPresent(scn::setGRNNumber);
	Optional.ofNullable(postData.getCropYear()).ifPresent(scn::setCropYear);

	Optional.ofNullable(device.getDcmDeviceType().getId()).ifPresent(scn::setDeviceTypeId);
	Optional.ofNullable(device.getDcmDeviceType().getDeviceTypeDesc()).ifPresent(scn::setDeviceType);
	scn.setPlotId(20L);
	WeightConverterModel weightDetails = new WeightConverterModel();
	weightDetails = Utility.postWeightConverter(postData.getWeight(), postData.getQuantityUnit());
	System.out.println(
		"********* Weight : " + weightDetails.getWeight() + " Weight unit : " + weightDetails.getUnit());
	scn.setWeight(weightDetails.getWeight());
	scn.setQuantityUnit(weightDetails.getUnit());

	//		if (postData.getQuantityUnit() != null && !postData.getQuantityUnit().isEmpty()) {
	//			scn.setQuantityUnit(postData.getQuantityUnit());
	//			if (postData.getQuantityUnit().equalsIgnoreCase("kg")) {
	//				Optional.ofNullable(postData.getQuantity() / 1000).ifPresent(scn::setQuantity);
	//			} else {
	//				Optional.ofNullable(postData.getQuantity()).ifPresent(scn::setQuantity);
	//			}
	//		} else {
	//			Optional.ofNullable(postData.getQuantity()).ifPresent(scn::setQuantity);
	//			scn.setQuantityUnit("Quintals");
	//		}
	Optional.ofNullable("A").ifPresent(scn::setGrade);
	Optional.ofNullable(1).ifPresent(scn::setAccepted);
	Optional.ofNullable(Long.valueOf(10)).ifPresent(scn::setVariance);
	Optional.ofNullable(Utility.getCurrentDate()).ifPresent(scn::setCreatedOnDate);
	Optional.ofNullable(Long.valueOf(Instant.now().getEpochSecond() + "000")).ifPresent(scn::setCreatedOn);

	Optional.ofNullable(postData.getLocation()).ifPresent(scn::setLocation);
	Optional.ofNullable(postData.getTruckGrossWeight()).ifPresent(scn::setTruckGrossWeight);
	Optional.ofNullable(postData.getTruckNumber()).ifPresent(scn::setTruckNumber);
	Optional.ofNullable(postData.getTruckNetWeight()).ifPresent(scn::setTruckNetWeight);
	Optional.ofNullable(postData.getTruckTareWeight()).ifPresent(scn::setTruckTareWeight);
	Optional.ofNullable(postData.getBag()).ifPresent(scn::setBag);
	Optional.ofNullable(postData.getCadNo()).ifPresent(scn::setCadNo);
	Optional.ofNullable(postData.getGatePass()).ifPresent(scn::setGatePass);
	Optional.ofNullable(postData.getSlipNo()).ifPresent(scn::setSlipNo);
	Optional.ofNullable(postData.getChamberNumber()).ifPresent(scn::setChamberNumber);
	Optional.ofNullable(postData.getPackingSize()).ifPresent(scn::setPackingSize);

	Optional.ofNullable(postData.getAvgWeightPerBag()).ifPresent(scn::setAvgWeightPerBag);
	Optional.ofNullable(postData.getStackNumber()).ifPresent(scn::setStackNumber);

	Optional.ofNullable(postData.getAreaCovered()).ifPresent(scn::setAreaCovered);
	// Optional.ofNullable(postData.getTotalWeight()).ifPresent(scn::se);
	//		Optional.ofNullable(postData.getWeight()).ifPresent(scn::setWeight);

	Optional.ofNullable(postData.getWeightAmt()).ifPresent(scn::setWeightAmt);
	Optional.ofNullable(postData.getVendorId()).ifPresent(scn::setVendorId);

	// Optional.ofNullable(postData.getAgentcode()).ifPresent(scn::setAgentcode);
	Optional.ofNullable(postData.getQualityScore()).ifPresent(scn::setQualityScore);
	Optional.ofNullable(postData.getVendorCode()).ifPresent(scn::setVendorCode);

	//		Optional.ofNullable(postData.getQuantityUnit()).ifPresent(scn::setQuantityUnit);
	Optional.ofNullable(postData.getFarmerCode()).ifPresent(scn::setFarmerCode);
	Optional.ofNullable(postData.getSampleId()).ifPresent(scn::setSampleId);
	Optional.ofNullable(postData.getVarietyId()).ifPresent(scn::setVarietyId);
	Optional.ofNullable(postData.getAmount()).ifPresent(scn::setAmount);
	Optional.ofNullable(postData.getSectionId()).ifPresent(scn::setSectionId);
	Optional.ofNullable(postData.getReferenceId()).ifPresent(scn::setRefernceId);
	Optional.ofNullable(postData.getImageUniqueId()).ifPresent(scn::setImageUniqueId);

	// scn.setCreatedBy(userId);
	List<ScanResultEntity> analysisResultList = new ArrayList();
	List<Analytics> quality = new ArrayList();

	Type typeOfT = new TypeToken<Collection<Analytics>>() {
	}.getType();
	quality = new Gson().fromJson(analyses, typeOfT);
	Analytics ana = new Analytics();
	for (Analytics a : quality) {
	    System.out.println(a.getAnalysisName());

	    ScanResultEntity ar = new ScanResultEntity();

	    if (a.getAnalysisName().equalsIgnoreCase("density")) {

		scn.setDensity(a.getValues());
		System.out.println(" a.getValues() : " + a.getValues());

	    } else {

		ar.setAnalysisName(a.getAnalysisName().toLowerCase());
		ar.setResult(new BigDecimal(a.getTotalAmount()));
		ar.setScan(scn);
		analysisResultList.add(ar);
	    }
	}
	scn.setResults(analysisResultList);
	ScanEntity scanData = scanRepo.save(scn);
	if (scanData != null) {
	    ScansModel queue = new ScansModel();
	    queue.setCommodity(postData.getCommodityId());
	    queue.setPartnerCode(postData.getPartnerCode());
	    queue.setSampleId(postData.getSampleId());
	    queue.setLotId(postData.getLotId());
	    queue.setProdId(postData.getProdId());
	    queue.setScanId(scanData.getId().toString());
	    queue.setDeviceSerialNum(postData.getDeviceSerialNo());
	    queue.setScanStatusId(scanData.getApproval());
	    if (scanData.getApproval() == 0) {
		queue.setScanStatusDesc("Pending");
	    } else if (scanData.getApproval() == 1) {
		queue.setScanStatusDesc("Approved");
	    } else if (scanData.getApproval() == 2) {
		queue.setScanStatusDesc("Rejected");
	    } else {
		queue.setScanStatusDesc("Undefine Status");
	    }
	    //			queue.setFarmerId(scn.getFarmerId());
	    // queue.setUserId(userId.toString());

	    //			queue.setUserId(userId);
	    if (postData.getOperatorId() != null) {
		queue.setOperatorId(postData.getOperatorId());
	    } else {
		queue.setOperatorId(requestContext.getUserId());
	    }
	    adminId = userRepo.getAdminId(Constants.STATUS.DELETED.getId());
	    queue.setAdminId(adminId);
	    if (postData.getClientId() != null) {
		clientId = postData.getClientId();//userRepo.findByCustomerCustomerId(postData.getClientId()).getUserId();
		queue.setClientId(clientId);
	    } else {
		System.out.println(" Notification cannot be sent to client as client id is not present ");
	    }
	    List<Analytics> quality1 = new ArrayList<>();

	    quality1.add(ana);

	    queue.setAnalyticList(quality1);

	    //			if (token != null && !token.isEmpty()) {
	    //				queue.setToken(token);
	    //				
	    //			}
	    //	    notify.send(queue);
	}
	return true;

    }

    public LabResults getLabDetails(String deviceSerialNumber, Long commodityId, Integer pageNumber, Integer limit) {
	List<ScanEntity> scmScanEntities = filterRepo.findAllByFilters(deviceSerialNumber, commodityId, pageNumber,
		limit);
	//		List<Long> commodityIds= new ArrayList<Long>();
	//		List<String>deviceSerialNumbers= new ArrayList<String>();
	//		Long commodity=null;
	//		String deviceSerialNo="";
	//		for (ScanEntity scanEntity : scmScanEntities) {
	//			
	//			commodity=scanEntity.getCommodityId();
	//			deviceSerialNo=scanEntity.getDeviceSerialNo();
	//			commodityIds.add(commodity);
	//			deviceSerialNumbers.add(deviceSerialNo);
	//		}
	//		commodityIds=convertIntoUniqueIds(commodityIds);
	//		deviceSerialNumbers=convertIntoUniqueString(deviceSerialNumbers);
	LabResults labResult = new LabResults();
	Long count = filterRepo.findLabCount(deviceSerialNumber, commodityId);
	labResult.setTotalCount(count);
	return convertLabResultNew(scmScanEntities, labResult);
    }

    private LabResults convertLabResultNew(List<ScanEntity> scmScanEntities, LabResults labResult) {

	List<LabResultModelNewModel> labResults = new ArrayList<LabResultModelNewModel>();

	for (ScanEntity scanEntity : scmScanEntities) {

	    LabResultModelNewModel lResult = new LabResultModelNewModel();
	    lResult.setBatchId(scanEntity.getBatchId());
	    lResult.setCommodityId(scanEntity.getCommodityId());
	    lResult.setCommodityName(scanEntity.getCommodityName());
	    lResult.setScanId(scanEntity.getId());
	    lResult.setWeight(scanEntity.getWeight().doubleValue());
	    lResult.setApproval(scanEntity.getApproval());
	    lResult.setUnit(scanEntity.getQuantityUnit());
	    lResult.setCustomerId(scanEntity.getCommodityId());
	    lResult.setDateDone(scanEntity.getCreatedOn());
	    lResult.setDeviceSerialNo(scanEntity.getDeviceSerialNo());
	    lResult.setCustomerName(custRepo.findByCustomerId(scanEntity.getCustomerId()).getCustomerName());

	    List<Analytics> analytics = new ArrayList<>();
	    for (ScanResultEntity result : scanEntity.getResults()) {

		if (result != null) {
		    Analytics analytic = new Analytics();
		    analytic.setAmountUnit("");
		    analytic.setAnalysisName(result.getAnalysisName());
		    analytic.setResult(Utility.formatDecimal(result.getResult()));
		    analytic.setAnalysisId(String.valueOf(result.getId()));
		    analytic.setTotalAmount(String.valueOf(Utility.formatDecimal(result.getResult())));
		    analytic.setLabResultValue(Utility.formatDecimal(result.getLabResult()));
		    analytics.add(analytic);

		}

	    }
	    lResult.setAnalytics(analytics);
	    labResults.add(lResult);

	}
	labResult.setLabModel(labResults);

	return labResult;
    }

    public Boolean saveLabScanResults(String analytics, Long scanId) {

	ScanEntity scn = scanRepo.getOne(scanId);
	List<ScanResultEntity> scanResult = resultRepo.getScanResults(scanId);

	List<ScanResultEntity> analysisResultList = new ArrayList();
	List<Analytics> quality = new ArrayList();

	Type typeOfT = new TypeToken<Collection<Analytics>>() {
	}.getType();
	quality = new Gson().fromJson(analytics, typeOfT);

	System.out.println(" analytics :  " + analytics);

	System.out.println(" quality : " + quality);

	for (ScanResultEntity scanResultEntity : scanResult) {

	    for (Analytics analytics2 : quality) {

		if (scanResultEntity.getAnalysisName().equalsIgnoreCase(analytics2.getAnalysisName()))
		    if (analytics2.getLabResultValue() != null)
			scanResultEntity.setLabResult(analytics2.getLabResultValue());

		analysisResultList.add(scanResultEntity);

	    }

	}
	scn.setResults(analysisResultList);
	ScanEntity res = scanRepo.save(scn);
	if (res != null)
	    return true;
	else
	    return false;
    }

    public void saveMoistureMeterResult(MoistureMeterResultModel model) throws IMException {
	logger.info("Saving moisture meter result");

	logger.info(" Moisture Meter Request : " + model);
	Long operatorId = null;
	if (moistureMeterRepo.findBySampleIdAndCommodityName(model.getSampleId(),model.getCommodityName()) != null) {
	    throw new IMException(Constants.ErrorCode.SAMPLE_ID_EXISTS, Constants.ErrorMessage.SAMPLE_ID_EXISTS);
	}

	ScanEntity sampleIdExist = scanRepo.findBySampleIdAndCommodityName(model.getSampleId(),model.getCommodityName());
	if (sampleIdExist != null) {
	    ScanResultEntity entityForMoisture = new ScanResultEntity();
	    Boolean isMoistureUpdated = false;
	    if (sampleIdExist != null && sampleIdExist.getResults() != null && sampleIdExist.getResults().size() != 0) {
		for (ScanResultEntity sre : sampleIdExist.getResults()) {
		    if (sre != null && sre.getAnalysisName() != null
			    && sre.getAnalysisName().equalsIgnoreCase("moisturecontent")) {
			sre.setResult(new BigDecimal(model.getMoisture()));
			sre.setScan(sampleIdExist);
			resultRepo.save(sre);
			isMoistureUpdated = true;
		    }
		}
	    }

	    //save moisture
	    if (isMoistureUpdated.booleanValue() != true) {
		entityForMoisture.setAnalysisName("moisturecontent");
		entityForMoisture.setResult(new BigDecimal(model.getMoisture()));
		entityForMoisture.setScan(sampleIdExist);
		resultRepo.save(entityForMoisture);
	    }

	    //save temperature
	    if (model.getTemperature() != null) {
		//	    ScanResultEntity entityForTemp = new ScanResultEntity();
		//	    entityForTemp.setAnalysisName("temperature");
		//	    entityForTemp.setResult(new BigDecimal(model.getTemperature()));
		//	    entityForTemp.setScan(sampleIdExist);
		//	    resultRepo.save(entityForTemp);
	    }

	    sampleIdExist.setTruckNumber(model.getTruckNumber());
	    scanRepo.save(sampleIdExist);
	} else {
	    // do something if sample_id does not exists in scan table
	    // add sample_id to scan table with is_valid flag false

	    saveDataToScanTable(model);
	}

	if (applicationContext != null && applicationContext.getRequestContext() != null
		&& applicationContext.getRequestContext().getUserId() != null) {
	    operatorId = applicationContext.getRequestContext().getUserId();
	}

	CommodityVarietyEntity varietyEntity = null;
//	if (model.getVarietyId() != null && model.getVarietyId() == Constants.CustomVariety.CUSTOM_VARIETY_ID && model.getVarietyName() != null) {
//	    varietyEntity = varietyRepo.getById(model.getVarietyId());
//	    varietyEntity.setVarietyName(model.getVarietyName());
//	} else if(model.getVarietyId() != null){
//	    varietyEntity = varietyRepo.getById(model.getVarietyId());
//	}
	if (model.getVarietyId() != null) {
	    varietyEntity = varietyRepo.getById(model.getVarietyId());
	}
	

	MoistureMeterResult entity = VOToEntityAssembler.convertMoistureMeterResult(model, operatorId, varietyEntity);
	moistureMeterRepo.save(entity);
    }

    private void saveDataToScanTable(MoistureMeterResultModel model) {
	Long operatorId = applicationContext.getRequestContext().getUserId();
	Long customerId = applicationContext.getRequestContext().getCustomerId();
	ScanEntity scn = new ScanEntity();

	DcmCommodity commodityEntity = commodityRepository.getCommodityName(model.getCommodityName());
	if (commodityEntity != null) {
	    scn.setCommodityId(commodityEntity.getId());
	    scn.setCommodityName(commodityEntity.getCommodityName());
	    scn.setCommodityCategoryId(commodityEntity.getDcmCommodityCategory().getId());
	}

	scn.setCreatedOn(Long.valueOf(Instant.now().getEpochSecond() + "000"));
	scn.setSampleId(model.getSampleId());

	CommodityVarietyEntity varietyEntity = varietyRepo.getById(model.getVarietyId());
	if (varietyEntity != null && varietyEntity.getId() == Constants.CustomVariety.CUSTOM_VARIETY_ID) {
	    scn.setVarietyId(varietyEntity.getId());
	    scn.setVarietyName(model.getVarietyName());
	} else if(varietyEntity != null){
	    scn.setVarietyId(varietyEntity.getId());
	    scn.setVarietyName(varietyEntity.getVarietyName());
	}

	DcmDevice deviceEntity = dcmDeviceRepo.findByUserIdAndDcmStatusStatusId(operatorId,
		Constants.STATUS.ACTIVE.getId());
	if (deviceEntity != null) {
	    scn.setInstallatonCenterId(deviceEntity.getScanLocation().getId());
	    scn.setCustomerId(customerId);
	    scn.setDeviceSerialNo(deviceEntity.getSerialNumber());
	    scn.setDeviceType(deviceEntity.getDcmDeviceType().getDeviceTypeDesc());
	    scn.setDeviceTypeId(deviceEntity.getDcmDeviceType().getId());
	}

	scn.setCreatedOnDate(Utility.getCurrentDate());
	scn.setTruckNumber(model.getTruckNumber());
	scn.setOperatorId(operatorId);

	Long stateAdminId = smoRepo.findStateManagerIdByOperatorId(operatorId);
	scn.setStateAdmin(stateAdminId);
	scn.setIsValid(Boolean.FALSE);
	scn.setWeight(BigDecimal.ZERO);

	scanRepo.save(scn);
    }

    public MoistureDataModel getMoistureByOperatorId(Long operatorId2, Integer pageNumber, Integer limit) {

	Sort sort = Sort.by(Direction.DESC, "id");
	Pageable pageable = PageRequest.of(Optional.ofNullable(pageNumber).orElse(Constants.ZERO),
		Optional.ofNullable(limit).orElse(Constants.RECORD_LIMIT), sort);

	Long operatorId = null;
	MoistureDataModel model = new MoistureDataModel();
	List<MoistureMeterResultModel> moistureDataList = new ArrayList<MoistureMeterResultModel>();
	if (operatorId2 != null) {
	    operatorId = operatorId2;
	} else {
	    operatorId = setOperatorId();
	}
	List<MoistureMeterResult> moistureList = moistureMeterRepo.findByClientId(operatorId, pageable);

	Long count = moistureMeterRepo.findCountByClientId(operatorId);
	logger.info("Total count : " + count);

	for (MoistureMeterResult mmr : moistureList) {
	    MoistureMeterResultModel moistureData = new MoistureMeterResultModel();
	    moistureData.setClientId(mmr.getClientId());
	    moistureData.setCommodityName(mmr.getCommodityName());
	    moistureData.setId(mmr.getId());
	    moistureData.setMoisture(mmr.getMoisture());
	    moistureData.setTemperature(mmr.getTemperature());
	    moistureData.setTruckNumber(mmr.getTruckNumber());
	    moistureData.setSampleId(mmr.getSampleId());
	    moistureData.setWeight(mmr.getWeight());
	    moistureData.setToken(mmr.getToken());
	    moistureData.setCreatedOn(mmr.getCreatedOn());
	    moistureDataList.add(moistureData);

	}
	model.setMoistureData(moistureDataList);
	model.setTotalCount(count);
	return model;
    }

    public HistoryMoistureWrapperModel getScanHistoryAndMoisture(Integer pageNumber, Integer limit, Long customerId,
	    Long commodityId, Long ccId, Long dateFrom, Long dateTo, Long farmerId, String deviceType,
	    Long deviceTypeId, String deviceSerialNumber, String userType, Long commCategoryId) {

	Long operatorId = null;
	Long stateAdminId = null;

	if (applicationContext.getRequestContext().getRoles().iterator().next()
		.equalsIgnoreCase(Constants.CustomerType.OPERATOR)) {
	    operatorId = applicationContext.getRequestContext().getUserId();
	} else if (applicationContext.getRequestContext().getRoles().iterator().next()
		.equalsIgnoreCase(Constants.CustomerType.STATE_ADMIN)) {
	    stateAdminId = applicationContext.getRequestContext().getUserId();
	}

	Sort sort = Sort.by(Direction.DESC, "id");
	List<ScanEntity> finalScanEntities = new ArrayList<>();
	
	Pageable pageable = PageRequest.of(Optional.ofNullable(pageNumber).orElse(Constants.ZERO),
		Optional.ofNullable(limit).orElse(Constants.RECORD_LIMIT), sort);

	List<ScanEntity> scanEntities = scanRepo.fetchScanHistoryForMoisture(customerId, commodityId, ccId, dateFrom,
		dateTo, farmerId, deviceType, deviceTypeId, deviceSerialNumber, commCategoryId, operatorId,
		stateAdminId, pageable);
	finalScanEntities.addAll(scanEntities);
	
	if(pageNumber != null){
	    Integer nextPageNumber = pageNumber + 1;
	    Pageable newPageable = PageRequest.of(Optional.ofNullable(nextPageNumber).orElse(Constants.ZERO),
			Optional.ofNullable(limit).orElse(Constants.RECORD_LIMIT), sort);

		List<ScanEntity> nextScanEntities = scanRepo.fetchScanHistoryForMoisture(customerId, commodityId, ccId, dateFrom,
			dateTo, farmerId, deviceType, deviceTypeId, deviceSerialNumber, commCategoryId, operatorId,
			stateAdminId, newPageable);
		finalScanEntities.addAll(nextScanEntities);
	}
	
	Long totalCount=scanRepo.fetchScanHistoryCountForMoisture(customerId, commodityId, ccId, dateFrom,
			dateTo, farmerId, deviceType, deviceTypeId, deviceSerialNumber, commCategoryId, operatorId,
			stateAdminId);

	ArrayList<LinkedHashMap<ScanEntity, MoistureMeterResult>> listOfMaps = new ArrayList<>();
	Map<String, Long> deviceMap = new HashMap<String, Long>();

	if (finalScanEntities != null) {
	    for (ScanEntity scanEntity : finalScanEntities) {
	    	logger.debug("Sample Id : "+scanEntity.getSampleId());
		LinkedHashMap<ScanEntity, MoistureMeterResult> map = new LinkedHashMap<ScanEntity, MoistureMeterResult>();

		MoistureMeterResult moistureMeterEntity=null;
//	    Code below is to handle multiple duplicates in moisture meter table

	    List<MoistureMeterResult> moistureEntityList= new ArrayList<MoistureMeterResult>();
		if (scanEntity.getCommodityName() != null
				&& !scanEntity.getCommodityName().trim().equalsIgnoreCase("")) {
			moistureEntityList = moistureMeterRepo.findBySampleIdAndCommodityName(scanEntity.getSampleId(),
					scanEntity.getCommodityName());

			   if(moistureEntityList !=null && !moistureEntityList.isEmpty() && moistureEntityList.size()>0) {

				   Boolean escapeCheck=Boolean.TRUE;
					for(MoistureMeterResult moisture:moistureEntityList) {
						if(moisture !=null && escapeCheck && moisture.getMoisture() !=null) {
							moistureMeterEntity=moisture;
							escapeCheck=Boolean.FALSE;
						}

					}
			   }


			map.put(scanEntity, moistureMeterEntity);
		}else if(scanEntity.getCommodityName() == null
				|| scanEntity.getCommodityName().trim().equalsIgnoreCase("")) {
		DcmCommodity commodity=commodityRepository.getOne(scanEntity.getCommodityId());
		moistureEntityList = moistureMeterRepo.findBySampleIdAndCommodityName(scanEntity.getSampleId(),
				commodity.getCommodityName());

		   if(moistureEntityList !=null && !moistureEntityList.isEmpty() && moistureEntityList.size()>0) {

			   Boolean escapeCheck=Boolean.TRUE;
				for(MoistureMeterResult moisture:moistureEntityList) {
					if(moisture !=null && escapeCheck && moisture.getMoisture() !=null) {
						moistureMeterEntity=moisture;
						escapeCheck=Boolean.FALSE;
					}

				}
		   }

		map.put(scanEntity, moistureMeterEntity);
		}
		listOfMaps.add(map);

		DcmDevice deviceEntity = dcmDeviceRepo.findBySerialNumberIgnoreCase(scanEntity.getDeviceSerialNo());
		deviceMap.put(deviceEntity.getSerialNumber(), deviceEntity.getId());
	    }
	    return moistureHistoryResponse.convertHistoryForMoistureData(listOfMaps, deviceMap,totalCount, limit);
	}
	return null;
    }

    
    public Boolean isImageExist(String imageId) {
	String finalURL = "";
	Boolean imageExist=false;
	try {
	    finalURL = getFileUrl(imageId);
	    final HttpURLConnection httpConnection = getHttpConnection(finalURL);
	    int responseCode = httpConnection.getResponseCode();
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	    imageExist=true;
	    logger.debug("ImageExist True loop ", imageExist);
	    }
	  
	} catch (IOException e) {
	    logger.error("FileParams are not set for image with Id : {}", imageId);
	    e.printStackTrace();
	}
	  return imageExist;
    }
    
    public List<ImageModel> downloadImagesForHistoryAPI(Long id) {
    	Map<Integer, String> returnMap = new HashMap<>();
    	String imageUniqueId = "";
    	ScanEntity physicalScan = scanRepo.getById(id);
    	if (physicalScan != null && physicalScan.getImageUniqueId() != null
    		&& !physicalScan.getImageUniqueId().isEmpty()) {
    	    imageUniqueId = physicalScan.getImageUniqueId();
    	} else {
    	    imageUniqueId = "16018953367WELK";
    	}
    	logger.debug("ImageUniquedIds : {}", imageUniqueId);
    	String[] imageIds = imageUniqueId.split(":");
    	List<ImageModel> modelList = new ArrayList<>();
    	Arrays.stream(imageIds).forEach(a -> {
    	    ImageModel image = new ImageModel();
    	    setFileParamsForHistoryAPI(image, a);
    	    if (image.getImageId() != null) {
    		modelList.add(image);
    	    }
    	});
    	return modelList;
        }
    
    /**
     * Set FIle Params
     *
     * @param image
     * @param imageId
     */
    private void setFileParamsForHistoryAPI(ImageModel image, String imageId) {
	String fileName = "";
	String finalURL = "";
	try {
	    finalURL = getFileUrl(imageId);
	    final HttpURLConnection httpConnection = getHttpConnection(finalURL);
	    int responseCode = httpConnection.getResponseCode();
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	    fileName = getFileName(httpConnection, finalURL);
	    
	    image.setImageId(imageId);
		image.setFileName(fileName);
		image.setFileExtension(".jpg");
		image.setUrl(finalURL);
		image.setIsImageExists(Boolean.TRUE);
		}
	    else {
	    	if(imageId !=null)
	    	image.setImageId(imageId);
	    	image.setIsImageExists(Boolean.FALSE);
	    }
	} catch (IOException e) {
	    logger.error("FileParams are not set for image with Id : {}", imageId);
	    e.printStackTrace();
	}
	
    }

    public ResponseEntity htmlToPdf(Long scanId) throws FileNotFoundException, IOException {
	 String HTML = "<h1>Hello</h1>" + "<p>How are you?</p>";
	
	 //using html in String
    	//HtmlConverter.convertToPdf(HTML, new FileOutputStream("string-to-pdf.pdf"));
    	System.out.println( "PDF created using string" );
    	
    	//from html file
    	//HtmlConverter.convertToPdf(new FileInputStream("index.html"), new FileOutputStream("index-to-pdf.pdf"));
    	System.out.println( "PDF created using html file" );
	return null;
    }
    
    
    
    
    
    // Multiple different analytical parameters handled 
    
    public ScanModel regularExpressionMatcher(ScanEntity scanEntity,Map<Long, List<String>> commoditiesAnalyticsMap) throws IMException, JsonParseException, JsonMappingException, IOException {
    	ScanModel scanModel = new ScanModel();
    	if (scanEntity != null) {

    	    ObjectMapper mapper = new ObjectMapper();
    	    Map<String, BigDecimal> map = null;
    	    if (scanEntity.getDensity() != null) {
    		map = mapper.readValue(scanEntity.getDensity(), Map.class);
    	    }

    	    
    	    Optional.ofNullable(scanEntity.getDeviceSerialNo()).ifPresent(scanModel::setDeviceSerialNo);
    	    Optional.ofNullable(scanEntity.getLotId()).ifPresent(scanModel::setLotId);
    	    Optional.ofNullable(scanEntity.getCommodityId()).ifPresent(scanModel::setCommodityId);
    	    Optional.ofNullable(scanEntity.getScanByUserCode()).ifPresent(scanModel::setScanByUserCode);
    	    Optional.ofNullable(scanEntity.getLocation()).ifPresent(scanModel::setLocation);
    	    Optional.ofNullable(scanEntity.getVendorCode()).ifPresent(scanModel::setVendorCode);
    	    Optional.ofNullable(scanEntity.getQuantity()).ifPresent(scanModel::setQuantity);
    	    Optional.ofNullable(scanEntity.getFarmerCode()).ifPresent(scanModel::setFarmerCode);
    	    Optional.ofNullable(scanEntity.getBatchId()).ifPresent(scanModel::setBatchId);
    	    Optional.ofNullable(scanEntity.getSampleId()).ifPresent(scanModel::setSampleId);
    	    Optional.ofNullable(scanEntity.getVarietyId()).ifPresent(scanModel::setVarietyId);
    	    Optional.ofNullable(scanEntity.getId()).ifPresent(scanModel::setId);
    	    Optional.ofNullable(scanEntity.getDeviceType()).ifPresent(scanModel::setDeviceType);
    	    scanModel.setMessage(setScanMessage(scanEntity));
    	    scanModel.setApproval(scanEntity.getApproval());
    	    if (scanEntity.getApproval() == 0) {
    		scanModel.setApprovalDesc("Pending");
    	    } else if (scanEntity.getApproval() == 1) {
    		scanModel.setApprovalDesc("Approved");
    	    }

    	    else if (scanEntity.getApproval() == 2) {
    		scanModel.setApprovalDesc("Rejected");
    	    } else {
    		scanModel.setApprovalDesc("Undefined");
    	    }
    	    if (scanEntity.getStatusChangedOn() != null)
    		Optional.ofNullable(Utility.formatLocalMonthDateTimeToString(scanEntity.getStatusChangedOn()))
    			.ifPresent(scanModel::setMsgTimeStamp);
    	    //
    	    Optional.ofNullable(scanEntity.getDeviceSerialNo()).ifPresent(scanModel::setDeviceSerialNo);
    	    Optional.ofNullable(scanEntity.getInstallatonCenterId()).ifPresent(scanModel::setInstCenterTypeId);
    	    Optional.ofNullable(scanEntity.getUserId()).ifPresent(scanModel::setUserId);
    	    Optional.ofNullable(scanEntity.getCommodityName()).ifPresent(scanModel::setCommodityName);
    	    WeightConverterModel weightDetails = new WeightConverterModel();
    	    weightDetails = Utility.fetchWeightConverter(scanEntity.getWeight(), scanEntity.getQuantityUnit());
    	    System.out.println(
    		    "********* Weight : " + weightDetails.getWeight() + " Weight unit : " + weightDetails.getUnit());
    	    scanModel.setWeight(weightDetails.getWeight());
    	    scanModel.setQuantityUnit(weightDetails.getUnit());

    	    //            Optional.ofNullable(scanEntity.getQuantityUnit()).ifPresent(scanModel::setQuantityUnit);
    	    //            Optional.ofNullable(scanEntity.getWeight()).ifPresent(scanModel::setWeight);

    	    Optional.ofNullable(scanEntity.getAreaCovered()).ifPresent(scanModel::setAreaCovered);
    	    
    	 List<Analytics> quality = new ArrayList();
 	    Analytics grain_count = new Analytics();
// 	    if (scanEntity.getCommodityId() == 4L) {
// 		for (ScanResultEntity result : scanEntity.getResults()) {
// 		    Analytics ana = new Analytics();
// 		    ana.setAmountUnit("");
// 		    ana.setAnalysisName(result.getAnalysisName());
// 		    ana.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
// 		    ana.setAnalysisId(String.valueOf(result.getId()));
// 		    ana.setTotalAmount(
// 			    String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
// 		    quality.add(ana);
// 		}
// 	    } else {
 	    
 	    List<String >analyticsNames=commoditiesAnalyticsMap.get(scanEntity.getCommodityId());
 	    
 		Boolean moisturePresent = false;
 		BigDecimal moisture = null;
 		for (String analyticsN : analyticsNames) {
			
 		for (ScanResultEntity result : scanEntity.getResults()) {
 			
 			 Matcher m = Pattern.compile(analyticsN)
 				     .matcher(result.getAnalysisName());

 		    if (result.getAnalysisName().equals("grain_count")) {
 			Analytics analytics = new Analytics();
 			analytics.setAmountUnit("");
 			analytics.setAnalysisName(result.getAnalysisName());
 			analytics.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
 			analytics.setAnalysisId(String.valueOf(result.getId()));
 			analytics.setTotalAmount(
 				String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
 			grain_count = analytics;
 			//			quality.add(0, analytics);
 		    } else if (!result.getAnalysisName().equals("grain_count")
 			    && !result.getAnalysisName().equals("moisturecontent")
 			    && !result.getAnalysisName().equals("moisture")) {
 			Analytics ana = new Analytics();
 			System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
 			ana.setAmountUnit("%");
 			ana.setAnalysisName(result.getAnalysisName());
 			ana.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
 			ana.setAnalysisId(String.valueOf(result.getId()));
 			ana.setTotalAmount(
 				String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));

 			if (map != null && map.containsKey(result.getAnalysisName())) {
 			    ana.setByDensityResult(Utility
 				    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
 			}
 			quality.add(ana);
 		    } else if (result.getAnalysisName().equals("moisture")
 			    || result.getAnalysisName().equals("moisturecontent")) {
 			if (result.getAnalysisName().equals("moisture")) {
 			    moisture = result.getResult();
 			    moisturePresent = true;
 			} else if (result.getAnalysisName().equals("moisturecontent")
 				&& moisturePresent.booleanValue() == false) {
 			    moisture = result.getResult();
 			}

 		    }

 		}
    	}
 		quality.add(setMoisture(moisture));
// 	    }
 	    logger.info(" Analytics : " + quality);
 	    Collections.reverse(quality);
 	    logger.info(" Analytics reverse list  :  " + quality);
 	    if (grain_count != null && grain_count.getAnalysisName() != null && !grain_count.getAnalysisName().isEmpty()
 		    && grain_count.getAnalysisName().equalsIgnoreCase("grain_count")) {
 		quality.add(0, grain_count);
 	    }
 	    scanModel.setQuality(removeDuplicates(quality));
 	    //	    scanModel.setQuality(quality.stream().distinct().collect(Collectors.toList()));
 	    //	    scanModel.setQuality(quality);

 	    // scanModel.setFarmer(farmerDetailModel);
    	}
 	    return scanModel;
    	
    }
    
    private  String setScanMessage(ScanEntity scanEntity) {
    	String message = "Status last";
    	LocalDateTime statusChangedOn = scanEntity.getStatusChangedOn();
    	if (statusChangedOn == null) {
    	    message = message + " created on:";
    	    final Long creationTimeStamp = scanEntity.getCreatedOn();
    	    statusChangedOn = Utility.millsToLocalDateTime(creationTimeStamp);
    	} else {
    	    message = message + " updated on:";
    	}
    	final String statusUpdateTime = Utility.formatDateToString(statusChangedOn);
    	if (!StringUtils.isEmpty(scanEntity.getMessage())) {
    	    message = message + statusUpdateTime + " with message :" + scanEntity.getMessage();
    	} else {
    	    message = message + statusUpdateTime;
    	}
    	return message;
        }
    
    private  Analytics setMoisture(BigDecimal moisture) {
	Analytics ana = new Analytics();
	if (moisture != null) {
	    ana.setAmountUnit("%");
	    ana.setAnalysisName("moisturecontent");
	    ana.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(moisture)));
	    ana.setTotalAmount(String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(moisture))));
	}
	return ana;
    }
    
    public  <T> List<T> removeDuplicates(List<T> list) {
	Set<T> set = new LinkedHashSet<>();
	set.addAll(list);
	list.clear();
	list.addAll(set);
	return list;
    }

    public Boolean isDuplicateSampleId(String sampleId, Long commodityId) {
	logger.info("inside ScanService.isDuplicateSampleId method");
	
	if(sampleId != null && !sampleId.isEmpty() && commodityId != null){
	    ScanEntity scanEntity =  scanRepo.findBySampleIdAndCommodityIdAndIsValid(sampleId, commodityId,Boolean.TRUE);
	    
	    if(scanEntity != null) {
	    	if(scanEntity.getIsValid()==Boolean.FALSE) {
	    		return false;
	    	}else if (scanEntity.getIsValid()==Boolean.TRUE) {
		return true;
	    	}
	    } else
		return false;
	}
	return null;
    }

	public Boolean updateImageStatus(String sampleId, String imageUniqueId) throws IMException {
		logger.info("inside ScanService.updateImageStatus method");
		Boolean isImageValid=Boolean.FALSE;
		List<ScanEntity> scanEntityList =  scanRepo.findBySampleIdAndImageUniqueIdAndIsValid(sampleId, imageUniqueId,Boolean.TRUE);

		if(scanEntityList !=null && scanEntityList.size() >0) {

			if(scanEntityList.size()>1) {
				throw new IMException("12005", "Duplicate Scan records exists for the given sample_id : "+sampleId+" and image_unique_id : "+imageUniqueId);
			}

			for (ScanEntity scanEntity : scanEntityList) {

				if (scanEntity.getIsImageUrlValid() !=null && scanEntity.getIsImageUrlValid()==Boolean.FALSE) {
					scanEntity.setIsImageUrlValid(Boolean.TRUE);


				}else if(scanEntity.getIsImageUrlValid() !=null && scanEntity.getIsImageUrlValid()==Boolean.TRUE) {
					// Do Nothing
				}
				scanEntity=scanRepo.save(scanEntity);
				isImageValid=scanEntity.getIsImageUrlValid();
			}


		}
		return isImageValid;

	}

   
    
}
