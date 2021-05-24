package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScanLocationModel {

	@JsonProperty("location_id")
	private Long locationId;
	
	@JsonProperty("location_name")
	private String locationName;
	
	@JsonProperty("warehouse_name")
	private String warehouseName;

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	
	
}
