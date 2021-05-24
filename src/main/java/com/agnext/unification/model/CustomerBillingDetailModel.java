/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.model;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class CustomerBankDetailModel.
 */
@JsonIgnoreProperties
@JsonInclude(content = Include.NON_NULL)
public class CustomerBillingDetailModel {

	@JsonProperty("billing_id")
	private Long billingId;

	@JsonProperty("billing_account_number")
	private String billingAccountNumber;

	@JsonProperty("billing_address")
	private String billingAddress;

	@JsonProperty("billing_uuid")
	private String billingUuid;

	@JsonProperty("created_by")
	private Long createdBy;

	@Column(name="created_on")
	private Long createdOn;

	@JsonProperty("updated_by")
	private Long updatedBy;

	@JsonProperty("updated_on")
	private Long updatedOn;

	@JsonProperty("customer_id")
	private Long customerId;

	/**
	 * @return the billingId
	 */
	public Long getBillingId() {
		return billingId;
	}

	/**
	 * @param billingId the billingId to set
	 */
	public void setBillingId(Long billingId) {
		this.billingId = billingId;
	}

	/**
	 * @return the billingAccountNumber
	 */
	public String getBillingAccountNumber() {
		return billingAccountNumber;
	}

	/**
	 * @param billingAccountNumber the billingAccountNumber to set
	 */
	public void setBillingAccountNumber(String billingAccountNumber) {
		this.billingAccountNumber = billingAccountNumber;
	}

	/**
	 * @return the billingAddress
	 */
	public String getBillingAddress() {
		return billingAddress;
	}

	/**
	 * @param billingAddress the billingAddress to set
	 */
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	/**
	 * @return the billingUuid
	 */
	public String getBillingUuid() {
		return billingUuid;
	}

	/**
	 * @param billingUuid the billingUuid to set
	 */
	public void setBillingUuid(String billingUuid) {
		this.billingUuid = billingUuid;
	}

	/**
	 * @return the createdBy
	 */
	public Long getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdOn
	 */
	public Long getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the updatedBy
	 */
	public Long getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedOn
	 */
	public Long getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * @return the customerId
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
}
