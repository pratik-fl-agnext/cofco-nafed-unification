package com.agnext.unification.oauth2.configuration;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CustomOauth2RequestFactory extends DefaultOAuth2RequestFactory {

    private static final Logger logger = LoggerFactory.getLogger(CustomOauth2RequestFactory.class);

    public static final String SAVED_AUTHORIZATION_REQUEST_SESSION_ATTRIBUTE_NAME =
            "savedAuthorizationRequest";

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserDetailsService userDetailsService;

    public CustomOauth2RequestFactory(ClientDetailsService clientDetailsService) {
        super(clientDetailsService);
    }


    @Override
    public TokenRequest createTokenRequest(Map<String, String> requestParameters,
            ClientDetails authenticatedClient) {
        if (requestParameters.get("grant_type").equals("refresh_token")) {
            logger.info("Inside createTokenRequest method: started...");
            OAuth2Authentication authentication = tokenStore.readAuthenticationForRefreshToken(
                    tokenStore.readRefreshToken(requestParameters.get("refresh_token")));
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(authentication.getName(), null,
                            userDetailsService.loadUserByUsername(authentication.getName())
                                    .getAuthorities()));
        }
        return super.createTokenRequest(requestParameters, authenticatedClient);
    }

    @Override
    public AuthorizationRequest createAuthorizationRequest(
            Map<String, String> authorizationParameters) {
        logger.info("Inside createAuthorizationRequest method: started...");

        try {
            HttpSession session =
                    ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                            .getRequest().getSession(false);
            if (session != null) {
                AuthorizationRequest authorizationRequest = (AuthorizationRequest) session
                        .getAttribute(SAVED_AUTHORIZATION_REQUEST_SESSION_ATTRIBUTE_NAME);
                if (authorizationRequest != null) {
                    session.removeAttribute(SAVED_AUTHORIZATION_REQUEST_SESSION_ATTRIBUTE_NAME);
                    logger.debug("createAuthorizationRequest(): return saved copy.");
                    return authorizationRequest;
                }
            }
        } catch (IllegalStateException e) {
            logger.error("Exception caught in createAuthorizationRequest and category is : "
                    + e.getMessage());
            e.printStackTrace();
        }

        logger.debug("createAuthorizationRequest(): create");
        return super.createAuthorizationRequest(authorizationParameters);
    }

}
