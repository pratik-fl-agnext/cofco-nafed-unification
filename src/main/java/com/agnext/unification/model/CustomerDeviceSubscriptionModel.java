package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class CustomerDeviceSubscriptionModel {

	@JsonProperty("customer_id")
	private Long customerId;

	@JsonProperty("device_type_details")
	private List<DeviceTypeModel> deviceTypeModel;

	@JsonProperty("device_type_id")
	private Long deviceTypeId;

	@JsonProperty("device_type_desc")
	private String deviceTypeDesc;

	@JsonProperty("user_id")
	private Long userId;

	@JsonProperty("device_id")
	private Long deviceId;

	@JsonProperty("device_serial_no")
	private String deviceSerialNumber;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(Long deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getDeviceTypeDesc() {
		return deviceTypeDesc;
	}

	public void setDeviceTypeDesc(String deviceTypeDesc) {
		this.deviceTypeDesc = deviceTypeDesc;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}

	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}

	public List<DeviceTypeModel> getDeviceTypeModel() {
		return deviceTypeModel;
	}

	public void setDeviceTypeModel(List<DeviceTypeModel> deviceTypeModel) {
		this.deviceTypeModel = deviceTypeModel;
	}

	
}
