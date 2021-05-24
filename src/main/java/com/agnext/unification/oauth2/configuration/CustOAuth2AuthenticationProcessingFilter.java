package com.agnext.unification.oauth2.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetailsSource;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.Assert;

/**
 * A pre-authentication filter for OAuth2 protected resources. Extracts an OAuth2 token from the
 * incoming request and uses it to populate the Spring Security context with an
 * {@link OAuth2Authentication} (if used in conjunction with an
 * {@link OAuth2AuthenticationManager}).
 * 
 * @author PK
 * 
 */
public class CustOAuth2AuthenticationProcessingFilter implements Filter, InitializingBean {

    private static final Log logger = LogFactory.getLog(CustOAuth2AuthenticationProcessingFilter.class);

    private AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();

    private AuthenticationManager authenticationManager;

    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource =
            new OAuth2AuthenticationDetailsSource();

    private TokenExtractor tokenExtractor = new BearerTokenExtractor();

    private AuthenticationEventPublisher eventPublisher = new NullEventPublisher();

    private boolean stateless = true;

    /**
     * Flag to say that this filter guards stateless resources (default true). Set this to true if
     * the only way the resource can be accessed is with a token. If false then an incoming cookie
     * can populate the security context and allow access to a caller that isn't an OAuth2 client.
     * 
     * @param stateless the flag to set (default true)
     */
    public void setStateless(boolean stateless) {
        this.stateless = stateless;
    }

    /**
     * Sets the authentication entry point.
     *
     * @param authenticationEntryPoint the authentication entry point to set
     */
    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    /**
     * Sets the authentication manager.
     *
     * @param authenticationManager the authentication manager to set (mandatory with no default)
     */
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Sets the token extractor.
     *
     * @param tokenExtractor the tokenExtractor to set
     */
    public void setTokenExtractor(TokenExtractor tokenExtractor) {
        this.tokenExtractor = tokenExtractor;
    }

    /**
     * Sets the authentication event publisher.
     *
     * @param eventPublisher the event publisher to set
     */
    public void setAuthenticationEventPublisher(AuthenticationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    /**
     * Sets the authentication details source.
     *
     * @param authenticationDetailsSource The AuthenticationDetailsSource to use
     */
    public void setAuthenticationDetailsSource(
            AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource) {
        Assert.notNull(authenticationDetailsSource, "AuthenticationDetailsSource required");
        this.authenticationDetailsSource = authenticationDetailsSource;
    }

    @Override
    public void afterPropertiesSet() {
        Assert.state(authenticationManager != null, "AuthenticationManager is required");
    }

    /**
     * Do filter.
     *
     * @param req the req
     * @param res the res
     * @param chain the chain
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        logger.info("Inside doFilter method: started... ");

        final boolean debug = logger.isDebugEnabled();
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        

        if (isApiUrl(request)) {
            try {

                Authentication authentication = tokenExtractor.extract(request);
                if (authentication == null) {
                    if (stateless && isAuthenticated()) {
                        if (debug) {
                            logger.debug("Clearing security context.");
                        }
                        SecurityContextHolder.clearContext();
                    }
                    if (debug) {
                        logger.debug("No token in request, will continue chain.");
                    }
                    throw new OAuth2Exception("Missing authorization token");
                } else {
                    request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE,
                            authentication.getPrincipal());
                    if (authentication instanceof AbstractAuthenticationToken) {
                        AbstractAuthenticationToken needsDetails =
                                (AbstractAuthenticationToken) authentication;
                        needsDetails.setDetails(authenticationDetailsSource.buildDetails(request));
                    }
                    Authentication authResult = authenticationManager.authenticate(authentication);

                    if (debug) {
                        logger.debug("Authentication success: " + authResult);
                    }
                    request.setAttribute("requestObject", authResult.getName());
                    eventPublisher.publishAuthenticationSuccess(authResult);
                    SecurityContextHolder.getContext().setAuthentication(authResult);

                }
            } catch (OAuth2Exception failed) {
                failed.printStackTrace();
                SecurityContextHolder.clearContext();

                if (debug) {
                    logger.debug("Authentication request failed: " + failed);
                }
                eventPublisher.publishAuthenticationFailure(
                        new BadCredentialsException(failed.getMessage(), failed),
                        new PreAuthenticatedAuthenticationToken("access-token", "N/A"));

                authenticationEntryPoint.commence(request, response,
                        new InsufficientAuthenticationException(failed.getMessage(), failed));

                return;
            }
        }
        logger.info("Inside doFilter method: ended... ");
        chain.doFilter(request, response);
    }

    /**
     * Checks if is auth url.
     *
     * @param request the request
     * @return true, if is auth url
     */
    private boolean isApiUrl(HttpServletRequest request) {
        logger.info("Inside isApiUrl method: started... ");
        String reqUri = request.getRequestURI();
        boolean isApiUrl = reqUri.contains("/api");
        if(reqUri.equalsIgnoreCase("/api/token")){
            isApiUrl = Boolean.FALSE;
        }
        System.out.println(isApiUrl + " reqUri" + reqUri);
        logger.info("API URL Method Ends reqUri:{} " + reqUri);
        logger.info("ISAPIURL Value : {}" + isApiUrl);
        return isApiUrl;
    }

    /**
     * Checks if is authenticated.
     *
     * @return true, if is authenticated
     */
    private boolean isAuthenticated() {
        logger.info("Inside isAuthenticated method: started... ");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return true;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    private static final class NullEventPublisher implements AuthenticationEventPublisher {
        @Override
        public void publishAuthenticationFailure(AuthenticationException exception,
                Authentication authentication) {}

        @Override
        public void publishAuthenticationSuccess(Authentication authentication) {}
    }

}
