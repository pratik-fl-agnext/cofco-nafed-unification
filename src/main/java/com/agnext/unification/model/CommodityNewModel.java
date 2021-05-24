package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommodityNewModel {

	@JsonProperty("commodity_name")
	private String commodityName;

	@JsonProperty("commodity_id")
	private Long commodityId;

	@JsonProperty("devices")
	List<DeviceSerialNumber> devices;

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public Long getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}

	public List<DeviceSerialNumber> getDevices() {
		return devices;
	}

	public void setDevices(List<DeviceSerialNumber> devices) {
		this.devices = devices;
	}

}
