/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.entity.cofco;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the im_role database table.
 * 
 */
@Entity
@Table(name="im_role_permission")
public class RolePermissionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="role_id")
	private RoleEntity role;

	@Column(name="permission_id")
	private PermissionEntity permission;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the role
	 */
	public RoleEntity getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(RoleEntity role) {
		this.role = role;
	}

	/**
	 * @return the permission
	 */
	public PermissionEntity getPermission() {
		return permission;
	}

	/**
	 * @param permission the permission to set
	 */
	public void setPermission(PermissionEntity permission) {
		this.permission = permission;
	}

}