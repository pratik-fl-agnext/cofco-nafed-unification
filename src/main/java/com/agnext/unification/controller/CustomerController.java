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

import com.agnext.unification.model.ClientDetailsModel;
import com.agnext.unification.model.CustomerCountModel;
import com.agnext.unification.model.CustomerModel;
import com.agnext.unification.model.StatusModel;
import com.agnext.unification.service.CustomerService;

@RestController
@RequestMapping(value = "api/customer", produces = "application/json")
public class CustomerController {
	
	private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService service;
	
	/**
	 * Creates the customer.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<?> createCustomer(@RequestBody CustomerModel request) {
		logger.info("CustomerController.createCustomer "+request);
		CustomerModel response;
		try {
			response = service.createCustomer(request);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			 logger.error("CustomerController.createCustomer exception is ",  e.getMessage());
			return service.handleException(e);
		}
		
	}
	
	/**
	 * List of the customers.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@GetMapping
	public ResponseEntity<?> getCustomers(@RequestParam(value = "p", required = false) Integer pageNumber,
			@RequestParam(value = "l", required = false) Integer limit,@RequestParam(value="key_search", required = false) String keySearch,
			@RequestParam(value="customer_type", required = false) String customerType) {
		logger.info("CustomerController.getLsitOfCustomers ");
		List<CustomerModel> response;
		try {
			response = service.geCustomers(pageNumber,limit,keySearch,customerType);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			 logger.error("CustomerController.getLsitOfCustomers exception is ",  e.getMessage());
			return service.handleException(e);
		}
		
	}
	
	/**
	 * Get customer by customer uuid.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@GetMapping("/{customer_id}")
	public ResponseEntity<?> getCustomerById(@PathVariable("customer_id") Long customerId) {
		logger.info("CustomerController.getCustomerByCustomerUuid ");
		CustomerModel response;
		try {
			response = service.getCustomerById(customerId);
			if(response==null)
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			 logger.error("CustomerController.getCustomerByCustomerUuid exception is ",  e.getMessage());
			return service.handleException(e);
		}
		
	}
	
	/**
	 * update customer by customer uuid.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@PutMapping("/{customer_id}")
	public ResponseEntity<?> updateCustomerById(@RequestBody CustomerModel request,@PathVariable("customer_id") Long customerId) {
		logger.info("CustomerController.updateCustomerById ");
		CustomerModel response;
		try {
			request.setCustomerId(customerId);
			response = service.updateCustomerById(request);
			if(response==null)
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			 logger.error("CustomerController.updateCustomerById exception is ",  e.getMessage());
			return service.handleException(e);
		}
		
	}
	
	/**
	 * Delete customer by customer id.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@DeleteMapping("/{customer_id}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable("customer_id") Long customerId) {
		logger.info("CustomerController.deleteCustomerById ");
		CustomerModel response;
		try {
			response = service.deleteCustomerById(customerId);
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			 logger.error("CustomerController.deleteCustomerById exception is ",  e.getMessage());
			return service.handleException(e);
		}
		
	}
	
	/**
	 * customer status update.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@PutMapping("status/{customer_id}")
	public ResponseEntity<?> updateCustomerStatus(@PathVariable("customer_id") Long customerId,@RequestBody StatusModel request) {
		logger.info("CustomerController.updateCustomerStatus ");
		StatusModel response;
		try {
			response = service.updateCustomerStatus(customerId,request);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			 logger.error("CustomerController.updateCustomerStatus exception is ",  e.getMessage());
			return service.handleException(e);
		}
		
	}
	
	/**
	 * update customer status from Initiated to Active and user also
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@PutMapping("/approve")
	public ResponseEntity<?> updateCustomerByIdAndUserStatusUpdate(@RequestBody CustomerModel request) {
		logger.info("CustomerController.updateCustomerByIdAndUserStatusUpdate ");
		CustomerModel response;
		try {
			response = service.updateCustomerByIdAndUserStatusUpdate(request);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			 logger.error("CustomerController.updateCustomerByIdAndUserStatusUpdate exception is ",  e.getMessage());
			return service.handleException(e);
		}
		
	}
	
	/**
	 * Get customer by customers detail.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@GetMapping("/count")
	public ResponseEntity<?> getCustomersCount(@RequestParam(value = "p", required = false) Integer pageNumber,
			@RequestParam(value = "l", required = false) Integer limit) {
		
		logger.info("CustomerController.getCustomersCount ");
		CustomerCountModel response;
		try {
			response = service.getCustomersCount(pageNumber,limit);
			if(response==null)
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			 logger.error("CustomerController.getCustomersCount exception is ",  e.getMessage());
			return service.handleException(e);
		}
		
	}
	
	/**
	 * Get customer name by customer id.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@GetMapping("/name/{customer_id}")
	public ResponseEntity<?> getCustomerNameById(@PathVariable("customer_id") Long customerId) {
		logger.info("CustomerController.getCustomerNameById ");
		String response;
		try {
			response = service.getCustomerNameById(customerId);
			if(response==null)
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			 logger.error("CustomerController.getCustomerNameById exception is ",  e.getMessage());
			return service.handleException(e);
		}
		
	}
	
	/**
	 * Get client's details.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@GetMapping("/clients")
	public ResponseEntity<?> getClients() {
		logger.info("CustomerController.getClients ");
		List<ClientDetailsModel> response;
		try {
			response = service.getClientDetails();
			if(response==null)
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			 logger.error("CustomerController.getClients exception is ",  e.getMessage());
			return service.handleException(e);
		}
		
	}
	
	/**
	 * Get operators details.
	 *
	 * @param request the request
	 * @return the response entity
	 */
	@GetMapping("/operators")
	public ResponseEntity<?> getOperators() {
		logger.info("CustomerController.getClients ");
		List<ClientDetailsModel> response;
		try {
			response = service.getOperators();
			if(response==null)
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			 logger.error("CustomerController.getOperators exception is ",  e.getMessage());
			return service.handleException(e);
		}
		
	}
	
}
