package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(content = Include.NON_NULL)
public class CustomerCountModel {
	
	@JsonProperty("total_customer")
	private Long totalCustomers;
	
	@JsonProperty("total_partners")
	private Long totalPartners;
	
	@JsonProperty("customers_under_partners")
	private Long customersUnderPartners;

	public Long getTotalCustomers() {
		return totalCustomers;
	}

	public void setTotalCustomers(Long totalCustomers) {
		this.totalCustomers = totalCustomers;
	}

	public Long getTotalPartners() {
		return totalPartners;
	}

	public void setTotalPartners(Long totalPartners) {
		this.totalPartners = totalPartners;
	}

	public Long getCustomersUnderPartners() {
		return customersUnderPartners;
	}

	public void setCustomersUnderPartners(Long customersUnderPartners) {
		this.customersUnderPartners = customersUnderPartners;
	}
	
}
