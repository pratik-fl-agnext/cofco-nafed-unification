package com.agnext.unification.oauth2.configuration;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	
	 private static final Logger logger = LoggerFactory.getLogger(AuthorizationServerConfiguration.class);
    

   // @Value("${check-user-scopes}")
    private Boolean checkUserScopes;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    /**
     * Request factory.
     *
     * @return the o auth 2 request factory
     */
    @Bean
    public OAuth2RequestFactory requestFactory() {
        CustomOauth2RequestFactory requestFactory =
                new CustomOauth2RequestFactory(clientDetailsService);
        requestFactory.setCheckUserScopes(true);
        return requestFactory;
    }

    /**
     * Token store.
     *
     * @return the token store
     */
    @Bean
    public TokenStore tokenStore() {
         return new JwtTokenStore(jwtAccessTokenConverter());
       // return new P7JwtTokenStore(dataSource, jwtAccessTokenConverter());
    }

    /**
     * Jwt access token converter.
     *
     * @return the jwt access token converter
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        logger.info("Inside jwtAccessTokenConverter method: started... ");
        JwtAccessTokenConverter converter = new CustomTokenEnhancer();
        converter.setKeyPair(
                new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "password".toCharArray())
                        .getKeyPair("jwt"));
        return converter;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.oauth2.config.annotation.web.configuration.
     * AuthorizationServerConfigurerAdapter#configure(org.springframework.security.oauth2.config.
     * annotation.configurers.ClientDetailsServiceConfigurer)
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder());
    }


    /**
     * Token endpoint authentication filter.
     *
     * @return the token endpoint authentication filter
     */
    @Bean
    public TokenEndpointAuthenticationFilter tokenEndpointAuthenticationFilter() {
        return new TokenEndpointAuthenticationFilter(authenticationManager, requestFactory());
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.oauth2.config.annotation.web.configuration.
     * AuthorizationServerConfigurerAdapter#configure(org.springframework.security.oauth2.config.
     * annotation.web.configurers.AuthorizationServerSecurityConfigurer)
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.oauth2.config.annotation.web.configuration.
     * AuthorizationServerConfigurerAdapter#configure(org.springframework.security.oauth2.config.
     * annotation.web.configurers.AuthorizationServerEndpointsConfigurer)
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        logger.info("Inside configure method: started... ");
        endpoints.tokenStore(tokenStore()).tokenEnhancer(jwtAccessTokenConverter())
                .authenticationManager(authenticationManager).userDetailsService(userDetailsService)
                .approvalStore(approvalStore())
                .authorizationCodeServices(authorizationCodeServices()).reuseRefreshTokens(true);
		/*
		 * if (checkUserScopes) endpoints.requestFactory(requestFactory());
		 */

		/*
		 * endpoints.exceptionTranslator(exception -> { if (exception instanceof
		 * OAuth2Exception) { ExceptionResponse exceptionResponse = new
		 * ExceptionResponse(new Date(), exception.getMessage(), ((OAuth2Exception)
		 * exception).getHttpErrorCode()); return new ResponseEntity(exceptionResponse,
		 * HttpStatus.UNAUTHORIZED); } else { throw exception; } });
		 */
    }

    /**
     * Two factor authentication filter registration.
     *
     * @return the filter registration bean
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Bean
    public FilterRegistrationBean twoFactorAuthenticationFilterRegistration() {
        logger.info("Inside twoFactorAuthenticationFilterRegistration method: started... ");
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(twoFactorAuthenticationFilter());
        registration.addUrlPatterns("/oauth/authorize");
        registration.setName("twoFactorAuthenticationFilter");
        return registration;
    }

    /**
     * Two factor authentication filter.
     *
     * @return the two factor authentication filter
     */
    @Bean
    public TwoFactorAuthenticationFilter twoFactorAuthenticationFilter() {
        return new TwoFactorAuthenticationFilter();
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }


}
