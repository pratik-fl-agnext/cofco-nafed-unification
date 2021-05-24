package com.agnext.unification.entity.nafed;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the im_customer database table.
 * 
 */
@Entity
@Table(name="im_customer")
public class CustomerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Long customerId;

	@Column(name="created_by")
	private Long createdBy;

	@Column(name="created_on")
	private Long createdOn;

	@Column(name="customer_admin_user_id")
	private Integer customerAdminUserId;

	@Column(name="customer_alternate_contact_number")
	private String customerAlternateContactNumber;

	@Column(name="customer_contact_number")
	private String customerContactNumber;

	@Column(name="customer_email")
	private String customerEmail;

	@Column(name="customer_name")
	private String customerName;

	@Column(name="customer_uuid")
	private String customerUuid;
	
	@Column(name="customer_pan")
	private String customerPan;
	
	@Column(name="customer_gst")
	private String customerGst;

	@Column(name="updated_by")
	private Long updatedBy;

	@Column(name="updated_on")
	private Long updatedOn;
	
	@Column(name="partner_id")
	private Long partnerId;
	
	@Column(name="remarks")
	private String remarks;

	//bi-directional many-to-one association to CustomerTypeEntity
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="customer_type")
	private CustomerTypeEntity customerType;

	//bi-directional many-to-one association to StatusEntity
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="status_id")
	private StatusEntity status;

	//bi-directional many-to-one association to CustomerAddressEntity
	@OneToMany(mappedBy="customer",cascade={CascadeType.ALL})
	private List<CustomerAddressEntity> customerAddresses;

	//bi-directional many-to-one association to CustomerBankDetailEntity
	@OneToMany(mappedBy="customer",cascade={CascadeType.ALL})
	private List<CustomerBankDetailEntity> customerBankDetails;

	//bi-directional many-to-one association to CustomerBillingDetailEntity
	@OneToMany(mappedBy="customer",cascade={CascadeType.ALL})
	private List<CustomerBillingDetailEntity> customerBillingDetails;

	//bi-directional many-to-many association to RoleEntity
//	@ManyToMany(mappedBy="customers",cascade={CascadeType.ALL})
//	private List<RoleEntity> roles;

	//bi-directional many-to-one association to UserEntity
	@OneToMany(mappedBy="customer" ,cascade={CascadeType.ALL})
	private List<UserEntity> users;

	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPan() {
		return customerPan;
	}

	public void setCustomerPan(String customerPan) {
		this.customerPan = customerPan;
	}

	public String getCustomerGst() {
		return customerGst;
	}

	public void setCustomerGst(String customerGst) {
		this.customerGst = customerGst;
	}

	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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

	
	public int getCustomerAdminUserId() {
		return this.customerAdminUserId;
	}

	public void setCustomerAdminUserId(int customerAdminUserId) {
		this.customerAdminUserId = customerAdminUserId;
	}

	public String getCustomerAlternateContactNumber() {
		return this.customerAlternateContactNumber;
	}

	public void setCustomerAlternateContactNumber(String customerAlternateContactNumber) {
		this.customerAlternateContactNumber = customerAlternateContactNumber;
	}

	public String getCustomerContactNumber() {
		return this.customerContactNumber;
	}

	public void setCustomerContactNumber(String customerContactNumber) {
		this.customerContactNumber = customerContactNumber;
	}

	public String getCustomerEmail() {
		return this.customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerUuid() {
		return this.customerUuid;
	}

	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
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

	public CustomerTypeEntity getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(CustomerTypeEntity customerType) {
		this.customerType = customerType;
	}

	public StatusEntity getStatus() {
		return this.status;
	}

	public void setStatus(StatusEntity status) {
		this.status = status;
	}

	public List<CustomerAddressEntity> getCustomerAddresses() {
		return this.customerAddresses;
	}

	public void setCustomerAddresses(List<CustomerAddressEntity> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}

	public CustomerAddressEntity addCustomerAddress(CustomerAddressEntity customerAddress) {
		getCustomerAddresses().add(customerAddress);
		customerAddress.setCustomer(this);

		return customerAddress;
	}

	public CustomerAddressEntity removeCustomerAddress(CustomerAddressEntity customerAddress) {
		getCustomerAddresses().remove(customerAddress);
		customerAddress.setCustomer(null);

		return customerAddress;
	}

	public List<CustomerBankDetailEntity> getCustomerBankDetails() {
		return this.customerBankDetails;
	}

	public void setCustomerBankDetails(List<CustomerBankDetailEntity> customerBankDetails) {
		this.customerBankDetails = customerBankDetails;
	}

	public CustomerBankDetailEntity addCustomerBankDetail(CustomerBankDetailEntity customerBankDetail) {
		getCustomerBankDetails().add(customerBankDetail);
		customerBankDetail.setCustomer(this);

		return customerBankDetail;
	}

	public CustomerBankDetailEntity removeCustomerBankDetail(CustomerBankDetailEntity customerBankDetail) {
		getCustomerBankDetails().remove(customerBankDetail);
		customerBankDetail.setCustomer(null);

		return customerBankDetail;
	}

	public List<CustomerBillingDetailEntity> getCustomerBillingDetails() {
		return this.customerBillingDetails;
	}

	public void setCustomerBillingDetails(List<CustomerBillingDetailEntity> customerBillingDetails) {
		this.customerBillingDetails = customerBillingDetails;
	}

	public CustomerBillingDetailEntity addCustomerBillingDetail(CustomerBillingDetailEntity customerBillingDetail) {
		getCustomerBillingDetails().add(customerBillingDetail);
		customerBillingDetail.setCustomer(this);

		return customerBillingDetail;
	}

	public CustomerBillingDetailEntity removeCustomerBillingDetail(CustomerBillingDetailEntity customerBillingDetail) {
		getCustomerBillingDetails().remove(customerBillingDetail);
		customerBillingDetail.setCustomer(null);

		return customerBillingDetail;
	}

 

	/**
	 * @return the roles
	 */
//	public List<RoleEntity> getRoles() {
//		return roles;
//	}

	/**
	 * @param roles the roles to set
	 */
//	public void setRoles(List<RoleEntity> roles) {
//		this.roles = roles;
//	}

	public List<UserEntity> getUsers() {
		return this.users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public UserEntity addUser(UserEntity user) {
		getUsers().add(user);
		user.setCustomer(this);

		return user;
	}

	public UserEntity removeUser(UserEntity user) {
		getUsers().remove(user);
		user.setCustomer(null);

		return user;
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
}