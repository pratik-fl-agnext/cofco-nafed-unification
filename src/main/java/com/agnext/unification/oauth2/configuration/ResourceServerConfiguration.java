package com.agnext.unification.oauth2.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
//@EnableResourceServer
//@EnableConfigurationProperties(RSecurityProperties.class)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	

	@Override
	public void configure(HttpSecurity http) throws Exception { // @formatter:off
		/*
		 * http .authorizeRequests() .antMatchers("/api/**").authenticated()
		 * .antMatchers("/").permitAll();
		 */
		
		  http.requestMatchers() .antMatchers("/login", "/oauth/authorize",
		  "/secure/two_factor_authentication")
		  .and().authorizeRequests().anyRequest().authenticated().and().formLogin().
		  loginPage("/login") .permitAll();
		  
		  http.authorizeRequests()
		  .antMatchers("/login", "/oauth/authorize").permitAll()
		  .anyRequest().authenticated();
		 
	}
	
}
