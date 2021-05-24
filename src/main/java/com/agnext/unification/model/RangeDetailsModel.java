package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RangeDetailsModel {
	
	

	
	@JsonProperty("client_details")
	private List<ClientModel> client;



	public List<ClientModel> getClient() {
		return client;
	}



	public void setClient(List<ClientModel> client) {
		this.client = client;
	}
	
	
}
