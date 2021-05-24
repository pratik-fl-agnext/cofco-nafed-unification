package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientModel {

	@JsonProperty("client_id")
	private Long clientId;
	
	@JsonProperty("client_name")
	private String clientName;
	
	@JsonProperty("locations")
	private List<ScanLocationModel> locations;

	public Long getClientId() {
		return clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public List<ScanLocationModel> getLocations() {
		return locations;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void setLocations(List<ScanLocationModel> locations) {
		this.locations = locations;
	}
	
	
	
}
