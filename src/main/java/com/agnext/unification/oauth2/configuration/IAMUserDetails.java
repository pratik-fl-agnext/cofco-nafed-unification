package com.agnext.unification.oauth2.configuration;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.agnext.unification.entity.nafed.UserEntity;

public class IAMUserDetails implements UserDetails{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  UserEntity user;

	    public IAMUserDetails(final UserEntity user) {
	            this.user = user;
	    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	public UserEntity getUser() {
		return user;
	}
}
