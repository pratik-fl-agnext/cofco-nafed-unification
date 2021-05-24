package com.agnext.unification.oauth2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import com.agnext.unification.repository.nafed.OauthClientEntityRepository;

@Service
public class OauthClientDetailService implements ClientDetailsService {

    @Autowired

    OauthClientEntityRepository repo;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return new OauthClient(repo.findById(clientId).get());
    }

}
