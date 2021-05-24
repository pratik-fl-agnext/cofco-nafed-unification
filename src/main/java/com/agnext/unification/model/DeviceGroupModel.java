package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceGroupModel {

	@JsonProperty("device_group_id")
	private Long deviceGroupId;

	@JsonProperty("device_group_desc")
	private String deviceGroupDesc;

	@JsonProperty("device_group_code")
	private String deviceGroupCode;

//	@JsonProperty("status_id")
//	private String statusId;
//
//	@JsonProperty("status_desc")
//	private String statusDesc;

	public Long getDeviceGroupId() {
		return deviceGroupId;
	}

	public void setDeviceGroupId(Long deviceGroupId) {
		this.deviceGroupId = deviceGroupId;
	}

	public String getDeviceGroupDesc() {
		return deviceGroupDesc;
	}

	public void setDeviceGroupDesc(String deviceGroupDesc) {
		this.deviceGroupDesc = deviceGroupDesc;
	}

	public String getDeviceGroupCode() {
		return deviceGroupCode;
	}

	public void setDeviceGroupCode(String deviceGroupCode) {
		this.deviceGroupCode = deviceGroupCode;
	}

//	public String getStatusId() {
//		return statusId;
//	}
//
//	public void setStatusId(String statusId) {
//		this.statusId = statusId;
//	}
//
//	public String getStatusDesc() {
//		return statusDesc;
//	}
//
//	public void setStatusDesc(String statusDesc) {
//		this.statusDesc = statusDesc;
//	}

}
