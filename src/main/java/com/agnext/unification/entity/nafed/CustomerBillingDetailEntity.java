package com.agnext.unification.entity.nafed;

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
 * The persistent class for the im_customer_billing_details database table.
 * 
 */
@Entity
@Table(name="im_customer_billing_details")
@NamedQuery(name="CustomerBillingDetailEntity.findAll", query="SELECT c FROM CustomerBillingDetailEntity c")
public class CustomerBillingDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="billing_id")
	private Long billingId;

	@Column(name="billing_account_number")
	private String billingAccountNumber;

	@Column(name="billing_address")
	private String billingAddress;

	@Column(name="billing_uuid")
	private String billingUuid;

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

	//bi-directional many-to-one association to StatusEntity
	
	public Long getBillingId() {
		return this.billingId;
	}

	public void setBillingId(Long billingId) {
		this.billingId = billingId;
	}

	public String getBillingAccountNumber() {
		return this.billingAccountNumber;
	}

	public void setBillingAccountNumber(String billingAccountNumber) {
		this.billingAccountNumber = billingAccountNumber;
	}

	public String getBillingAddress() {
		return this.billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getBillingUuid() {
		return this.billingUuid;
	}

	public void setBillingUuid(String billingUuid) {
		this.billingUuid = billingUuid;
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