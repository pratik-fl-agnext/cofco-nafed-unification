package com.agnext.unification.config;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.agnext.unification.model.RoleModel;

@RequestScope
@Component
public class RequestContext {


  private String token;

  private String customerType;

  private Long customerId;

  private String customerUuid;

  private String userUuid;

  private Long userId;

  private List<RoleModel> listRoles;

	private Set<String> roles;

  private Long tokenValidTill;

  private String userHierarchy;

  private String accessToken;

  private String userName;
  private String customerName;
  private String correlationId;
  private Set<String> permissions;
  private Set<String> allowedIps;
  private String userEmail;
  private String userMobile;
  private String deviceSerialNumber;
  private Long deviceId;
  private String requestURL;
  
  private Long stateAdmin;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getCorrelationId() {
    return correlationId;
  }

  public void setCorrelationId(String correlationId) {
    this.correlationId = correlationId;
  }

  public Set<String> getPermissions() {
    return permissions;
  }

  public void setPermissions(Set<String> permissions) {
    this.permissions = permissions;
  }

  public Set<String> getAllowedIps() {
    return allowedIps;
  }

  public void setAllowedIps(Set<String> allowedIps) {
    this.allowedIps = allowedIps;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getUserMobile() {
    return userMobile;
  }

  public void setUserMobile(String userMobile) {
    this.userMobile = userMobile;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getUserHierarchy() {
    return userHierarchy;
  }

  public void setUserHierarchy(String userHierarchy) {
    this.userHierarchy = userHierarchy;
  }

  public String getUserUuid() {
    return userUuid;
  }

  public void setUserUuid(String userUuid) {
    this.userUuid = userUuid;
  }

  public String getCustomerUuid() {
    return customerUuid;
  }

  public void setCustomerUuid(String customerUuid) {
    this.customerUuid = customerUuid;
  }

  /**
   * @return the token
   */
  public String getToken() {
    return token;
  }

  /**
   * @param token the token to set
   */
  public void setToken(String token) {
    this.token = token;
  }

  /**
   * @return the customerType
   */
  public String getCustomerType() {
    return customerType;
  }

  /**
   * @param customerType the customerType to set
   */
  public void setCustomerType(String customerType) {
    this.customerType = customerType;
  }

  /**
   * @return the customerId
   */
  public Long getCustomerId() {
    return customerId;
  }

  /**
   * @param customerId the customerId to set
   */
  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  /**
   * @return the userId
   */
  public Long getUserId() {
    return userId;
  }

  /**
   * @param userId the userId to set
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

	public List<RoleModel> getListRoles() {
		return listRoles;
	}

	public void setListRoles(List<RoleModel> listRoles) {
		this.listRoles = listRoles;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	/**
   * @return the tokenValidTill
   */
  public Long getTokenValidTill() {
    return tokenValidTill;
  }

  /**
   * @param tokenValidTill the tokenValidTill to set
   */
  public void setTokenValidTill(Long tokenValidTill) {
    this.tokenValidTill = tokenValidTill;
  }

	@Override
	public String toString() {
		return "RequestContext{" +
				"token='" + token + '\'' +
				", customerType='" + customerType + '\'' +
				", customerId=" + customerId +
				", customerUuid='" + customerUuid + '\'' +
				", userUuid='" + userUuid + '\'' +
				", userId=" + userId +
				", listRoles=" + listRoles +
				", roles=" + roles +
				", tokenValidTill=" + tokenValidTill +
				", userHierarchy='" + userHierarchy + '\'' +
				", accessToken='" + accessToken + '\'' +
				", userName='" + userName + '\'' +
				", customerName='" + customerName + '\'' +
				", correlationId='" + correlationId + '\'' +
				", permissions=" + permissions +
				", allowedIps=" + allowedIps +
				", userEmail='" + userEmail + '\'' +
				", userMobile='" + userMobile + '\'' +
				'}';
	}

	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}

	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Long getStateAdmin() {
		return stateAdmin;
	}

	public void setStateAdmin(Long stateAdmin) {
		this.stateAdmin = stateAdmin;
	}

	public String getRequestURL() {
	    return requestURL;
	}

	public void setRequestURL(String requestURL) {
	    this.requestURL = requestURL;
	}
	
	
}
