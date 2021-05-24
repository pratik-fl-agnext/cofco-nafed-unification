package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationListModel {
	
	@JsonProperty("locations_by_state")
	private List<LocationModel> locationsByState;
	
	@JsonProperty("size")
	private Integer size;

	public List<LocationModel> getLocationsByState() {
		return locationsByState;
	}

	public void setLocationsByState(List<LocationModel> locationsByState) {
		this.locationsByState = locationsByState;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	

}
