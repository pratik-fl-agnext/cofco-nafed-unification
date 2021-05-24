package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceSubTypeModel {
	
	@JsonProperty("device_sub_type_id")
	private Long deviceSubTypeId;

	@JsonProperty("device_sub_type_desc")
	private String deviceSubTypeDesc;

	@JsonProperty("device_sub_type_code")
	private String deviceSubTypepCode;

	public Long getDeviceSubTypeId() {
		return deviceSubTypeId;
	}

	public void setDeviceSubTypeId(Long deviceSubTypeId) {
		this.deviceSubTypeId = deviceSubTypeId;
	}

	public String getDeviceSubTypeDesc() {
		return deviceSubTypeDesc;
	}

	public void setDeviceSubTypeDesc(String deviceSubTypeDesc) {
		this.deviceSubTypeDesc = deviceSubTypeDesc;
	}

	public String getDeviceSubTypepCode() {
		return deviceSubTypepCode;
	}

	public void setDeviceSubTypepCode(String deviceSubTypepCode) {
		this.deviceSubTypepCode = deviceSubTypepCode;
	}
	
	
	

}
