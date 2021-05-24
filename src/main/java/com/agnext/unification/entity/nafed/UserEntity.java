package com.agnext.unification.entity.nafed;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the im_user database table.
 * 
 */
@Entity
@Table(name = "im_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "password", length = 800)
	private String password;

	@Column(name = "user_uuid", length = 36)
	private String userUuid;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "created_on")
	private Long createdOn;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "updated_on")
	private Long updatedOn;

	@Column(name = "user_2fa_enabled")
	private Boolean user2faEnabled;

	@Column(name = "user_2fa_required")
	private Boolean user2faRequired;

	@Column(name = "user_account_number")
	private String userAccountNumber;

	@Column(name = "user_alternate_contact_number")
	private String userAlternateContactNumber;

	@Column(name = "user_contact_number")
	private String userContactNumber;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "user_first_name")
	private String userFirstName;

	@Column(name = "user_last_name")
	private String userLastName;

	@Column(name = "secret_key")
	private String secretKey;

	@Column(name = "qrcode_path")
	private String qrCodePath;

	@Column(name = "user_hierarchy")
	private String userHierarchy;

	@OneToOne(cascade = CascadeType.ALL)
	private UserEntity supervisor;

	// bi-directional many-to-one association to CustomerEntity
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private CustomerEntity customer;

	// bi-directional many-to-many association to RoleEntity	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "im_user_role", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "user_id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "role_id") })
	private List<RoleEntity> roles;

	/*
	 * @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	 * 
	 * @JoinTable(joinColumns = @JoinColumn(name = "userId", referencedColumnName =
	 * "id"), inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName
	 * = "id")) private List<RoleEntity> roles;
	 */

	// bi-directional many-to-one association to StatusEntity
	@ManyToOne
	@JoinColumn(name = "status_id")
	private StatusEntity status;

	// bi-directional many-to-one association to UserAddressEntity
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserAddressEntity> userAddresses;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserEntity() {
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Boolean getUser2faEnabled() {
		return user2faEnabled;
	}

	public void setUser2faEnabled(Boolean user2faEnabled) {
		this.user2faEnabled = user2faEnabled;
	}

	public Boolean getUser2faRequired() {
		return user2faRequired;
	}

	public void setUser2faRequired(Boolean user2faRequired) {
		this.user2faRequired = user2faRequired;
	}

	public String getUserAccountNumber() {
		return this.userAccountNumber;
	}

	public void setUserAccountNumber(String userAccountNumber) {
		this.userAccountNumber = userAccountNumber;
	}

	public String getUserAlternateContactNumber() {
		return this.userAlternateContactNumber;
	}

	public void setUserAlternateContactNumber(String userAlternateContactNumber) {
		this.userAlternateContactNumber = userAlternateContactNumber;
	}

	public String getUserContactNumber() {
		return this.userContactNumber;
	}

	public void setUserContactNumber(String userContactNumber) {
		this.userContactNumber = userContactNumber;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserFirstName() {
		return this.userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return this.userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public CustomerEntity getCustomer() {
		return this.customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public List<RoleEntity> getRoles() {
		return this.roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public void addRoles(RoleEntity role) {
		this.roles.add(role);
		role.getUsers().add(this);
	}

	public void removeRoles(RoleEntity role) {
		this.roles.remove(role);
		role.getUsers().remove(this);
	}

	public StatusEntity getStatus() {
		return this.status;
	}

	public void setStatus(StatusEntity status) {
		this.status = status;
	}

	public List<UserAddressEntity> getUserAddresses() {
		return this.userAddresses;
	}

	public void setUserAddresses(List<UserAddressEntity> userAddresses) {
		this.userAddresses = userAddresses;
	}

	public UserAddressEntity addUserAddress(UserAddressEntity userAddress) {
		getUserAddresses().add(userAddress);
		userAddress.setUser(this);

		return userAddress;
	}

	public UserAddressEntity removeUserAddress(UserAddressEntity userAddress) {
		getUserAddresses().remove(userAddress);
		userAddress.setUser(null);

		return userAddress;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	/**
	 * @return the qrCodePath
	 */
	public String getQrCodePath() {
		return qrCodePath;
	}

	/**
	 * @param qrCodePath the qrCodePath to set
	 */
	public void setQrCodePath(String qrCodePath) {
		this.qrCodePath = qrCodePath;
	}

	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * @param secretKey the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
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

	public UserEntity getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(UserEntity supervisor) {
		this.supervisor = supervisor;
	}

	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", password=" + password + ", userUuid=" + userUuid + ", createdBy="
				+ createdBy + ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn
				+ ", user2faEnabled=" + user2faEnabled + ", user2faRequired=" + user2faRequired + ", userAccountNumber="
				+ userAccountNumber + ", userAlternateContactNumber=" + userAlternateContactNumber
				+ ", userContactNumber=" + userContactNumber + ", userEmail=" + userEmail + ", userFirstName="
				+ userFirstName + ", userLastName=" + userLastName + ", secretKey=" + secretKey + ", qrCodePath="
				+ qrCodePath + ", userHierarchy=" + userHierarchy + ", supervisor=" + supervisor + ", customer="
				+ customer + ", roles=" + roles + ", status=" + status + ", userAddresses=" + userAddresses + "]";
	}

	
}