package com.agnext.unification.oauth2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agnext.unification.entity.nafed.UserEntity;
import com.agnext.unification.repository.nafed.UserRepository;


@Service
public class IAMUserDetailService implements UserDetailsService{

	@Autowired
	UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user = repository.findByUserEmail(username);
		//UserEntity user = new UserEntity();
		//user.setUserName(username);
		//user.setPassword("{bcrypt}$2a$10$OloCjtlsxEaqZhG9C6/SrOQra2FCcjra75NGaaS9HskjnPrs.feLu");
		if (user != null) {
			return new IAMUserDetails(user);
		}
		
		throw new UsernameNotFoundException("Invalid Email or Password");
	}

}
