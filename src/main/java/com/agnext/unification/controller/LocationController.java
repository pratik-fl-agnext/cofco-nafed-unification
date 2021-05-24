package com.agnext.unification.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agnext.unification.common.Constants;
import com.agnext.unification.config.ApplicationContext;
import com.agnext.unification.entity.nafed.StateEntity;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.LocationListModel;
import com.agnext.unification.model.LocationModel;
import com.agnext.unification.service.LocationService;
import com.agnext.unification.service.NafedDataService;


/**
 * Location Controller
 * 
 * @author Piyush
 * @version 1.0
 * 
 */
@RestController
@RequestMapping(value = "api/location", produces = "application/json")
public class LocationController {
	
	private static Logger logger = LoggerFactory.getLogger(LocationController.class);
	
	@Autowired
	LocationService service;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	NafedDataService nafedDataService;
	
	@GetMapping()
	public ResponseEntity<?> getAllCommercialLocation(
			@RequestParam(value = "search_keyword", required = false) String searchKeyword,
			@RequestParam(value = "p", required = false) Integer pageNumber,
			@RequestParam(value = "l", required = false) Integer limit,
			@RequestParam(value = "customer_id", required = false) Long customerId,
			@RequestParam(value = "region_id", required = false) Long regionId,
			@RequestParam(value = "installation_center_type_id", required = false) Long installationCenterTypeId) {
		logger.info("CommercialLocationController.getAllCommercialLocation ");
		List<LocationModel> response;
		try {
			response = service.getAllCommercialLocationByFilter(searchKeyword, pageNumber, limit,customerId,regionId,installationCenterTypeId);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("CommercialLocationController.getAllCommercialLocationTypeDetails exception is ",
					e.getMessage());
			return service.handleException(e);
		}

	}
	
	@PostMapping()
	public ResponseEntity<?> saveCommercialLocation(@RequestBody LocationModel request) {
		logger.info("CommercialLocationController.saveCommercialLocation ");
		LocationModel response;
		try {
			response = service.saveCommercialLocation(request);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("CommercialLocationController.saveCommercialLocation exception is ", e.getMessage());
			return service.handleException(e);
		}

	}
	
	@PutMapping("/{installation_center_id}")
	public ResponseEntity<?> updateCommercialLocation(@PathVariable("installation_center_id") Long installationCenterId,
			@RequestBody LocationModel request) {
		logger.info("CommercialLocationController.updateCommercialLocation ");
		LocationModel response;
		try {
			response = service.updateCommercialLocation(installationCenterId, request);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("CommercialLocationController.updateCommercialLocation exception is ", e.getMessage());
			return service.handleException(e);
		}

	}

	@DeleteMapping("/{installation_center_id}")
	public ResponseEntity<?> deleteCommercialLocation(
			@PathVariable("installation_center_id") Long installationCenterId) {
		logger.info("CommercialLocationController.deleteCommercialLocation ");
//		String response;
		try {
			service.deleteCommercialLocation(installationCenterId);
			return new ResponseEntity<>( HttpStatus.OK);
		} catch (Exception e) {
			logger.error("CommercialLocationController.deleteCommercialLocation exception is ", e.getMessage());
			return service.handleException(e);
		}

	}
	
	@GetMapping("/{installation_center_id}")
	public ResponseEntity<?> getLocationById(@PathVariable("installation_center_id") Long installationCenterId) {
		logger.info("CommercialLocationController.getLocationById ");
		LocationModel response;
		try {
			response = service.getLocationById(installationCenterId);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("CommercialLocationController.updateCommercialLocation exception is ", e.getMessage());
			return service.handleException(e);
		}

	}
	

	@GetMapping("/by-state")
	public ResponseEntity<?> getLocationByStateId() throws IMException {
		logger.info("LocationController.getLocationByStateId ");
		
		Long stateId;
		if (applicationContext.getRequestContext().getRoles().contains(Constants.CustomerType.STATE_ADMIN)) {
	                StateEntity stateEntity = nafedDataService.getStateIdForStateAdmin(applicationContext.getRequestContext().getStateAdmin());
	                if (stateEntity != null) {
	                    stateId = stateEntity.getId();
	                } else {
	                    throw new IMException(Constants.ErrorCode.STATE_NOT_FOUND, Constants.ErrorMessage.STATE_NOT_FOUND);
	                }
	        } else {
                    throw new IMException(Constants.ErrorCode.USER_NOT_AUTHORIZED, Constants.ErrorMessage.USER_NOT_AUTHORIZED);
	        }
		
		LocationListModel response;
		try {
			response = service.getLocationByStateId(stateId);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("LocationController.getLocationByStateId exception is ", e.getMessage());
			return service.handleException(e);
		}

	}

}
