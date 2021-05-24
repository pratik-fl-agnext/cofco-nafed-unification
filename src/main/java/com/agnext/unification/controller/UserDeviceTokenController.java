package com.agnext.unification.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agnext.unification.model.UserDeviceModel;
import com.agnext.unification.service.UserDeviceTokenService;

@RestController
@RequestMapping(value = "api/user", produces = "application/json")
public class UserDeviceTokenController {

	private static Logger logger = LoggerFactory.getLogger(UserDeviceTokenController.class);
	
	@Autowired
	UserDeviceTokenService service;

	@PostMapping(value = "/device/token")
	public ResponseEntity<?> saveOrUpdateDeviceToken(@RequestBody UserDeviceModel request) {

		UserDeviceModel response;
		try {
			response = service.saveOrUpdateDeviceToken(request);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("UserDeviceTokenController.saveOrUpdateDeviceToken exception is ",
					e.getMessage());
			return service.handleException(e);
		}

	}
 
}
 		
