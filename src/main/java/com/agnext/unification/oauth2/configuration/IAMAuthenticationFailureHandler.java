package com.agnext.unification.oauth2.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class IAMAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	ObjectMapper mapper = new ObjectMapper();
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		
		if("mobile".equals(request.getParameter("bearer"))) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			 Map<String, Object> data = new HashMap<>();
			 data.put("error-code", "L120001"); 
			 data.put("error-message", "Invalid credentials");
			 response.getOutputStream().println(mapper.writeValueAsString(data));
		}else {
			//super.onAuthenticationFailure(request, response, exception);
			response.sendRedirect("/login?error=Y");
		}
		
		//System.out.println("Parameter : "+request.getParameter("test"));
		//request.getRequestDispatcher("/login?error=Y").include(request, response);
		
	}
}