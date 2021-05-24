/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class CustomerModel.
 */
@JsonIgnoreProperties
@JsonInclude(content = Include.NON_NULL)
public class CustomerModel {

	@JsonProperty("customer_id")
	private Long customerId;
	
	@JsonProperty("created_on")
	private Long createdOn;

	@JsonProperty("gst")
	private String gst;
	
	@JsonProperty("pan")
	private String pan;

	@JsonProperty("contact_number")
	private String contactNumber;

	@JsonProperty("email")
	private String email;

	@JsonProperty("name")
	private String name;

	@JsonProperty("customer_uuid")
	private String customerUuid;

	@JsonProperty("updated_on")
	private Long updatedOn;
	
	@JsonProperty("customer_type_id")
	private Long customerTypeId;
	
	@JsonProperty("customer_type")
	private String customerType;
	
	@JsonProperty("status")
	private Long status;
	
	@JsonProperty("status_name")
	private String statusName;
	
	@JsonProperty("commodity_category_ids")
	private Long[] commodityCategoryId;
	
	@JsonProperty("address")
	private List<CustomerAddressModel> customerAddresses;
	
	@JsonProperty("roles")
	private List<RoleModel> roles;
	
	@JsonProperty("bank_details")
	private List<CustomerBankDetailModel> customerBankDetails;
	
	@JsonProperty("billing_details")
	private List<CustomerBillingDetailModel> customerBillingDetails;
	
	@JsonProperty("user")
	private UserModel userModel;
	
	@JsonProperty("total_count")
	private Long totalCount;
	
	@JsonProperty("partner_id")
	private Long partnerId;
	
	@JsonProperty("is_partner")
	private boolean isPartner;
	
	@JsonProperty("remarks")
	private String remarks;
	
	@JsonProperty("product_id")
	private Long productId;

	@JsonProperty("start_of_subscription")
	private Long startOfSubscription;

	@JsonProperty("end_of_subscription")
	private Long endOfSubscription;
	
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	 

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getCustomerUuid() {
		return customerUuid;
	}

	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}

	public Long getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Long getCustomerTypeId() {
		return customerTypeId;
	}

	public void setCustomerTypeId(Long customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public List<CustomerAddressModel> getCustomerAddresses() {
		return customerAddresses;
	}

	public void setCustomerAddresses(List<CustomerAddressModel> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}

	public List<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleModel> roles) {
		this.roles = roles;
	}

	public List<CustomerBankDetailModel> getCustomerBankDetails() {
		return customerBankDetails;
	}

	public void setCustomerBankDetails(List<CustomerBankDetailModel> customerBankDetails) {
		this.customerBankDetails = customerBankDetails;
	}

	public List<CustomerBillingDetailModel> getCustomerBillingDetails() {
		return customerBillingDetails;
	}

	public void setCustomerBillingDetails(List<CustomerBillingDetailModel> customerBillingDetails) {
		this.customerBillingDetails = customerBillingDetails;
	}

	/**
	 * @return the userModel
	 */
	public UserModel getUserModel() {
		return userModel;
	}

	/**
	 * @param userModel the userModel to set
	 */
	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;

	}

	/**
	 * @return the totalCount
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the partnerId
	 */
	public Long getPartnerId() {
		return partnerId;
	}

	/**
	 * @param partnerId the partnerId to set
	 */
	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * @return the isPartner
	 */
	public boolean isPartner() {
		return isPartner;
	}

	/**
	 * @param isPartner the isPartner to set
	 */
	public void setPartner(boolean isPartner) {
		this.isPartner = isPartner;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Long[] getCommodityCategoryId() {
		return commodityCategoryId;
	}

	public void setCommodityCategoryId(Long[] commodityCategoryId) {
		this.commodityCategoryId = commodityCategoryId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getStartOfSubscription() {
		return startOfSubscription;
	}

	public void setStartOfSubscription(Long startOfSubscription) {
		this.startOfSubscription = startOfSubscription;
	}

	public Long getEndOfSubscription() {
		return endOfSubscription;
	}

	public void setEndOfSubscription(Long endOfSubscription) {
		this.endOfSubscription = endOfSubscription;
	}

}
