package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceSerialNumber {

	@JsonProperty("device_serial_no")
	private String deviceSerialNumber;
//	private String deviceType;
	
	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}
	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}
//	public String getDeviceType() {
//		return deviceType;
//	}
//	public void setDeviceType(String deviceType) {
//		this.deviceType = deviceType;
//	}
}
