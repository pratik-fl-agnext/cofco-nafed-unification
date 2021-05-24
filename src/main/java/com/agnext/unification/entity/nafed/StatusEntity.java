package com.agnext.unification.entity.nafed;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the im_status database table.
 * 
 */
@Entity
@Table(name = "im_status")
public class StatusEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_id")
	private Long statusId;

	@Column(name = "created_by")
	private BigInteger createdBy;

	@Column(name = "created_on")
	private BigInteger createdOn;

	@Column(name = "status_name")
	private String statusDesc;

	@Column(name = "updated_by")
	private BigInteger updatedBy;

	@Column(name = "updated_on")
	private BigInteger updatedOn;

	// bi-directional many-to-one association to CustomerEntity
	@OneToMany(mappedBy = "status", cascade = { CascadeType.ALL })
	private List<CustomerEntity> customers;

		// bi-directional many-to-one association to PermissionEntity
	@OneToMany(mappedBy = "status")
	private List<PermissionEntity> permissions;

	// bi-directional many-to-one association to RoleEntity
	@OneToMany(mappedBy = "status")
	private List<RoleEntity> roles;

	// bi-directional many-to-one association to UserEntity
	@OneToMany(mappedBy = "status")
	private List<UserEntity> users;

	public Long getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public BigInteger getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(BigInteger createdBy) {
		this.createdBy = createdBy;
	}

	public BigInteger getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(BigInteger createdOn) {
		this.createdOn = createdOn;
	}


	public BigInteger getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(BigInteger updatedBy) {
		this.updatedBy = updatedBy;
	}

	public BigInteger getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(BigInteger updatedOn) {
		this.updatedOn = updatedOn;
	}

	public List<CustomerEntity> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<CustomerEntity> customers) {
		this.customers = customers;
	}

	public CustomerEntity addCustomer(CustomerEntity customer) {
		getCustomers().add(customer);
		customer.setStatus(this);

		return customer;
	}

	public CustomerEntity removeCustomer(CustomerEntity customer) {
		getCustomers().remove(customer);
		customer.setStatus(null);

		return customer;
	}
	

		
	public List<PermissionEntity> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(List<PermissionEntity> permissions) {
		this.permissions = permissions;
	}

	public PermissionEntity addPermission(PermissionEntity permission) {
		getPermissions().add(permission);
		permission.setStatus(this);

		return permission;
	}

	public PermissionEntity removePermission(PermissionEntity permission) {
		getPermissions().remove(permission);
		permission.setStatus(null);

		return permission;
	}

	public List<RoleEntity> getRoles() {
		return this.roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public RoleEntity addRole(RoleEntity role) {
		getRoles().add(role);
		role.setStatus(this);

		return role;
	}

	public RoleEntity removeRole(RoleEntity role) {
		getRoles().remove(role);
		role.setStatus(null);

		return role;
	}

	public List<UserEntity> getUsers() {
		return this.users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public UserEntity addUser(UserEntity user) {
		getUsers().add(user);
		user.setStatus(this);

		return user;
	}

	public UserEntity removeUser(UserEntity user) {
		getUsers().remove(user);
		user.setStatus(null);

		return user;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}