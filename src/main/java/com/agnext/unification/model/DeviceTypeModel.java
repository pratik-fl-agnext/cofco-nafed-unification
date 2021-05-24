package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceTypeModel {
	
	@JsonProperty("device_type_id")
	private Long deviceTypeId ;
	
	
	@JsonProperty("device_type_desc")
	private String deviceTypeDesc;
	

	/**
	 * @return the deviceTypeId
	 */
	public Long getDeviceTypeId() {
		return deviceTypeId;
	}

	/**
	 * @param deviceTypeId the deviceTypeId to set
	 */
	public void setDeviceTypeId(Long deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	 

	/**
	 * @return the deviceTypeDesc
	 */
	public String getDeviceTypeDesc() {
		return deviceTypeDesc;
	}

	/**
	 * @param deviceTypeDesc the deviceTypeDesc to set
	 */
	public void setDeviceTypeDesc(String deviceTypeDesc) {
		this.deviceTypeDesc = deviceTypeDesc;
	}

}
