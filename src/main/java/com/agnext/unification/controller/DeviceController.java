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

import com.agnext.unification.config.ServerContext;
import com.agnext.unification.model.CustomerDeviceSubscriptionModel;
import com.agnext.unification.model.DeviceGroupModel;
import com.agnext.unification.model.DeviceModel;
import com.agnext.unification.model.DeviceOrderModel;
import com.agnext.unification.model.DeviceSensorProfileModel;
import com.agnext.unification.model.DeviceSubTypeModel;
import com.agnext.unification.model.DeviceTypeModel;
import com.agnext.unification.model.UserDetailModel;
import com.agnext.unification.service.DeviceService;

/*
 * Device Controller
 * 
 * @author piyush.r
 * @version 1.0
 * 
 */
@RestController
@RequestMapping(value = "api/device", produces = "application/json")
public class DeviceController {
	private static Logger logger = LoggerFactory.getLogger(DeviceController.class);

	@Autowired
	DeviceService service;

	@Autowired
	ServerContext serverContext;

	@GetMapping("/filter")
	public ResponseEntity<?> getDevicesByFilter(@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "p", required = false) Integer pageNumber,
			@RequestParam(value = "l", required = false) Integer limit,
			@RequestParam(value = "customer_id", required = false) Long customerId,
			@RequestParam(value = "operation_type", required = false) String operationType) {
		logger.info("DeviceController.getDevicesByFilter ");
		List<DeviceModel> response;
		try {

			response = service.getDevicesByFilter(keyword, pageNumber, limit, customerId, operationType);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("DeviceController.getDevicesByFilter exception is ", e.getMessage());
			return service.handleException(e);
		}

	}

	@GetMapping("/order")
	public ResponseEntity<?> getDeviceOrder(@RequestParam(value = "p", required = false) Integer pageNumber,
			@RequestParam(value = "l", required = false) Integer limit,
			@RequestParam(value = "search_keyword", required = false) String keyword) {
		logger.info("DeviceController.getDeviceOrder()");
		List<DeviceOrderModel> response;
		try {
			response = service.getDeviceOrder(pageNumber, limit, keyword);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("DeviceController.getDeviceOrder exception is ", e.getMessage());
			return service.handleException(e);
		}
	}
	
	@GetMapping("/{device_id}")
	public ResponseEntity<?> getDeviceById(@PathVariable("device_id") Long id) {
		logger.info("DeviceController.getAllDevicesForNonAdmin ");
		DeviceModel response;
		try {
			response = service.getDeviceById(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("DeviceController.getDeviceById exception is ", e.getMessage());
			return service.handleException(e);
		}

	}
	
	@GetMapping("/types")
	public ResponseEntity<?> getAllDeviceTypes(@RequestParam(value = "p", required = false) Integer pageNumber,
			@RequestParam(value = "l", required = false) Integer limit) {

		logger.info("DeviceController.getAllDeviceTypes ");
		List<DeviceTypeModel> response;
		try {
			response = service.getAllDeviceTypes(pageNumber, limit);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("DeviceController.getAllDeviceTypes exception is ", e.getMessage());
			return service.handleException(e);
		}

	}
	
	@GetMapping("/group")
//	@Permissions(values = "get_devices_list")
	public ResponseEntity<?> getDeviceGroups() {
		logger.info("DeviceController.getDeviceGroup()");

		List<DeviceGroupModel> response;
		try {
			response = service.getDeviceGroups();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("DeviceController.getAllDeviceGroup exception is ", e.getMessage());
			return service.handleException(e);
		}

	}

	@GetMapping("/sub-type")
//	@Permissions(values = "get_devices_list")
	public ResponseEntity<?> getDeviceSubTypes() {
		logger.info("DeviceController.getDeviceSubTypes()");

		List<DeviceSubTypeModel> response;
		try {
			response = service.getDeviceSubTypes();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("DeviceController.getDeviceSubTypes exception is ", e.getMessage());
			return service.handleException(e);
		}

	}

	@GetMapping("/sensor-profile")
//	@Permissions(values = "get_devices_list")
	public ResponseEntity<?> getSensorProfiles() {
		logger.info("DeviceController.getSensorProfiles()");

		List<DeviceSensorProfileModel> response;
		try {
			response = service.getSensorProfiles();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("DeviceController.getSensorProfiles exception is ", e.getMessage());
			return service.handleException(e);
		}
	}

	@PostMapping("/inventory")
	public ResponseEntity<?> saveDevices(@RequestBody DeviceModel deviceModel) {
		logger.info("DeviceController.saveDevices : " + deviceModel);
		try {
			service.saveDevices(deviceModel);
			// service.saveNewDevices(deviceModel); //enhancement #80
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("DeviceController.saveDevices exception is ", e.getMessage());
			return service.handleException(e);
		}
	}

	@PutMapping("/inventory/{device_id}")
	public ResponseEntity<?> updateDevices(@RequestBody DeviceModel deviceModel,
			@PathVariable("device_id") Long deviceId) {
		logger.info("DeviceController.updateDevices : " + deviceModel);

		try {
			deviceModel.setDeviceId(deviceId);
			service.updateDevices(deviceModel);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("DeviceController.updateDevices exception is ", e.getMessage());
			return service.handleException(e);
		}
	}

	@PutMapping("/provision/{device_id}")
	public ResponseEntity<?> provision(@RequestBody DeviceModel deviceModel, @PathVariable("device_id") Long deviceId) {
		logger.info("DeviceController.provision : " + deviceModel);

		try {
			deviceModel.setDeviceId(deviceId);
			service.provision(deviceModel);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("DeviceController.provision  exception is ", e.getMessage());
			return service.handleException(e);
		}
	}

	@GetMapping("/customer-subscribed")
	public ResponseEntity<?> getCustomerSubscribedDeviceDetails(
			@RequestParam(value = "p", required = false) Integer pageNumber,
			@RequestParam(value = "l", required = false) Integer limit) {
		logger.info("DeviceController.getCustomerSubscribedDeviceDetails()");
		CustomerDeviceSubscriptionModel response;
		try {
			response = service.getCustomerSubscribedDeviceDetails(pageNumber, limit);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("DeviceController.getCustomerSubscribedDeviceDetails exception is ", e.getMessage());
			return service.handleException(e);
		}
	}

	@GetMapping("/operator")
	public ResponseEntity<?> getOperatorDeviceDetails(@RequestParam(value = "p", required = false) Integer pageNumber,
			@RequestParam(value = "l", required = false) Integer limit) {
		logger.info("DeviceController.getOperatorDeviceDetails()");
		CustomerDeviceSubscriptionModel response;
		try {
			response = service.getOperatorDeviceDetails(pageNumber,limit);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("DeviceController.getOperatorDeviceDetails exception is ", e.getMessage());
			return service.handleException(e);
		}
	}
	
	
	@PostMapping("/user-details")
	public ResponseEntity<?> saveUserDetails(@RequestBody UserDetailModel userDetails) {
		logger.info("DeviceController.saveUserDetails : " + userDetails);
		try {
			service.saveUserDetails(userDetails);
			//service.saveNewDevices(deviceModel);  //enhancement #80
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("DeviceController.saveUserDetails exception is ", e.getMessage());
			return service.handleException(e);
		}
	}
	
}
