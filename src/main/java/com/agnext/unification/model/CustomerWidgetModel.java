package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(content = Include.NON_NULL)
public class CustomerWidgetModel {
	
	@JsonProperty("all_customer_details")
	private List<CustomerModel> customerDetails;
	
	@JsonProperty("partner_details")
	private List<CustomerModel> partnersDetails;
	
	@JsonProperty("customers_under_partners")
	private List<CustomerModel> customerUnderPartners;

	public List<CustomerModel> getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(List<CustomerModel> customerDetails) {
		this.customerDetails = customerDetails;
	}

	public List<CustomerModel> getPartnersDetails() {
		return partnersDetails;
	}

	public void setPartnersDetails(List<CustomerModel> partnersDetails) {
		this.partnersDetails = partnersDetails;
	}

	public List<CustomerModel> getCustomerUnderPartners() {
		return customerUnderPartners;
	}

	public void setCustomerUnderPartners(List<CustomerModel> customerUnderPartners) {
		this.customerUnderPartners = customerUnderPartners;
	}
	
	

}
