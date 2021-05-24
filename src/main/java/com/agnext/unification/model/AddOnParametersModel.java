package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddOnParametersModel {

	@JsonProperty("customer_id")
	private Long customerId;
	
	

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = 183L;
	}
	
	
}
