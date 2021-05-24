/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agnext.unification.model.LoginResponseModel;
import com.agnext.unification.model.UserDeviceModel;
import com.agnext.unification.service.AuthenticationService;

@RestController
@RequestMapping(value = "arya/token", produces = "application/json")
public class AuthenticationController {

	private static Logger logger = LoggerFactory.getLogger(com.agnext.unification.controller.AuthenticationController.class);

	@Autowired
	AuthenticationService service;

	/**
	 * authenticate user by access code.
	 *
	 * */

	@GetMapping()
	public ResponseEntity<?> authorizeUser(@RequestParam("code") String code,@RequestParam(name="bearer",required = false) String bearer,
			@RequestBody(required = false) UserDeviceModel userDeviceModel) {
		logger.info("AuthenticationController.authorizeUser with code : {}, and bearer :{}", code, bearer);
		LoginResponseModel response;
		try {
			response=service.authorizeUserWithCode(code,bearer,userDeviceModel);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("AuthenticationController.authorizeUser ", e.getMessage());
			return service.handleException(e);
		}
	}
}
