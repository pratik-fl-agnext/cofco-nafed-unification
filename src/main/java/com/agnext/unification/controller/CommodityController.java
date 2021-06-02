package com.agnext.unification.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agnext.unification.common.Constants;
import com.agnext.unification.config.ApplicationContext;
import com.agnext.unification.model.CategoryCommodityVarietyWrapperModel;
import com.agnext.unification.model.CommodityCategoryModel;
import com.agnext.unification.model.CommodityModel;
import com.agnext.unification.model.CommodityVarietyModel;
import com.agnext.unification.repository.cofco.CofcoCommodityRepository;
import com.agnext.unification.repository.nafed.DcmCommodityRepository;
import com.agnext.unification.service.CommodityService;

/**
 * Commodity Controller
 *
 */
@RestController
@RequestMapping(value = "api/commodity", produces = "application/json")
public class CommodityController {

    private static Logger logger = LoggerFactory.getLogger(CommodityController.class);

    @Autowired
    CommodityService service;
    
    @Autowired
    ApplicationContext  appContext;
    
    @Autowired
    BeanFactory bf;
    
    @Autowired
    DcmCommodityRepository nafedCommRepo;
    
    @Autowired
    CofcoCommodityRepository cofcoCommRepo;

    /**
     * get the commodity category.
     *
     * @param request
     *            the request
     * @return the response entity
     */
    @GetMapping("/categories")
    public ResponseEntity<?> getCommodityCategory(@RequestParam(value = "p", required = false) Integer pageNumber,
	    @RequestParam(value = "l", required = false) Integer limit,
	    @RequestParam(value = "customer_id", required = false) Long customerId) {
	logger.info("CommodityController.getCommodityCategory ");
	List<CommodityCategoryModel> response;
	try {
	    response = service.getCommodityCategory(pageNumber, limit, customerId);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("CommodityController.getCommodityCategory exception is ", e.getMessage());
	    return service.handleException(e);
	}

    }

    /**
     * get the commodity.
     *
     * @param request
     *            the request
     * @return the response entity
     */
    @GetMapping()
    //	@Permissions(values = "get_commodity")
    public ResponseEntity<?> getCommodityOrCategoryId(
	    @RequestParam(value = "commodityCategoryId", required = false) Long commodityCategoryId,
	    @RequestParam(value = "p", required = false) Integer pageNumber,
	    @RequestParam(value = "l", required = false) Integer limit) {
	//		logger.info("CommodityController.getCommodityOrCategoryId " + serverContext.getRequestContext());
	List<CommodityModel> response;
	try {
	    response = service.getCommodityOrCategoryId(commodityCategoryId, pageNumber, limit);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("CommodityController.getCommodityOrCategoryId exception is ", e.getMessage());
	    return service.handleException(e);
	}

    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllCommodityList() {
	logger.info("CommodityController.getAllCommodityList ");

	try {
	    List<CommodityModel> response = service.getAllCommodities();
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("CommodityController.getAllCommodityList exception is ", e.getMessage());
	    return service.handleException(e);
	}

    }
    /**
     * get the commodity.
     *
     * @param request
     *            the request
     * @return the response entity
     */
    @GetMapping("/device")
    //	@Permissions(values = "get_commodity")
    public ResponseEntity<?> getCommodityByDevice(
	    @RequestParam(value = "device_id", required = false) Long deviceId,
	    @RequestParam(value = "p", required = false) Integer pageNumber,
	    @RequestParam(value = "l", required = false) Integer limit) {
	List<CommodityModel> response;
	try {
	    response = service.getCommodityByDevice(deviceId, pageNumber, limit);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("CommodityController.getCommodityByDevice exception is ", e.getMessage());
	    return service.handleException(e);
	}

    }
    
    /**
     * get the commodity variety.
     *
     * @param request
     *            the request
     * @return the response entity
     */
    @GetMapping("/variety")
    public ResponseEntity<?> getCommodityVariety(@RequestParam(value = "p", required = false) Integer pageNumber,
	    @RequestParam(value = "l", required = false) Integer limit,
	    @RequestParam(value = "commodity_id", required = false) Long commodityId) {
	logger.info("CommodityController.getCommodityVariety ");
	CommodityVarietyModel response;
	try {
	    response = service.getCommodityVariety(pageNumber, limit, commodityId);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("CommodityController.getCommodityVariety exception is ", e.getMessage());
	    return service.handleException(e);
	}

    }
    
    /**
     * get the commodity.
     *
     * @param request
     *            the request
     * @return the response entity
     */
    @GetMapping("/by-device")
    public ResponseEntity<?> getCategoryCommodityVarietyByDevice(
	    @RequestParam(value = "device_id", required = false) Long deviceId,
	    @RequestParam(value = "p", required = false) Integer pageNumber,
	    @RequestParam(value = "l", required = false) Integer limit) {
	CategoryCommodityVarietyWrapperModel response;
	try {
	    response = service.getCategoryCommodityVarietyByDevice(deviceId, pageNumber, limit);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("CommodityController.getCategoryCommodityVarietyByDevice exception is ", e.getMessage());
	    return service.handleException(e);
	}

    }
    
    @GetMapping("/by-state")
    public ResponseEntity<?> getCommodityListByState() {
	try {
	    List<CommodityModel> response = service.getCommodityByState();
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    logger.error("CommodityController.getCommodityByState exception is ", e.getMessage());
	    return service.handleException(e);
	}
    }
    
    @GetMapping("/cofco")
    public ResponseEntity<?> getCommoditiedForCofco() {
	try {
	    List<CommodityModel> response = service.getCofcoCommoditied();
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    return service.handleException(e);
	}
    }
    
    @GetMapping("/nafed")
    public ResponseEntity<?> getCommoditiedForNafed() {
	try {
	    List<CommodityModel> response = service.getNafedCommoditied();
	    return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    return service.handleException(e);
	}
    }
    
    @GetMapping("/unified-commoditied")
    public ResponseEntity<?> getCommoditiedUnified() {
	try {
	  String urlId = Constants.URL.getIdUsingUrl(appContext.getRequestContext().getRequestURL());
	  //urlId = "COFCO";
	  
	  //TODO implement factory pattern
	  
	  List<CommodityModel> response = service.getAllCommodityList(urlId, nafedCommRepo);
	  
	  return new ResponseEntity<>(response, HttpStatus.OK);
	} catch (Exception e) {
	    return service.handleException(e);
	}
    }
}
