package com.agnext.unification.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.agnext.unification.assembler.EntityToVOAssembler;
import com.agnext.unification.common.Constants;
import com.agnext.unification.entity.nafed.DcmUserDevice;
import com.agnext.unification.model.AuthenticationModel;
import com.agnext.unification.model.LoginResponseModel;
import com.agnext.unification.model.PermissionModel;
import com.agnext.unification.model.RoleModel;
import com.agnext.unification.model.UserDeviceModel;
import com.agnext.unification.model.UserModel;
import com.agnext.unification.repository.nafed.DcmUserDeviceRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class AuthenticationService extends GenericService {

	private static Logger logger = LoggerFactory.getLogger(com.agnext.unification.service.AuthenticationService.class);

	@Value("${iam.url.access}")
	private String accessUrl;
	
	@Value("${iam.url.userAccess}")
	private String userAccessUrl;
	
	@Autowired
	DcmUserDeviceRepository dcmUserDeviceRepository;

	public LoginResponseModel authorizeUserWithCode(String code, String bearer, UserDeviceModel userDeviceModel) {
		logger.debug("authenticate user by access code");
		logger.debug("Code is : " + code);
		logger.debug("bearer is :" + bearer);
		logger.debug("userDeviceModel is : " + userDeviceModel);
		RestTemplate restTemplate = new RestTemplate();
		AuthenticationModel response = restTemplate.postForObject(accessUrl + code, marshalBasicAuth(bearer), AuthenticationModel.class);
		logger.debug("response : {}", response);
		if (response.getAccessToken() != null && response.getUserId() != null) {
			ResponseEntity<UserModel> userResponse = restTemplate.exchange(
				userAccessUrl + response.getUserId(), HttpMethod.GET,
					marshalAuthentication(response.getAccessToken()), UserModel.class);
			logger.debug("user Response :{}", userResponse);
			addUserDetailAndPermissions(response, userResponse.getBody());
		}
		if (userDeviceModel != null) {
			if (!userDeviceModel.getDeviceToken().isEmpty() && userDeviceModel.getDeviceToken() != null) {
				if (Constants.BearerType.MOBILE.equalsIgnoreCase(bearer)) {
					DcmUserDevice dcmUserDevice = dcmUserDeviceRepository
							.findByUserId(Long.valueOf(response.getUserId()));
					if (dcmUserDevice == null) {
						dcmUserDevice = new DcmUserDevice();
						dcmUserDevice.setCreatedBy(Long.valueOf(response.getUserId()));
						dcmUserDevice.setCreatedOn(Instant.now().getEpochSecond());
					} else {
						dcmUserDevice.setModifiedBy(Long.valueOf(response.getUserId()));
						dcmUserDevice.setModifiedOn(Instant.now().getEpochSecond());
					}

					dcmUserDevice.setDeviceToken(userDeviceModel.getDeviceToken());
					dcmUserDevice.setUserId(Long.valueOf(response.getUserId()));
					dcmUserDeviceRepository.save(dcmUserDevice);
				}
			}
		}

		return EntityToVOAssembler.convertLoginResponse(response);

	}

	private void addUserDetailAndPermissions(AuthenticationModel response, UserModel user) {
		if (user != null) {
			response.setPermissions(addPermissions(user));
			// user.setRoleList(null);
			List<RoleModel> getRoleList=user.getRoleList();
			 user.setRoles(getRoleCodes(getRoleList));
			user.setUser2faRequired(null);
			user.setRoleList(null);
			response.setUser(user);
		}

	}

	private List<String> getRoleCodes(List<RoleModel> list) {
		List<String> roleCodes = new ArrayList<>();
		for (RoleModel roles : list) {
			roleCodes.add(roles.getRoleCode());
		}
		return roleCodes;
	}

	private List<String> addPermissions(UserModel user) {
		List<String> response = new ArrayList<>();
		if (user != null) {
			for (RoleModel role : user.getRoleList()) {
				for (PermissionModel permissions : role.getPermissions()) {
					response.add(permissions.getPermissionCode());
				}
			}
		}

		return response;
	}

	private HttpEntity<String> marshalBasicAuth(String bearer) {
		String plainCreds = null;
		if (Constants.BearerType.MOBILE.equalsIgnoreCase(bearer))
			plainCreds = Constants.CLIENT_ID_MOBILE + ":" + Constants.PASSWORD_MOBILE;
		else
			plainCreds = Constants.CLIENT_ID_WEB + ":" + Constants.PASSWORD_WEB;
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		HttpHeaders headers = new HttpHeaders();
		headers.add(Constants.AUTHORIZATION, Constants.BASIC + " " + base64Creds);

		return new HttpEntity<String>(headers);
	}

	private HttpEntity<String> marshalAuthentication(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(Constants.AUTHORIZATION, Constants.BEARER + " " + token);

		return new HttpEntity<String>(headers);
	}

}
