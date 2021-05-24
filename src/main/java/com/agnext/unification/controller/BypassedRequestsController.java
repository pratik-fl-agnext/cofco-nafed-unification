package com.agnext.unification.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agnext.unification.model.AndroidVersionModel;
import com.agnext.unification.service.BypassedRequestService;

@RestController
@RequestMapping(value = "bypassed-requests", produces = "application/json")
public class BypassedRequestsController {
	
	private static Logger logger = LoggerFactory.getLogger(com.agnext.unification.controller.BypassedRequestsController.class);
	@Autowired
	BypassedRequestService service;
	
	@GetMapping()
	public ResponseEntity<?> getAndroidAppVersion() {
		logger.info("BypassedRequestsController.getAndroidAppVersion ");
		AndroidVersionModel response;
		try {
			response=service.getAndroidAppVersion();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("BypassedRequestsController.getAndroidAppVersion ", e.getMessage());
			return service.handleException(e);
		}
	}

}
