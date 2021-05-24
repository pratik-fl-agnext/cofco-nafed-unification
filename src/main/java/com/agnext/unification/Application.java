package com.agnext.unification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

// TODO: Auto-generated Javadoc
/**
 * The Class Application.
 */
@SpringBootApplication
@EnableAuthorizationServer
//@EnableResourceServer
public class Application{

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}
