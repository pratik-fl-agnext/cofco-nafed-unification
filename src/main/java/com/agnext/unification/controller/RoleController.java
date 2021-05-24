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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agnext.unification.model.PermissionModel;
import com.agnext.unification.model.RoleModel;
import com.agnext.unification.service.RoleService;

@RestController
@RequestMapping(value = "api/role", produces = "application/json")
public class RoleController {
	
	private static Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	RoleService service;
	
	/**
	 * Creates the role.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<?> createRole(@RequestBody RoleModel request) {
		logger.info("RoleController.createRole "+request);
		RoleModel response;
		try {
			response = service.createRole(request);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			 logger.error("RoleController.createRole exception is ",  e.getMessage());
			return service.handleException(e);
		}
		
	}
	
	/**
	 * Creates the role with permission.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@PostMapping("/{role_id}/assign-permissions")
	public ResponseEntity<?> assignPermissionsToRole(@RequestBody List<PermissionModel> permissions,@PathVariable("role_id") Long roleId){
		logger.info("RoleController.assignPermissionsToRole "+roleId);
		List<RoleModel> response;
		try {
			response = service.assignPermissionsToRole(permissions,roleId);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			 logger.error("RoleController.permissions exception is ",  e.getMessage());
			return service.handleException(e);
		}
		
	}
	
	/**
	 * Get all roles
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@GetMapping()
	public ResponseEntity<?> getAllRoles(){
		logger.info("RoleController.getAllRoles ");
		List<RoleModel> response;
		try {
			response = service.getAllRoles();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			 logger.error("RoleController.getAllRoles exception is ",  e.getMessage());
			return service.handleException(e);
		}
		
	}
	
	@GetMapping("/permissions")
    public ResponseEntity<?> findPermissionsByRoles() {
		logger.info("RoleController.getAllRolesAndPermissions ");
		List<RoleModel> response;
        try {
        	response = service.getPermissionsForRole();
        	return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
        	logger.error("RoleController.getAllRolesAndPermissions exception is ",  ex.getMessage());
			return service.handleException(ex);
        }
    }
 
}
