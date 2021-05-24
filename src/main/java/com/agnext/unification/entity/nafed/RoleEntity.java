/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.entity.nafed;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the im_role database table.
 * 
 */
@Entity
@Table(name = "im_role")
@NamedQuery(name = "RoleEntity.findAll", query = "SELECT r FROM RoleEntity r")
public class RoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "role_code")
	private String roleCode;

	@Column(name = "role_desc")
	private String roleDesc;

	// bi-directional many-to-many association to CustomerEntity
//	@ManyToMany(cascade={CascadeType.ALL})
//	@JoinTable(name = "im_customer_role", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "customer_id") })
//	private List<CustomerEntity> customers;

	// bi-directional many-to-many association to PermissionEntity
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name = "im_role_permission", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
			@JoinColumn(name = "permission_id") })
	private Set<PermissionEntity> permissions = new HashSet<>();

	// bi-directional many-to-one association to StatusEntity
	@ManyToOne
	@JoinColumn(name = "status_id")
	private StatusEntity status;

	// bi-directional many-to-many association to UserEntity
	@ManyToMany(mappedBy = "roles")
	private Set<UserEntity> users = new HashSet<>();

	public RoleEntity() {
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

//	public List<CustomerEntity> getCustomers() {
//		return this.customers;
//	}
//
//	public void setCustomers(List<CustomerEntity> customers) {
//		this.customers = customers;
//	}

	/**
	 * @return the permissions
	 */
	public Set<PermissionEntity> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(Set<PermissionEntity> permissions) {
		this.permissions = permissions;
	}

	public void addPermission(PermissionEntity permission) {
		this.permissions.add(permission);
		permission.getRoles().add(this);
	}

	public void removePermission(PermissionEntity permission) {
		this.permissions.remove(permission);
		permission.getRoles().remove(this);
	}

	public StatusEntity getStatus() {
		return this.status;
	}

	public void setStatus(StatusEntity status) {
		this.status = status;
	}

	public Set<UserEntity> getUsers() {
		return this.users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}

	public void addUsers(UserEntity user) {
		this.users.add(user);
		user.getRoles().add(this);
	}

	public void removeUsers(UserEntity user) {
		this.users.remove(user);
		user.getRoles().remove(this);
	}
}