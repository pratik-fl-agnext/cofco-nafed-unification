package com.agnext.unification.oauth2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import com.agnext.unification.entity.nafed.UserEntity;
import com.agnext.unification.repository.nafed.UserRepository;


@Component
public class ActiveUsers {
	  @Autowired
	    UserRepository userRepo;

	    public String getUserName(OAuth2Authentication authentication) {
	        return authentication.getName();
	    }

	    public Long getUserId(OAuth2Authentication authentication) {
	    	UserEntity user =userRepo.findByUserEmail(authentication.getName());
	        return user.getUserId();
	    }

//	    public Long getClientId(JwtAuthenticationToken token) {
//	        UserContext userContext = (UserContext) token.getPrincipal();
//	        return userContext.getClientId();
//	    }

}
