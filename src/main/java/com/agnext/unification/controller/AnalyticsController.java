package com.agnext.unification.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agnext.unification.model.AnalyticsAvgMinMaxModel;
import com.agnext.unification.model.AnalyticsCollectionModel;
import com.agnext.unification.model.ClientAvgMinMaxModel;
import com.agnext.unification.model.ClientDetailModel;
import com.agnext.unification.model.CollectionDetailsModel;
import com.agnext.unification.model.CollectionDetailsUniqueModel;
import com.agnext.unification.model.CommodityAnalyticModel;
import com.agnext.unification.model.CommodityDeviceDropDownModel;
import com.agnext.unification.model.FarmerDetailsModel;
import com.agnext.unification.model.ScanCountModel;
import com.agnext.unification.model.ScanModel;
import com.agnext.unification.model.ScanResponseModel;
import com.agnext.unification.model.TotalCollectionsModel;
import com.agnext.unification.model.UserLinkModel;
import com.agnext.unification.model.WarehouseDetailsModel;
import com.agnext.unification.service.AnalyticsService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Analysis Controller
 *
 */
@RestController
@RequestMapping(value = "api/analytics", produces = "application/json")
public class AnalyticsController {

	private static Logger logger = LoggerFactory.getLogger(AnalyticsController.class);

	@Autowired
	AnalyticsService analyticsService;

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * It get Collection Details .
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@GetMapping("/quantity")
	public ResponseEntity<?> getCollectionDetails(@RequestParam(value = "p", required = false) Integer pageNumber,
			@RequestParam(value = "l", required = false) Integer limit,
			@RequestParam(value = "client_id", required = false) Long customerId,
			@RequestParam(value = "commodity_id", required = false) Long commodityId,
			@RequestParam(value = "inst_center_id", required = false) Long ccId,
			@RequestParam(value = "date_from", required = false) Long dateFrom,
			@RequestParam(value = "date_to", required = false) Long dateTo,
			@RequestParam(value = "device_type", required = false) String deviceType,
			@RequestParam(value = "device_type_id", required = false) Long deviceTypeId,
			@RequestParam(value = "device_serial_number", required = false) String deviceSerialNo,
			@RequestParam(value = "state_id", required = false) Long stateId,
			@RequestParam(value = "category_id", required = false) Long categoryId,
			@RequestParam(value = "district_name", required = false) String districtName) {

		logger.info("AnalyticsController.getCollectionDetails ");

		AnalyticsAvgMinMaxModel response;
		try {
			response = analyticsService.getCollection(pageNumber, limit, customerId, commodityId, ccId, dateFrom,
					dateTo, deviceType, deviceTypeId, deviceSerialNo, stateId,categoryId, districtName);

			if (response == null)
				return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("AnalyticsController.getCollectionDetails exception is ", e.getMessage());
			return analyticsService.handleException(e);
		}

	}

	@GetMapping("/quantity/collections")
	public ResponseEntity<?> getCollectionDetailsList(@RequestParam(value = "p", required = false) Integer pageNumber,
			@RequestParam(value = "l", required = false) Integer limit,
			@RequestParam(value = "client_id", required = false) Long customerId,
			@RequestParam(value = "commodity_id", required = false) Long commodityId,
			@RequestParam(value = "inst_center_id", required = false) Long ccId,
			@RequestParam(value = "region_id", required = false) Long regionId,
			@RequestParam(value = "date_from", required = false) Long dateFrom,
			@RequestParam(value = "date_to", required = false) Long dateTo,
			@RequestParam(value = "device_type", required = false) String deviceType,
			@RequestParam(value = "device_type_id", required = false) Long deviceTypeId,
			@RequestParam(value = "device_serial_number", required = false) String deviceSerialNo,
			@RequestParam(value = "state_id", required = false) Long stateId,
			@RequestParam(value = "category_id", required = false) Long categoryId,
			@RequestParam(value = "district_name", required = false) String districtName) {

		logger.info("AnalyticsController.getCollectionDetailsList ");

		List<AnalyticsCollectionModel> response;
		try {
			response = analyticsService.getCollectionList(pageNumber, limit, customerId, commodityId, ccId, dateFrom,
					dateTo, regionId, deviceType, deviceTypeId, deviceSerialNo, stateId, categoryId, districtName);

			if (response == null || response.size() == 0)
				return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("AnalyticsController.getCollectionDetailsList exception is ", e.getMessage());
			return analyticsService.handleException(e);
		}

	}

	@GetMapping("/collections-time")
	public ResponseEntity<?> getCollectionDetailsListOverTime(
			@RequestParam(value = "p", required = false) Integer pageNumber,
			@RequestParam(value = "l", required = false) Integer limit,
			@RequestParam(value = "client_id", required = false) Long customerId,
			@RequestParam(value = "commodity_id", required = false) Long commodityId,
			@RequestParam(value = "inst_center_id", required = false) Long instCenterId,
			@RequestParam(value = "date_from", required = false) Long dateFrom,
			@RequestParam(value = "date_to", required = false) Long dateTo,
			@RequestParam(value = "device_type", required = false) String deviceType,
			@RequestParam(value = "device_type_id", required = false) Long deviceTypeId,
			@RequestParam(value = "device_serial_number", required = false) String deviceSerialNo,
			@RequestParam(value = "state_id", required = false) Long stateId,
			@RequestParam(value = "category_id", required = false) Long categoryId,
			@RequestParam(value = "district_name", required = false) String districtName) {

		logger.info("AnalyticsController.getCollectionDetailsListOverTime ");

		List<ScanCountModel> response;
		try {
			response = analyticsService.getCollectionDetailsListOverTime(pageNumber, limit, customerId, commodityId,
					instCenterId, dateFrom, dateTo, deviceType, deviceTypeId, deviceSerialNo, stateId,categoryId,districtName);
			if (response == null || response.size() == 0)
				return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("AnalyticsController.getCollectionDetailsListOverTime exception is ", e.getMessage());
			return analyticsService.handleException(e);
		}

	}

	// @GetMapping("/center-region")
	// public ResponseEntity<?> getCenterAndRegionCollections(
	// @RequestParam(value = "p", required = false) Integer pageNumber,
	// @RequestParam(value = "l", required = false) Integer limit,
	// @RequestParam(value = "customer_id", required = false) Long customerId,
	// @RequestParam(value = "commodity_id", required = false) Long commodityId,
	// @RequestParam(value = "inst_center_id", required = false) Long instCenterId,
	// @RequestParam(value = "date_from", required = false) Long dateFrom,
	// @RequestParam(value = "date_to", required = false) Long dateTo,
	// @RequestParam(value = "device_type", required = false) String deviceType,
	// @RequestParam(value = "device_type_id", required = false) Long deviceTypeId,
	// @RequestParam(value = "device_serial_number", required = false) String
	// deviceSerialNo) {
	//
	// logger.info("AnalyticsController.getCenterAndRegionCollections ");
	//
	// List<ScanCountModel> response;
	// try {
	// response = analyticsService.getCenterAndRegionCollections(pageNumber, limit,
	// customerId, commodityId,
	// instCenterId, dateFrom, dateTo,deviceType,deviceTypeId,deviceSerialNo);
	//
	// if(response == null || response.size()==0)
	// return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	//
	// return new ResponseEntity<>(response, HttpStatus.OK);
	// } catch (Exception e) {
	// logger.error("AnalyticsController.getCenterAndRegionCollections exception is
	// ", e.getMessage());
	// return analyticsService.handleException(e);
	// }
	//
	// }

	/**
	 * It get Collection Details for Arya's clients .
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@GetMapping("/clients")
	public ResponseEntity<?> getClientsDetails(@RequestParam(value = "p", required = false) Integer pageNumber,
			@RequestParam(value = "l", required = false) Integer limit,
			@RequestParam(value = "client_id", required = false) Long customerId,
			@RequestParam(value = "commodity_id", required = false) Long commodityId,
			@RequestParam(value = "date_from", required = false) Long dateFrom,
			@RequestParam(value = "date_to", required = false) Long dateTo,
			@RequestParam(value = "inst_center_id", required = false) Long instCenterId,
			@RequestParam(value = "device_type", required = false) String deviceType,
			@RequestParam(value = "device_type_id", required = false) Long deviceTypeId) {

		logger.info("AnalyticsController.getClientsDetails ");

		ClientAvgMinMaxModel response;
		try {
			response = analyticsService.getClientsDetails(pageNumber, limit, customerId, commodityId, dateFrom, dateTo,
					instCenterId, deviceType, deviceTypeId);

			if (response == null)
				return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("AnalyticsController.getClientsDetails exception is ", e.getMessage());
			return analyticsService.handleException(e);
		}

	}

	// @GetMapping("/farmers/quality")
	// public ResponseEntity<?> getFarmerDetails(@RequestParam(value = "p", required
	// = false) Integer pageNumber,
	// @RequestParam(value = "l", required = false) Integer limit,
	// @RequestParam(value = "customer_id", required = false) Long customerId,
	// @RequestParam(value = "commodity_id", required = false) Long commodityId,
	// @RequestParam(value = "date_from", required = false) Long dateFrom,
	// @RequestParam(value = "date_to", required = false) Long dateTo,
	// @RequestParam(value = "analysis_code", required = true) String analysisCode,
	// @RequestParam(value = "inst_center_id", required = false) Long instCenterId,
	// @RequestParam(value = "device_type", required = false) String deviceType,
	// @RequestParam(value = "device_type_id", required = false) Long deviceTypeId)
	// {
	//
	// logger.info("AnalyticsController.getFarmerDetails ");
	//
	// ClientAvgMinMaxModel response;
	// try {
	// response = analyticsService.getFarmerQualityDetails(pageNumber, limit,
	// customerId, commodityId, dateFrom, dateTo,
	// instCenterId,analysisCode,deviceType,deviceTypeId);
	// if(response == null )
	// return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	//
	// return new ResponseEntity<>(response, HttpStatus.OK);
	// } catch (Exception e) {
	// logger.error("AnalyticsController.getFarmerDetails exception is ",
	// e.getMessage());
	// return analyticsService.handleException(e);
	// }
	//
	// }
	/**
	 * It get Collection Details for Clients .
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@GetMapping("/client/list")
	public ResponseEntity<?> getClientList(@RequestParam(value = "p", required = false) Integer pageNumber,
			@RequestParam(value = "l", required = false) Integer limit,
			@RequestParam(value = "client_id", required = false) Long customerId,
			@RequestParam(value = "commodity_id", required = false) Long commodityId,
			@RequestParam(value = "date_from", required = false) Long dateFrom,
			@RequestParam(value = "date_to", required = false) Long dateTo,
			@RequestParam(value = "inst_center_id", required = false) Long instCenterId,
			@RequestParam(value = "device_type", required = false) String deviceType,
			@RequestParam(value = "device_type_id", required = false) Long deviceTypeId) {

		logger.info("AnalyticsController.getClientList ");

		List<ClientDetailModel> response;
		try {
			response = analyticsService.getClientList(pageNumber, limit, customerId, commodityId, dateFrom, dateTo,
					instCenterId, deviceType, deviceTypeId);
			if (response == null || response.size() == 0)
				return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("AnalyticsController.getClientList exception is ", e.getMessage());
			return analyticsService.handleException(e);
		}

	}

	// @GetMapping("/farmers/list/quality")
	// public ResponseEntity<?> getFarmerListQuality(@RequestParam(value = "p",
	// required = false) Integer pageNumber,
	// @RequestParam(value = "l", required = false) Integer limit,
	// @RequestParam(value = "customer_id", required = false) Long customerId,
	// @RequestParam(value = "commodity_id", required = false) Long commodityId,
	// @RequestParam(value = "date_from", required = false) Long dateFrom,
	// @RequestParam(value = "date_to", required = false) Long dateTo,
	// @RequestParam(value = "analysis_code", required = true) String analysisCode,
	// @RequestParam(value = "inst_center_id", required = false) Long instCenterId,
	// @RequestParam(value = "device_type", required = false) String deviceType,
	// @RequestParam(value = "device_type_id", required = false) Long deviceTypeId)
	// {
	//
	// logger.info("AnalyticsController.getFarmerList ");
	//
	// List<ClientDetailModel> response;
	// try {
	// response = analyticsService.getFarmerListQuality(pageNumber, limit,
	// customerId, commodityId, dateFrom, dateTo,
	// instCenterId,analysisCode,deviceType,deviceTypeId);
	//
	// if(response == null || response.size()==0 )
	// return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	//
	// return new ResponseEntity<>(response, HttpStatus.OK);
	// } catch (Exception e) {
	// logger.error("AnalyticsController.getFarmerList exception is ",
	// e.getMessage());
	// return analyticsService.handleException(e);
	// }
	//
	// }
	@GetMapping("/increment-decrement")
	public ResponseEntity<?> getIncreamentDecrementInCollections(
			@RequestParam(value = "p", required = false) Integer pageNumber,
			@RequestParam(value = "l", required = false) Integer limit,
			@RequestParam(value = "client_id", required = false) Long customerId,
			@RequestParam(value = "commodity_id", required = false) Long commodityId,
			@RequestParam(value = "inst_center_id", required = false) Long instCenterId,
			@RequestParam(value = "date_from", required = false) Long dateFrom,
			@RequestParam(value = "date_to", required = false) Long dateTo,
			@RequestParam(value = "device_type", required = false) String deviceType,
			@RequestParam(value = "device_type_id", required = false) Long deviceTypeId,
			@RequestParam(value = "device_serial_number", required = false) String deviceSerialNo,
			@RequestParam(value = "state_id", required = false) Long stateId,
			@RequestParam(value = "category_id", required = false) Long categoryId,
			@RequestParam(value = "district_name", required = false) String districtName) {

		logger.info("AnalyticsController.getIncreamentDecrementInCollections ");

		ScanCountModel response;
		try {
			response = analyticsService.getIncreamentDecrementInCollections(pageNumber, limit, customerId, commodityId,
					instCenterId, dateFrom, dateTo, deviceType, deviceTypeId, deviceSerialNo, stateId,categoryId,districtName);

			if (response == null || response.getGraphData().size() <= 0)
				return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("AnalyticsController.getIncreamentDecrementInCollections exception is ", e.getMessage());
			return analyticsService.handleException(e);
		}

	}

	// @GetMapping("/payment-overtime")
	// public ResponseEntity<?> getPaymentOverTime(
	//
	// @RequestParam(value = "customer_id", required = false) Long customerId,
	// @RequestParam(value = "commodity_id", required = false) Long commodityId,
	// @RequestParam(value = "inst_center_id", required = false) Long instCenterId,
	// @RequestParam(value = "region_id", required = false) Long regionId,
	// @RequestParam(value = "date_from", required = false) Long dateFrom,
	// @RequestParam(value = "date_to", required = false) Long dateTo,
	// @RequestParam(value = "device_type", required = false) String deviceType,
	// @RequestParam(value = "device_type_id", required = false) Long deviceTypeId)
	// {
	//
	// logger.info("AnalyticsController.getPaymentOverTime ");
	//
	// List<ScanCountModel> response;
	// try {
	// response = analyticsService.getPaymentOverTime(customerId, commodityId,
	// instCenterId, dateFrom, dateTo,
	// regionId,deviceType,deviceTypeId);
	//
	// if(response == null || response.size()==0 )
	// return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	//
	// return new ResponseEntity<>(response, HttpStatus.OK);
	// } catch (Exception e) {
	// logger.error("AnalyticsController.getPaymentOverTime exception is ",
	// e.getMessage());
	// return analyticsService.handleException(e);
	// }
	//
	// }
	//
	// @GetMapping("/payment-chart")
	// public ResponseEntity<?> getPaymentChartData(
	//
	// @RequestParam(value = "customer_id", required = false) Long customerId,
	// @RequestParam(value = "commodity_id", required = false) Long commodityId,
	// @RequestParam(value = "inst_center_id", required = false) Long instCenterId,
	// @RequestParam(value = "region_id", required = false) Long regionId,
	// @RequestParam(value = "date_from", required = false) Long dateFrom,
	// @RequestParam(value = "date_to", required = false) Long dateTo,
	// @RequestParam(value = "device_type", required = false) String deviceType,
	// @RequestParam(value = "device_type_id", required = false) Long deviceTypeId)
	// {
	//
	// logger.info("AnalyticsController.getPaymentChartData ");
	//
	// List<ScanCountModel> response;
	// try {
	// response = analyticsService.getPaymentChartData(customerId, commodityId,
	// instCenterId, dateFrom, dateTo,
	// regionId,deviceType,deviceTypeId);
	// if(response == null || response.size()==0 )
	// return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	//
	// return new ResponseEntity<>(response, HttpStatus.OK);
	// } catch (Exception e) {
	// logger.error("AnalyticsController.getPaymentChartData exception is ",
	// e.getMessage());
	// return analyticsService.handleException(e);
	// }
	//
	// }
	//
	// @GetMapping("/payment-list")
	// public ResponseEntity<?> getPaymentList(
	// @RequestParam(value = "p", required = false) Integer pageNumber,
	// @RequestParam(value = "l", required = false) Integer limit,
	// @RequestParam(value = "customer_id", required = false) Long customerId,
	// @RequestParam(value = "commodity_id", required = false) Long commodityId,
	// @RequestParam(value = "inst_center_id", required = false) Long instCenterId,
	// @RequestParam(value = "region_id", required = false) Long regionId,
	// @RequestParam(value = "date_from", required = false) Long dateFrom,
	// @RequestParam(value = "date_to", required = false) Long dateTo,
	// @RequestParam(value = "device_type", required = false) String deviceType,
	// @RequestParam(value = "device_type_id", required = false) Long deviceTypeId)
	// {
	//
	// logger.info("AnalyticsController.getPaymentList ");
	//
	// List<PaymentListModel> response;
	// try {
	// response = analyticsService.getPaymentList(pageNumber, limit, customerId,
	// commodityId,
	// instCenterId, dateFrom, dateTo, regionId,deviceType,deviceTypeId);
	// if(response == null || response.size()==0 )
	// return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	//
	// return new ResponseEntity<>(response, HttpStatus.OK);
	// } catch (Exception e) {
	// logger.error("AnalyticsController.getPaymentList exception is ",
	// e.getMessage());
	// return analyticsService.handleException(e);
	// }
	//
	// }

	/**
	 * It get Collection Details modified .
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@GetMapping("/quantity-details")
	public ResponseEntity<?> getCollectionDetailsModified(
			@RequestParam(value = "p", required = false) Integer pageNumber,
			@RequestParam(value = "l", required = false) Integer limit,
			@RequestParam(value = "client_id", required = false) Long customerId,
			@RequestParam(value = "commodity_id", required = false) Long commodityId,
			@RequestParam(value = "inst_center_id", required = false) Long instCenterId,
			@RequestParam(value = "inst_center_type_id", required = false) Long instCenterTypeId,
			@RequestParam(value = "date_from", required = false) Long dateFrom,
			@RequestParam(value = "date_to", required = false) Long dateTo,
			@RequestParam(value = "device_serial_number", required = false) String deviceSerialNo,
			@RequestParam(value = "commodity_category_id", required = false) Long commodityCategoryId,
			@RequestParam(value = "device_type", required = false) String deviceType,
			@RequestParam(value = "device_type_id", required = false) Long deviceTypeId,
			@RequestParam(value = "search_keyword", required = false) String keyword,
			@RequestParam(value = "state_id", required = false) Long stateId) {

		logger.info("AnalyticsController.getCollectionDetailsModified ");

		CollectionDetailsModel response;
		try {
			response = analyticsService.getCollectionDetailsModified(pageNumber, limit, customerId, commodityId,
					instCenterId, dateFrom, dateTo, instCenterTypeId, commodityCategoryId, deviceType, deviceTypeId,
					deviceSerialNo, keyword, stateId);
			if (response == null) {
				return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("AnalyticsController.getCollectionDetailsModified exception is ", e.getMessage());
			return analyticsService.handleException(e);
		}

	}

	/**
	 * It get Collection Details for farmers .
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@GetMapping("/client/new")
	public ResponseEntity<?> getClientDetailById(@RequestParam(value = "p", required = false) Integer pageNumber,
			@RequestParam(value = "l", required = false) Integer limit,
			@RequestParam(value = "client_id", required = false) Long customerId,
			@RequestParam(value = "commodity_id", required = false) Long commodityId,
			@RequestParam(value = "date_from", required = false) Long dateFrom,
			@RequestParam(value = "date_to", required = false) Long dateTo,
			@RequestParam(value = "inst_center_id", required = false) Long instCenterId,
			@RequestParam(value = "farmer_id", required = false) Long farmerId,
			@RequestParam(value = "analysis_code", required = true) String analysisCode,
			@RequestParam(value = "device_type", required = false) String deviceType,
			@RequestParam(value = "device_type_id", required = false) Long deviceTypeId) {

		logger.info("AnalyticsController.getClientDetailById ");

		FarmerDetailsModel response;
		try {
			response = analyticsService.getClientDetailById(pageNumber, limit, customerId, commodityId, dateFrom,
					dateTo, instCenterId, farmerId, analysisCode, deviceType, deviceTypeId);

			if (response == null)
				return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("AnalyticsController.getClientDetailById exception is ", e.getMessage());
			return analyticsService.handleException(e);
		}

	}

	// /**
	// * It get Collection Details modified .
	// *
	// * @param request the request
	// * @return the response entity
	// */
	//
	@GetMapping("/quantity-details-id")
	public ResponseEntity<?> getCollectionDetailsByIdModified(
			@RequestParam(value = "p", required = false) Integer pageNumber,
			@RequestParam(value = "l", required = false) Integer limit,
			@RequestParam(value = "client_id", required = false) Long customerId,
			@RequestParam(value = "commodity_id", required = false) Long commodityId,
			@RequestParam(value = "inst_center_id", required = false) Long instCenterId,
			@RequestParam(value = "inst_center_type_id", required = false) Long instCenterTypeId,
			@RequestParam(value = "date_from", required = false) Long dateFrom,
			@RequestParam(value = "date_to", required = false) Long dateTo,
			@RequestParam(value = "commodity_category_id", required = false) Long commodityCategoryId,
			@RequestParam(value = "device_type", required = false) String deviceType,
			@RequestParam(value = "device_serial_number", required = false) String deviceSerialNo,
			@RequestParam(value = "device_type_id", required = false) Long deviceTypeId,
			@RequestParam(value = "state_id", required = false) Long stateId) {

		logger.info("AnalyticsController.getCollectionDetailsModified ");

		CollectionDetailsUniqueModel response;
		try {
			response = analyticsService.getCollectionDetailsByIdModified(pageNumber, limit, customerId, commodityId,
					instCenterId, dateFrom, dateTo, instCenterTypeId, commodityCategoryId, deviceType, deviceTypeId,
					deviceSerialNo, stateId);

			if (response == null)
				return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("AnalyticsController.getCollectionDetailsModified exception is ", e.getMessage());
			return analyticsService.handleException(e);
		}

	}

	@GetMapping("/scan-analytics")
	public ResponseEntity<?> getAnalytics(@RequestParam(value = "p", required = false) Integer pageNumber,
			@RequestParam(value = "l", required = false) Integer limit,
			@RequestParam(value = "client_id", required = false) Long customerId,
			@RequestParam(value = "commodity_id", required = false) Long commodityId,
			@RequestParam(value = "inst_center_id", required = false) Long instCenterId,
			@RequestParam(value = "inst_center_type_id", required = false) Long instCenterTypeId,
			@RequestParam(value = "region_id", required = false) Long regionId,
			@RequestParam(value = "date_from", required = false) Long dateFrom,
			@RequestParam(value = "date_to", required = false) Long dateTo,
			@RequestParam(value = "commodity_category_id", required = false) Long commodityCategoryId,
			@RequestParam(value = "device_type", required = false) String deviceType,
			@RequestParam(value = "device_serial_number", required = false) String deviceSerialNo,
			@RequestParam(value = "device_type_id", required = false) Long deviceTypeId) {

		logger.info("AnalyticsController.getAnalytics ");

		List<CommodityAnalyticModel> response;
		try {
			response = analyticsService.getAnalytics(customerId, commodityId, null, deviceType, deviceTypeId);

			if (response == null)
				return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("AnalyticsController.getAnalytics exception is ", e.getMessage());
			return analyticsService.handleException(e);
		}

	}

	@GetMapping("/commodity/devices")
	public ResponseEntity<?> getCommodityWiseDevices() {

		logger.info("AnalyticsController.getCommodityWiseDevices ");

		CommodityDeviceDropDownModel response;
		try {
			response = analyticsService.getCommodityDeviceDropDown();
			if (response == null)
				return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("AnalyticsController.getCommodityWiseDevices exception is ", e.getMessage());
			return analyticsService.handleException(e);
		}

	}

	@PutMapping("/update-scan/{scan_id}")
	public ResponseEntity<?> updateNanoScan(@PathVariable("scan_id") Long scanId, @RequestBody ScanModel postData) {

		logger.info(" NanoScanController.updateNanoScan() ");

//	logger.info("Data:  " + data);

		try {
//	    ScanModel postData = objectMapper.readValue(data, ScanModel.class);

			logger.info("Post Data:  " + postData);
			analyticsService.updateScan(postData, scanId);
			ScanResponseModel response = new ScanResponseModel();
			response.setMessage("Scan updated successful");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(" NanoScanController.updateNanoScan() ", e.getMessage());
			return analyticsService.handleException(e);
		}

	}

	@GetMapping("/links")
	public ResponseEntity<?> getUserLinks() {

		logger.info("AnalyticsController.getUserLinks ");

		UserLinkModel response;
		try {
			response = analyticsService.getUserLinks();
			if (response == null)
				return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("AnalyticsController.getUserLinks exception is ", e.getMessage());
			return analyticsService.handleException(e);
		}

	}
	
	@GetMapping("/location-details")
	public ResponseEntity<?> getLocationAnalyticsDetails(
			@RequestParam(value = "p", required = false) Integer pageNumber,
			@RequestParam(value = "l", required = false) Integer limit,
			@RequestParam(value = "commodity_id", required = false) Long commodityId,
			@RequestParam(value = "date_from", required = false) Long dateFrom,
			@RequestParam(value = "date_to", required = false) Long dateTo,
			@RequestParam(value = "device_serial_number", required = false) String deviceSerialNo,
			@RequestParam(value = "commodity_category_id", required = false) Long commodityCategoryId,
			@RequestParam(value = "device_type", required = false) String deviceType,
			@RequestParam(value = "device_type_id", required = false) Long deviceTypeId,
			@RequestParam(value = "search_keyword", required = false) String keyword,
			@RequestParam(value = "state_id", required = false) Long stateId,
			@RequestParam(value = "location_name", required = false) String locationName,
			@RequestParam(value = "district_name", required = false) String districtName
			) {

		logger.info("AnalyticsController.getLocationAnalyticsDetails ");

		WarehouseDetailsModel response;
		try {
			response = analyticsService.getLocationAnalyticsDetails(pageNumber, limit, commodityId, dateFrom, dateTo, commodityCategoryId, deviceType, deviceTypeId,
					deviceSerialNo, keyword, stateId,locationName,districtName);
			if (response == null) {
				return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("AnalyticsController.getLocationAnalyticsDetails exception is ", e.getMessage());
			return analyticsService.handleException(e);
		}

	}
	
	@GetMapping("/total-collections")
	public ResponseEntity<?> getTotalCollections(
			@RequestParam(value = "commodity_id", required = false) Long commodityId,
			@RequestParam(value = "date_from", required = false) Long dateFrom,
			@RequestParam(value = "date_to", required = false) Long dateTo,
			@RequestParam(value = "device_serial_number", required = false) String deviceSerialNo,
			@RequestParam(value = "commodity_category_id", required = false) Long commodityCategoryId,
			@RequestParam(value = "search_keyword", required = false) String keyword,
			@RequestParam(value = "state_id", required = false) Long stateId,
			@RequestParam(value = "district_name", required = false) String districtName) {

		logger.info("AnalyticsController.getTotalCollections ");

		TotalCollectionsModel response;
		try {
			response = analyticsService.getTotalCollections( commodityId, dateFrom, dateTo, commodityCategoryId,
					deviceSerialNo, keyword, stateId, districtName);
			if (response == null) {
				return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("AnalyticsController.getTotalCollections exception is ", e.getMessage());
			return analyticsService.handleException(e);
		}

	}
	
}
