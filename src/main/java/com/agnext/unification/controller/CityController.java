/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agnext.unification.model.CityModel;
import com.agnext.unification.service.CityService;

@RestController
@RequestMapping(value = "api/cities", produces = "application/json")
public class CityController {
	
	private static Logger logger = LoggerFactory.getLogger(CityController.class);

	@Autowired
	CityService service;
	
	/**
	 * Get the list of city by state id.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@GetMapping()
	public ResponseEntity<?> getAllCities(@RequestParam("state_id") Long stateId) {
		logger.info("CityController.getAllCities ");
		List<CityModel> response;
		try {
			response = service.getAllCities(stateId);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			 logger.error("CityController.getAllCities exception is ",  e.getMessage());
			return service.handleException(e);
		}
		
	}
	
	 
}
