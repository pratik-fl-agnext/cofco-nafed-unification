package com.agnext.unification.oauth2.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	


    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfiguration.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;
    
    @Autowired
    AuthenticationHandler successHandler;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        logger.info("Inside configuremethod: started...");
        CustOAuth2AuthenticationProcessingFilter oAuth2AuthenticationProcessingFilter =
                new CustOAuth2AuthenticationProcessingFilter();
        OAuth2AuthenticationManager omanager = new OAuth2AuthenticationManager();
        omanager.setTokenServices(tokenServices());
        omanager.setClientDetailsService(clientDetailsService);
        //oAuth2AuthenticationProcessingFilter.setAuthenticationManager(authenticationManagerBean());
        oAuth2AuthenticationProcessingFilter.setAuthenticationManager(omanager);
        http.csrf().disable().formLogin().loginPage("/login").successHandler(successHandler)
                .failureHandler(authenticationFailureHandler())
                 .and().logout()
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler()).and().authorizeRequests()
                .antMatchers("/login", "/newLogin", "/oauth/authorize", "/oauth/token",
                        "/secure/two_factor_authentication", "/oauth/check_token", "/error", "/","/arya/token","arya/token")
                .permitAll().antMatchers("/api/**").authenticated().and()
                .addFilterBefore(oAuth2AuthenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new IAMAuthenticationFailureHandler();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    ResourceServerTokenServices tokenServices() {
        logger.info("Inside tokenServices: started...");
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetailsService);
        return tokenServices;
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new IAMLogoutSuccessHandler();
    }


}
