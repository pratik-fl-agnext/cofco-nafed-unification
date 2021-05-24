package com.agnext.unification.oauth2.configuration;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.filter.OncePerRequestFilter;

import com.agnext.unification.controller.TwoFactorAuthenticationController;
import com.agnext.unification.service.UserService;

/**
 * Stores the oauth authorizationRequest in the session so that it can later be
 * picked by the {@link com.example.CustomOAuth2RequestFactory} to continue with
 * the authorization flow.
 */
public class TwoFactorAuthenticationFilter extends OncePerRequestFilter {
	
	 private static final Logger logger =
	            LoggerFactory.getLogger(TwoFactorAuthenticationFilter.class);

	    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	    private OAuth2RequestFactory oAuth2RequestFactory;

	    // These next two are added as a test to avoid the compilation errors that happened when they
	    // were not defined.
	    public static final String ROLE_TWO_FACTOR_AUTHENTICATED = "ROLE_TWO_FACTOR_AUTHENTICATED";
	    public static final String ROLE_TWO_FACTOR_AUTHENTICATION_ENABLED =
	            "ROLE_TWO_FACTOR_AUTHENTICATION_ENABLED";


	    @Autowired
	    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
	        oAuth2RequestFactory = new CustomOauth2RequestFactory(clientDetailsService);
	    }

	    private boolean twoFactorAuthenticationEnabled(
	            Collection<? extends GrantedAuthority> authorities) {
	        return authorities.stream().anyMatch(authority -> ROLE_TWO_FACTOR_AUTHENTICATION_ENABLED
	                .equals(authority.getAuthority()));
	    }



	    private Map<String, String> paramsFromRequest(HttpServletRequest request) {
	        logger.info("Inside paramsFromRequest method: started... ");
	        Map<String, String> params = new HashMap<>();
	        for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
	            params.put(entry.getKey(), entry.getValue()[0]);
	        }
	        return params;
	    }


	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
	            FilterChain filterChain) throws ServletException, IOException {
	        logger.info("Inside doFilterInternal method: started... ");
	        System.out.println("Query String @@@@@@@@@@@@"+request.getQueryString());
	        // Check if the user hasn't done the two factor authentication.
	        if (!isAnonymousUser() && isAuthenticated() && is2FARequired()) {
	            AuthorizationRequest authorizationRequest =
	                    oAuth2RequestFactory.createAuthorizationRequest(paramsFromRequest(request));
	            /*
	             * Check if the client's authorities (authorizationRequest.getAuthorities()) or the
	             * user's ones require two factor authentication.
	             */
	            /*
	             * if (twoFactorAuthenticationEnabled(authorizationRequest.getAuthorities()) ||
	             * twoFactorAuthenticationEnabled(SecurityContextHolder.getContext()
	             * .getAuthentication().getAuthorities())) {
	             */
	            // Save the authorizationRequest in the session. This allows the
	            // CustomOAuth2RequestFactory
	            // to return this saved request to the AuthenticationEndpoint after the user
	            // successfully
	            // did the two factor authentication.
	            request.getSession().setAttribute(
	                    CustomOauth2RequestFactory.SAVED_AUTHORIZATION_REQUEST_SESSION_ATTRIBUTE_NAME,
	                    authorizationRequest);
	            logger.debug("doFilterInternal(): redirecting to {}",
	                    TwoFactorAuthenticationController.PATH);

	            // redirect the the page where the user needs to enter the two factor authentication
	            // code
	            redirectStrategy.sendRedirect(request, response,
	                    TwoFactorAuthenticationController.PATH);
	            return;
	            // }
	        }

	        logger.debug("doFilterInternal(): without redirect.");

	        filterChain.doFilter(request, response);
	    }

	    public boolean isAuthenticated() {
	        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
	    }

	    @Autowired
	    private UserService userService;

	    private boolean is2FARequired() {
	    	return false;
	        /*return userService
	                .loadUserByUsername(
	                        SecurityContextHolder.getContext().getAuthentication().getName())
	                .getUser2faRequired();*/

	    }

	    private boolean isAnonymousUser() {

	        return (SecurityContextHolder.getContext()
	                .getAuthentication() instanceof AnonymousAuthenticationToken);
	    }

	    private boolean hasAuthority(String checkedAuthority) {


	        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
	                .anyMatch(authority -> checkedAuthority.equals(authority.getAuthority()));
	    }

}
