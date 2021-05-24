package com.agnext.unification.oauth2.configuration;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.agnext.unification.entity.nafed.OauthClientEntity;

public class OauthClient implements ClientDetails {

    OauthClientEntity entity;

    public OauthClient(OauthClientEntity entity) {

    }

    @Override
    public String getClientId() {
        return entity.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        String arr[] = entity.getResourceIds().split(",");
        return new HashSet<>(Arrays.asList(arr));
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return entity.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        String idArr[] = entity.getScope().split(",");
        return new HashSet<>(Arrays.asList(idArr));
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        String idArr[] = entity.getAuthorizedGrantTypes().split(",");
        return new HashSet<>(Arrays.asList(idArr));
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        String idArr[] = entity.getWebServerRedirectUri().split(",");
        return new HashSet<>(Arrays.asList(idArr));
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        String idArr[] = entity.getWebServerRedirectUri().split(",");
        HashSet<String> authSet = new HashSet<>(Arrays.asList(idArr));

        authSet.forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r));
        });
        return authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return entity.getAccessTokenValidity();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return entity.getAccessTokenValidity();
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return new HashMap();
    }

}
