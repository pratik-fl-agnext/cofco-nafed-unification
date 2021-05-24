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
 * The Class UserModel.
 */

@JsonIgnoreProperties
@JsonInclude(content = Include.NON_NULL)
public class UserModel {

	/** The role id. */
	@JsonProperty("user_id")
	private Long userId;
	
	@JsonProperty("created_by")
	private Long createdBy;

	@JsonProperty("created_on")
	private Long createdOn;

	@JsonProperty("updated_by")
	private Long updatedBy;

	@JsonProperty("updated_on")
	private Long updatedOn;


	@JsonProperty("is_2fa_required")
	private Boolean user2faRequired;

	@JsonProperty("user_account_number")
	private String userAccountNumber;

	@JsonProperty("user_alternate_contact_number")
	private String userAlternateContactNumber;

	@JsonProperty("contact_number")
	private String userContactNumber;

	@JsonProperty("email")
	private String userEmail;

	@JsonProperty("first_name")
	private String userFirstName;

	@JsonProperty("last_name")
	private String userLastName;
	
	@JsonProperty("customer_id")
	private Long customerId;
	
	@JsonProperty("customer_name")
	private String customerName;
	
	@JsonProperty("customer_type")
	private String customerType;

	@JsonProperty("address")
	List<UserAddressModel> userAddresses;
	
	@JsonProperty("roles_list")
	List<RoleModel> roleList;
	
	@JsonProperty("roles")
	List<String> roles;
	
	
	@JsonProperty("user_hierarchy")
	private String userHierarchy;
	
	@JsonProperty("supervisor_id")
	private Long supervisorId;
	
	@JsonProperty("supervisor_name")
	private String supervisorName;
	
	@JsonProperty("total_count")
	private Long totalCount;
	
	@JsonProperty("status_id")
	private Long statusId;
	
	@JsonProperty("status_name")
	private String statusName;
	
	@JsonProperty("state_admin_id")
	private Long stateAdminId;
	
	@JsonProperty("state_admin_name")
	private String stateAdminName;
	
	@JsonProperty("password")
	private String password;
	
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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
	 * @return the user2faRequired
	 */
	public Boolean getUser2faRequired() {
		return user2faRequired;
	}

	/**
	 * @param user2faRequired the user2faRequired to set
	 */
	public void setUser2faRequired(Boolean user2faRequired) {
		this.user2faRequired = user2faRequired;
	}

	/**
	 * @return the userAccountNumber
	 */
	public String getUserAccountNumber() {
		return userAccountNumber;
	}

	/**
	 * @param userAccountNumber the userAccountNumber to set
	 */
	public void setUserAccountNumber(String userAccountNumber) {
		this.userAccountNumber = userAccountNumber;
	}

	/**
	 * @return the userAlternateContactNumber
	 */
	public String getUserAlternateContactNumber() {
		return userAlternateContactNumber;
	}

	/**
	 * @param userAlternateContactNumber the userAlternateContactNumber to set
	 */
	public void setUserAlternateContactNumber(String userAlternateContactNumber) {
		this.userAlternateContactNumber = userAlternateContactNumber;
	}

	/**
	 * @return the userContactNumber
	 */
	public String getUserContactNumber() {
		return userContactNumber;
	}

	/**
	 * @param userContactNumber the userContactNumber to set
	 */
	public void setUserContactNumber(String userContactNumber) {
		this.userContactNumber = userContactNumber;
	}

	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return the userFirstName
	 */
	public String getUserFirstName() {
		return userFirstName;
	}

	/**
	 * @param userFirstName the userFirstName to set
	 */
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	/**
	 * @return the userLastName
	 */
	public String getUserLastName() {
		return userLastName;
	}

	/**
	 * @param userLastName the userLastName to set
	 */
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
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

	/**
	 * @return the userAddresses
	 */
	public List<UserAddressModel> getUserAddresses() {
		return userAddresses;
	}

	/**
	 * @param userAddresses the userAddresses to set
	 */
	public void setUserAddresses(List<UserAddressModel> userAddresses) {
		this.userAddresses = userAddresses;
	}

	 
	/**
	 * @return the userHierarchy
	 */
	public String getUserHierarchy() {
		return userHierarchy;
	}

	/**
	 * @param userHierarchy the userHierarchy to set
	 */
	public void setUserHierarchy(String userHierarchy) {
		this.userHierarchy = userHierarchy;
	}

	/**
	 * @return the roleList
	 */
	public List<RoleModel> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<RoleModel> roleList) {
		this.roleList = roleList;
	}

	/**
	 * @return the roles
	 */
	public List<String> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Long getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(Long supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
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

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy="
				+ updatedBy + ", updatedOn=" + updatedOn + ", user2faRequired=" + user2faRequired
				+ ", userAccountNumber=" + userAccountNumber + ", userAlternateContactNumber="
				+ userAlternateContactNumber + ", userContactNumber=" + userContactNumber + ", userEmail=" + userEmail
				+ ", userFirstName=" + userFirstName + ", userLastName=" + userLastName + ", customerId=" + customerId
				+ ", customerName=" + customerName + ", customerType=" + customerType + ", userAddresses="
				+ userAddresses + ", roleList=" + roleList + ", roles=" + roles + ", userHierarchy=" + userHierarchy
				+ ", supervisorId=" + supervisorId + ", supervisorName=" + supervisorName + ", totalCount=" + totalCount
				+ ", statusId=" + statusId + ", statusName=" + statusName + ", stateAdminId=" + stateAdminId
				+ ", stateAdminName=" + stateAdminName + ", password=" + password + "]";
	}

	public Long getStateAdminId() {
		return stateAdminId;
	}

	public void setStateAdminId(Long stateAdminId) {
		this.stateAdminId = stateAdminId;
	}

	public String getStateAdminName() {
		return stateAdminName;
	}

	public void setStateAdminName(String stateAdminName) {
		this.stateAdminName = stateAdminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
