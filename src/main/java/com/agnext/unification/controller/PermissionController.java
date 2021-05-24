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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agnext.unification.model.PermissionModel;
import com.agnext.unification.oauth2.configuration.ActiveUsers;
import com.agnext.unification.service.PermissionService;
import com.agnext.unification.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class PermissionController.
 */
@RestController
@RequestMapping(value = "api/permission", produces = "application/json")
public class PermissionController {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(PermissionController.class);

	/** The service. */
	@Autowired
	PermissionService service;
	@Autowired
	UserService userUervice;
	
	@Autowired
	ActiveUsers activeUsers;
	/**
	 * Creates the permission.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<?> createPermission(@RequestBody PermissionModel request) {
		logger.info("PermissionController.createPermission " + request);
		PermissionModel response;
		try {
			response = service.createPermission(request);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("PermissionController.createPermission exception is ", e.getMessage());
			return service.handleException(e);
		}

	}

	@GetMapping
	public ResponseEntity<?> getPermission() {
			return new ResponseEntity<>("{\"permissionCode\":1}", HttpStatus.OK);

	}
	
	
	/**
	 * Delete permission.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePermission(@PathVariable("id") Long id) {
		logger.info("PermissionController.deletePermission " + id);
		try {
			service.deletePermission(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error("PermissionController.deletePermission exception is ", e.getMessage());
			return service.handleException(e);
		}
	}

}
