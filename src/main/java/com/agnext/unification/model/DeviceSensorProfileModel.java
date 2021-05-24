package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceSensorProfileModel {
	@JsonProperty("device_sensor_profile_id")
	private Long deviceSensorProfileId;

	@JsonProperty("device_sensor_profile_desc")
	private String deviceSensorProfileDesc;

	@JsonProperty("device_sensor_profile_code")
	private String deviceSensorProfileCode;

	public Long getDeviceSensorProfileId() {
		return deviceSensorProfileId;
	}

	public void setDeviceSensorProfileId(Long deviceSensorProfileId) {
		this.deviceSensorProfileId = deviceSensorProfileId;
	}

	public String getDeviceSensorProfileDesc() {
		return deviceSensorProfileDesc;
	}

	public void setDeviceSensorProfileDesc(String deviceSensorProfileDesc) {
		this.deviceSensorProfileDesc = deviceSensorProfileDesc;
	}

	public String getDeviceSensorProfileCode() {
		return deviceSensorProfileCode;
	}

	public void setDeviceSensorProfileCode(String deviceSensorProfileCode) {
		this.deviceSensorProfileCode = deviceSensorProfileCode;
	}
	
	

}
