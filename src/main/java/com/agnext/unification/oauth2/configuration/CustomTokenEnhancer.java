package com.agnext.unification.oauth2.configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.agnext.unification.entity.nafed.RoleEntity;
import com.agnext.unification.entity.nafed.UserEntity;
import com.agnext.unification.repository.nafed.UserRepository;


@SuppressWarnings("deprecation")
public class CustomTokenEnhancer extends JwtAccessTokenConverter
        implements JwtAccessTokenConverterConfigurer {

    private static final String CUSTOMER = "customer";

    private static final Logger logger = LoggerFactory.getLogger(CustomTokenEnhancer.class);
    
    @Autowired
    UserRepository userRepository;


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
            OAuth2Authentication authentication) {
        logger.info("Inside enhance method: started... ");
        
        UserEntity user = null;
        if (authentication.getPrincipal() instanceof UserEntity) {
            user = (UserEntity) authentication.getPrincipal();
           
        } if (authentication.getPrincipal() instanceof IAMUserDetails) {
        	user = userRepository.findByUserEmail(((IAMUserDetails) authentication.getPrincipal()).getUser().getUserEmail());
        }
        
        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
        customAccessToken.setAdditionalInformation(buildClaims(accessToken,user));

        return super.enhance(customAccessToken, authentication);
    }

    private Map<String, Object> buildClaims(OAuth2AccessToken accessToken, UserEntity user) {
    	
    	Map<String, Object> claim =
                new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());
    	 if(user !=null) {
    		claim.put(TokenClaim.USERUUID, user.getUserUuid());
         	claim.put(TokenClaim.USERID, user.getUserId());
         	claim.put(TokenClaim.USEREMAIL, user.getUserEmail());
         	claim.put(TokenClaim.USERMOBILE, user.getUserContactNumber());
         	claim.put(TokenClaim.USERHIERARCHY, user.getUserHierarchy());
         	claim.put(TokenClaim.USERFIRSTNAME, user.getUserFirstName());
         	claim.put(TokenClaim.USERLASTNAME,  user.getUserLastName());
         	claim.put(TokenClaim.USERTYPE, user.getCustomer().getCustomerType().getCustomerType());
         	claim.put(TokenClaim.CUSTOMERUUID, user.getCustomer().getCustomerUuid());
         	claim.put(TokenClaim.CUSTOMERNAME, user.getCustomer().getCustomerName());
         	claim.put(TokenClaim.CUSTOMERID, user.getCustomer().getCustomerId());
         	claim.put(TokenClaim.ROLES, getRoles(user.getRoles()));
         	claim.put(TokenClaim.ISSUER, "Qualix");
         }
		return claim;
	}

	private List<String> getRoles(List<RoleEntity> roles) {
    	List<String> response = new ArrayList<String>();
    	if(roles!=null) {
    		for(RoleEntity role:roles) {
    			response.add(role.getRoleCode());
    		}
    	}
		return response;
	}

	public void configure(JwtAccessTokenConverter converter) {
        converter.setAccessTokenConverter(this);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        logger.info("Inside extractAuthentication method: started... ");
        OAuth2Authentication authentication = super.extractAuthentication(map);
        return authentication;
    }
}
