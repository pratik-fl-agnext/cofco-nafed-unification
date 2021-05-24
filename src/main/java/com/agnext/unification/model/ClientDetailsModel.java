package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientDetailsModel {
	
	@JsonProperty("client_id")
	private Long clientId;
	
	@JsonProperty("clientName")
	private String clientName;

	@JsonProperty("phone_number")
	private String phoneNumber;
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("customer_id")
	private Long customerId;
	
	@JsonProperty("customer_name")
	private String customerName;

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


}
