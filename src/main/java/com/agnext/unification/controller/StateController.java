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

import com.agnext.unification.model.StateModel;
import com.agnext.unification.service.StateService;

@RestController
@RequestMapping(value = "api/states", produces = "application/json")
public class StateController {

    private static Logger logger = LoggerFactory.getLogger(StateController.class);

    @Autowired
    StateService service;

    /**
     * Get the list of state by country id.
     *
     * @param request
     *            the request
     * @return the response entity
     */
    @GetMapping()
    public ResponseEntity<?> getAllStates(@RequestParam("country_id") Long countryId) {
	logger.info("StateController.getAllStates ");
	List<StateModel> response;
	try {
	    response = service.getAllStates(countryId);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("StateController.getAllStates exception is ", e.getMessage());
	    return service.handleException(e);
	}

    }

    @GetMapping("/scan-based")
    public ResponseEntity<?> getStates() {
	logger.info("StateController.getStates ");
	List<StateModel> response;
	try {
	    response = service.getStatesFromScan();
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("StateController.getStates exception is ", e.getMessage());
	    return service.handleException(e);
	}

    }

}
