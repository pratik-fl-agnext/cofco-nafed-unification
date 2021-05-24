package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(content = Include.NON_EMPTY)
public class UserCountModel {

	@JsonProperty("total_users")
	private Long totalUsers;
	
	@JsonProperty("service_provider_users")
	private Long serviceProviderUsers;
	
	@JsonProperty("total_customer_users")
	private Long totalCustomerUsers;
	
	@JsonProperty("total_partners_users")
	private Long totalPartnersUsers;

	public Long getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(Long totalUsers) {
		this.totalUsers = totalUsers;
	}

	public Long getServiceProviderUsers() {
		return serviceProviderUsers;
	}

	public void setServiceProviderUsers(Long serviceProviderUsers) {
		this.serviceProviderUsers = serviceProviderUsers;
	}

	public Long getTotalCustomerUsers() {
		return totalCustomerUsers;
	}

	public void setTotalCustomerUsers(Long totalCustomerUsers) {
		this.totalCustomerUsers = totalCustomerUsers;
	}

	public Long getTotalPartnersUsers() {
		return totalPartnersUsers;
	}

	public void setTotalPartnersUsers(Long totalPartnersUsers) {
		this.totalPartnersUsers = totalPartnersUsers;
	}
	
	
}
