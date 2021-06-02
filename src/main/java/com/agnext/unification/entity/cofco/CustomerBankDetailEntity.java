package com.agnext.unification.entity.cofco;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the im_customer_bank_detail database table.
 * 
 */
@Entity
@Table(name="im_customer_bank_detail")
@NamedQuery(name="CustomerBankDetailEntity.findAll", query="SELECT c FROM CustomerBankDetailEntity c")
public class CustomerBankDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bank_id")
	private Long bankId;

	@Column(name="bank_account_number")
	private String bankAccountNumber;

	@Column(name="bank_address")
	private String bankAddress;

	@Column(name="bank_branch")
	private String bankBranch;

	@Column(name="bank_ifsc")
	private String bankIfsc;

	@Column(name="bank_name")
	private String bankName;

	@Column(name="bank_uuid")
	private String bankUuid;

	@Column(name="created_by")
	private Long createdBy;

	@Column(name="created_on")
	private Long createdOn;

	@Column(name="updated_by")
	private Long updatedBy;

	@Column(name="updated_on")
	private Long updatedOn;

	//bi-directional many-to-one association to CustomerEntity
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="customer_id")
	private CustomerEntity customer;

	
	public Long getBankId() {
		return this.bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public String getBankAccountNumber() {
		return this.bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankAddress() {
		return this.bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getBankBranch() {
		return this.bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getBankIfsc() {
		return this.bankIfsc;
	}

	public void setBankIfsc(String bankIfsc) {
		this.bankIfsc = bankIfsc;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankUuid() {
		return this.bankUuid;
	}

	public void setBankUuid(String bankUuid) {
		this.bankUuid = bankUuid;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public Long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
	}

	public CustomerEntity getCustomer() {
		return this.customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

}