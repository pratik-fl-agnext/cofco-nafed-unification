package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDetailModel {

	@JsonProperty("user_id")
	private Long userId;

	@JsonProperty("user_email")
	private String userEmail;

	@JsonProperty("phone_serial_number")
	private String phoneSerialNumber;

	@JsonProperty("phone_version")
	private String phoneVersion;

	@JsonProperty("phone_brand")
	private String phoneBrand;

	@JsonProperty("device_commodity")
	private String deviceCommodity;

	@JsonProperty("selected_commodity")
	private String selectedCommodity;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPhoneSerialNumber() {
		return phoneSerialNumber;
	}

	public void setPhoneSerialNumber(String phoneSerialNumber) {
		this.phoneSerialNumber = phoneSerialNumber;
	}

	public String getPhoneVersion() {
		return phoneVersion;
	}

	public void setPhoneVersion(String phoneVersion) {
		this.phoneVersion = phoneVersion;
	}

	public String getPhoneBrand() {
		return phoneBrand;
	}

	public void setPhoneBrand(String phoneBrand) {
		this.phoneBrand = phoneBrand;
	}

	public String getDeviceCommodity() {
		return deviceCommodity;
	}

	public void setDeviceCommodity(String deviceCommodity) {
		this.deviceCommodity = deviceCommodity;
	}

	public String getSelectedCommodity() {
		return selectedCommodity;
	}

	public void setSelectedCommodity(String selectedCommodity) {
		this.selectedCommodity = selectedCommodity;
	}

	@Override
	public String toString() {
		return "UserDetailModel [userId=" + userId + ", userEmail=" + userEmail + ", phoneSerialNumber="
				+ phoneSerialNumber + ", phoneVersion=" + phoneVersion + ", phoneBrand=" + phoneBrand
				+ ", deviceCommodity=" + deviceCommodity + ", selectedCommodity=" + selectedCommodity + "]";
	}

}
