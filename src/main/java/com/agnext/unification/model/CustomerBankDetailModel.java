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
 * The Class CustomerBankDetailModel.
 */
@JsonIgnoreProperties
@JsonInclude(content = Include.NON_NULL)
public class CustomerBankDetailModel {

	@JsonProperty("bank_id")
	private Long bankId;

	@JsonProperty("bank_account_number")
	private String bankAccountNumber;

	@JsonProperty("bank_address")
	private String bankAddress;

	@JsonProperty("branch")
	private String bankBranch;

	@JsonProperty("ifsc")
	private String bankIfsc;

	@JsonProperty("bank_name")
	private String bankName;

	@JsonProperty("bank_uuid")
	private String bankUuid;

	@JsonProperty("created_by")
	private Long createdBy;

	@JsonProperty("created_on")
	private Long createdOn;

	@JsonProperty("updated_by")
	private Long updatedBy;

	@JsonProperty("updated_on")
	private Long updatedOn;

	@JsonProperty("customer_id")
	private Long customer;


	/**
	 * @return the bankId
	 */
	public Long getBankId() {
		return bankId;
	}

	/**
	 * @param bankId the bankId to set
	 */
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	/**
	 * @return the bankAccountNumber
	 */
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	/**
	 * @param bankAccountNumber the bankAccountNumber to set
	 */
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	/**
	 * @return the bankAddress
	 */
	public String getBankAddress() {
		return bankAddress;
	}

	/**
	 * @param bankAddress the bankAddress to set
	 */
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	/**
	 * @return the bankBranch
	 */
	public String getBankBranch() {
		return bankBranch;
	}

	/**
	 * @param bankBranch the bankBranch to set
	 */
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	/**
	 * @return the bankIfsc
	 */
	public String getBankIfsc() {
		return bankIfsc;
	}

	/**
	 * @param bankIfsc the bankIfsc to set
	 */
	public void setBankIfsc(String bankIfsc) {
		this.bankIfsc = bankIfsc;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the bankUuid
	 */
	public String getBankUuid() {
		return bankUuid;
	}

	/**
	 * @param bankUuid the bankUuid to set
	 */
	public void setBankUuid(String bankUuid) {
		this.bankUuid = bankUuid;
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
	 * @return the customer
	 */
	public Long getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Long customer) {
		this.customer = customer;
	}

	
	
	
}
