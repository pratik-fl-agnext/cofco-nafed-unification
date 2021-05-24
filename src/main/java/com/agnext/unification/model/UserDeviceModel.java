package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class UserDeviceModel {


	@JsonProperty("device_token")
	private String deviceToken;
	
	@JsonProperty("user_device_token_id")
	private Long userDeviceTokenId;
	
	@JsonProperty("user_id")
	private Long userId;
	
	@JsonProperty("created_on")
	private Long created_on;
	
	@JsonProperty("modified_on")
	private Long modifiedOn;

	/**
	 * @return the deviceToken
	 */
	public String getDeviceToken() {
		return deviceToken;
	}

	/**
	 * @param deviceToken the deviceToken to set
	 */
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	/**
	 * @return the userDeviceTokenId
	 */
	public Long getUserDeviceTokenId() {
		return userDeviceTokenId;
	}

	/**
	 * @param userDeviceTokenId the userDeviceTokenId to set
	 */
	public void setUserDeviceTokenId(Long userDeviceTokenId) {
		this.userDeviceTokenId = userDeviceTokenId;
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

	/**
	 * @return the created_on
	 */
	public Long getCreated_on() {
		return created_on;
	}

	/**
	 * @param created_on the created_on to set
	 */
	public void setCreated_on(Long created_on) {
		this.created_on = created_on;
	}

	/**
	 * @return the modifiedOn
	 */
	public Long getModifiedOn() {
		return modifiedOn;
	}

	/**
	 * @param modifiedOn the modifiedOn to set
	 */
	public void setModifiedOn(Long modifiedOn) {
		this.modifiedOn = modifiedOn;
	}


	@Override
	public String toString() {
		return "UserDeviceModel{" +
				"deviceToken='" + deviceToken + '\'' +
				", userDeviceTokenId=" + userDeviceTokenId +
				", userId=" + userId +
				", created_on=" + created_on +
				", modifiedOn=" + modifiedOn +
				'}';
	}
}
