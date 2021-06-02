package com.agnext.unification.entity.cofco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "phone_serial_number")
	private String phoneSerialNumber;

	@Column(name = "phone_version")
	private String phoneVersion;

	@Column(name = "phone_brand")
	private String phoneBrand;

	@Column(name = "device_commodity")
	private String deviceCommodity;

	@Column(name = "selected_commodity")
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
}
