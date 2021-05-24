package com.agnext.unification.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agnext.unification.model.AnalyticsRangeModel;
import com.agnext.unification.model.RangeDetailsModel;
import com.agnext.unification.model.RangeModel;
import com.agnext.unification.service.AnalyticsClientRangeService;

/**
 * Analytics Client Range Controller
 *
 */
@RestController
@RequestMapping(value = "api/range", produces = "application/json")
public class AnalyticsClientRangeControllers {
	
	private static Logger logger = LoggerFactory.getLogger(DeviceController.class);
	
	@Autowired
	AnalyticsClientRangeService service;
	
	@PostMapping()
	public ResponseEntity<?> saveAnalyticsRange(@RequestBody RangeModel rangeModel) {
		logger.info("AnalyticsClientRangeControllers.saveAnalyticsRange: " + rangeModel);
		try {
			service.saveRange(rangeModel);
			//service.saveNewDevices(deviceModel);  //enhancement #80
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("AnalyticsClientRangeControllers.saveRange exception is ", e.getMessage());
			return service.handleException(e);
		}
	}
	
	@GetMapping()
	public ResponseEntity<?> getAllAnalyticsRange(@RequestParam(value = "client_id", required = false) Long clientId,
			@RequestParam(value = "warehouse_name", required = false) String warehosueName,
			@RequestParam(value = "commodity_id", required = false) Long commodityId) {
		logger.info("AnalyticsClientRangeControllers.getAllAnalyticsRange: " );
		try {
			AnalyticsRangeModel response;
			response=service.getAllRange(clientId,warehosueName,commodityId);
			//service.saveNewDevices(deviceModel);  //enhancement #80
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("AnalyticsClientRangeControllers.getAllAnalyticsRange exception is ", e.getMessage());
			return service.handleException(e);
		}
	}

	@GetMapping("/by-commodity")
	public ResponseEntity<?> filteredDataByCommodity(@RequestParam(value = "commodity_id", required = false) Long commodityId) {
		logger.info("AnalyticsClientRangeControllers.filteredDataByCommodity: " );
		try {
			RangeDetailsModel response;
			response=service.getFilteredDataByCommodity(commodityId);
			//service.saveNewDevices(deviceModel);  //enhancement #80
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("AnalyticsClientRangeControllers.filteredDataByCommodity exception is ", e.getMessage());
			return service.handleException(e);
		}
	}
}
