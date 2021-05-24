/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class CountryModel.
 */
@JsonIgnoreProperties
@JsonInclude(content = Include.NON_EMPTY)
public class StatusModel {

	@JsonProperty("status_id")
	private Long statusId;
	
	@JsonProperty("customer_id")
	private Long customerId;

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

}
