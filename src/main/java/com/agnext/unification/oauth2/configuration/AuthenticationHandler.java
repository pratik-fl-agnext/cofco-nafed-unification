package com.agnext.unification.oauth2.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationHandler implements AuthenticationSuccessHandler {
	
	private AuthenticationSuccessHandler target = new SavedRequestAwareAuthenticationSuccessHandler();

    public void onAuthenticationSuccess(HttpServletRequest request,
        HttpServletResponse response, Authentication auth) throws IOException, ServletException {
    	//SecurityContextHolder.clearContext();
    	//request.getSession().invalidate();
    	target.onAuthenticationSuccess(request, response, auth);
        /*if (hasTemporaryPassword(auth)) {
            response.sendRedirect("/changePassword");
        } else {
            target.onAuthenticationSuccess(request, response, auth);
        }*/
    }

    public void proceed(HttpServletRequest request, 
        HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        target.onAuthenticationSuccess(request, response, auth);
    }


}
