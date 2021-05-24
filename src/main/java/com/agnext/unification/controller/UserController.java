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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.agnext.unification.model.PasswordModel;
import com.agnext.unification.model.StatusModel;
import com.agnext.unification.model.UserCSVModel;
import com.agnext.unification.model.UserCountModel;
import com.agnext.unification.model.UserModel;
import com.agnext.unification.oauth2.configuration.ActiveUsers;
import com.agnext.unification.service.UserService;

@RestController
@RequestMapping(value = "api/user", produces = "application/json")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService service;

    @Autowired
    ActiveUsers activeUsers;

    public static final String SUCCESS_PASSWORD_CHANGE_JSON = "{ \"status\" : true, \"message\": \"Password changed succesfully!\" }";

    /**
     * Creates the user.
     *
     * @param request
     *            the request
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserModel request) {
	logger.info("UserController.createUser " + request);
	UserModel response;
	try {
		 logger.info("  The User Request : "+request);
	    response = service.createUser(request);
	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	} catch (Exception e) {
	    logger.error("UserController.createUser exception is ", e.getMessage());
	    return service.handleException(e);
	}

    }

    /**
     * Get the user.
     *
     * @param request
     *            the request
     * @return the response entity
     */
    @GetMapping()
    public ResponseEntity<?> getAllUsers(@RequestParam(value = "p", required = false) Integer pageNumber,
	    @RequestParam(value = "l", required = false) Integer limit,
	    @RequestParam(value = "key_search", required = false) String keySearch,
	    @RequestParam(value = "customer_id", required = false) Long customerId,
	    @RequestParam(value = "user_type", required = false) String userType,
	    @RequestParam(value="is_state_admin",required=false)Boolean isStateAdmin) {
	logger.info("UserController.getUsers ");
	List<UserModel> response;
	try {
	    response = service.getUsers(pageNumber, limit, customerId, keySearch, userType,isStateAdmin);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("UserController.getUser exception is ", e.getMessage());
	    return service.handleException(e);
	}

    }
    

    /**
     * Get the user.
     *
     * @param request
     *            the request
     * @return the response entity
     */
    @GetMapping("/{user_id}")
    public ResponseEntity<?> getUser(@PathVariable("user_id") Long userId) {
	logger.info("UserController.getUser " + userId);
	UserModel response;
	try {
	    response = service.getUser(userId);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("UserController.getUser exception is ", e.getMessage());
	    return service.handleException(e);
	}

    }

    /**
     * Update the user.
     *
     * @param request
     *            the request
     * @return the response entity
     */
    @PutMapping("/{user_id}")
    public ResponseEntity<?> updateUser(@RequestBody UserModel request, @PathVariable("user_id") Long userId) {
	logger.info("UserController.updateUser " + request);
	UserModel response;
	try {
	    response = service.updateUser(request, userId);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("UserController.updateUser exception is ", e.getMessage());
	    return service.handleException(e);
	}

    }

    /**
     * Delete the user.
     *
     * @param request
     *            the request
     * @return the response entity
     */
    @DeleteMapping("/{user_id}")
    public ResponseEntity<?> deleteUser(@PathVariable("user_id") Long userId) {
	logger.info("UserController.deleteUser " + userId);
	UserModel response;
	try {
	    response = service.deleteUser(userId);
	    return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	} catch (Exception e) {
	    logger.error("UserController.deleteUser exception is ", e.getMessage());
	    return service.handleException(e);
	}

    }

    /**
     * user status update.
     *
     * @param request
     *            the request
     * @return the response entity
     */
    @PutMapping("status/{user_id}")
    public ResponseEntity<?> updateCustomerStatus(@PathVariable("user_id") Long userId,
	    @RequestBody StatusModel request) {
	logger.info("CustomerController.updateUserStatus ");
	StatusModel response;
	try {
	    response = service.updateUserStatus(userId, request);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("CustomerController.updateUserStatus exception is ", e.getMessage());
	    return service.handleException(e);
	}

    }

    /**
     * user status update.
     *
     * @param request
     *            the request
     * @return the response entity
     */
    @PutMapping("changePassword")
    public ResponseEntity<?> changePassword(@RequestBody PasswordModel request) {
	logger.info("CustomerController.updateUserStatus ");
	try {
	    service.changePassword(request);
	    return new ResponseEntity<>(SUCCESS_PASSWORD_CHANGE_JSON, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("CustomerController.updateUserStatus exception is ", e.getMessage());
	    return service.handleException(e);
	}

    }

    /**
     * Get the user's count.
     *
     * @param request
     *            the request
     * @return the response entity
     */
    @GetMapping("/count")
    public ResponseEntity<?> getUsersCount() {
	logger.info("UserController.getUsersCount ");
	UserCountModel response;
	try {
	    response = service.getUsersCount();
	    if (response == null) {
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(response, HttpStatus.OK);

	} catch (Exception e) {
	    logger.error("UserController.getUsersCount exception is ", e.getMessage());
	    return service.handleException(e);
	}

    }
    
    /**
     * Creates the user.
     *
     * @param request
     *            the request
     * @return the response entity
     */
    @PostMapping("/bulk-upload")
    public ResponseEntity<?> createUsersFromCSV( @RequestParam("file") MultipartFile mpf) {
    	
	logger.info("UserController.createUser " + mpf.getName());
	UserCSVModel response;
	try {
	    response = service.createUsersFromCSV(mpf);
	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	} catch (Exception e) {
	    logger.error("UserController.createUser exception is ", e.getMessage());
	    return service.handleException(e);
	}

    }

}
