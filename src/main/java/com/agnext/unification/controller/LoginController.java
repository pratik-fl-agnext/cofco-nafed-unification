/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

// TODO: Auto-generated Javadoc
/**
 * The Class PermissionController.
 */
@RestController
@RequestMapping(produces = "application/json")
public class LoginController {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

		
	@RequestMapping(method = RequestMethod.GET, value = "/login")
    public ModelAndView login(ModelAndView model,HttpServletRequest request,HttpServletResponse response) {
        logger.info("Inside login GET Method: started...");
        if("mobile".equals(request.getParameter("bearer"))) {
        	model = new ModelAndView(new MappingJackson2JsonView()); 
        	model.addObject("action", "login"); 
        	model.addObject("key2", "value2");
        	//model.setViewName("mobile_login");
        }
        else {
        	response.setHeader("ETag", "33a64df551425fcc55e4d42a148795d9f25f89d4");
        	model.setViewName("login");
        }
        return model;
    }
	
	
}
