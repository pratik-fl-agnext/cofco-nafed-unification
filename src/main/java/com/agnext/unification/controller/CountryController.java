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
import org.springframework.web.bind.annotation.RestController;

import com.agnext.unification.model.CountryModel;
import com.agnext.unification.service.CountryService;

@RestController
@RequestMapping(value = "api/countries", produces = "application/json")
public class CountryController {
	
	private static Logger logger = LoggerFactory.getLogger(CountryController.class);

	@Autowired
	CountryService service;
	
	/**
	 * Get the list of countries.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@GetMapping()
	public ResponseEntity<?> getAllCountries() {
		logger.info("UserController.getAllCountries ");
		List<CountryModel> response;
		try {
			response = service.getAllCountries();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			 logger.error("UserController.getAllCountries exception is ",  e.getMessage());
			return service.handleException(e);
		}
		
	}
	
	 
}
