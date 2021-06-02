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
import javax.persistence.Table;

/**
 * The persistent class for the im_customer_address database table.
 * 
 */
@Entity
@Table(name = "im_customer_address")
public class CustomerAddressEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Long addressId;

	@Column(name = "address_line1")
	private String addressLine1;

	@Column(name = "address_line2")
	private String addressLine2;

	private String city;

	private String country;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "created_on")
	private Long createdOn;

	private String state;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "updated_on")
	private Long updatedOn;

	@Column(name = "zip_code")
	private int zipCode;

	// bi-directional many-to-one association to AddressTypeEntity
	@ManyToOne
	@JoinColumn(name = "address_type_id")
	private AddressTypeEntity addressType;

	// bi-directional many-to-one association to CustomerEntity
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "customer_id")
	private CustomerEntity customer;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	StatusEntity status;


	public Long getAddressId() {
		return this.addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
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

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
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

	public int getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public AddressTypeEntity getAddressType() {
		return this.addressType;
	}

	public void setAddressType(AddressTypeEntity addressType) {
		this.addressType = addressType;
	}

	public CustomerEntity getCustomer() {
		return this.customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public StatusEntity getStatus() {
	    return status;
	}

	public void setStatus(StatusEntity status) {
	    this.status = status;
	}
	
	
}