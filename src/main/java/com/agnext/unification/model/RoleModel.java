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
 * The Class RoleModel.
 */
@JsonIgnoreProperties
@JsonInclude(content = Include.NON_NULL)
public class RoleModel {

	/** The role code. */
	@JsonProperty("role_code")
	private String roleCode;
	
	/** The role desc. */
	@JsonProperty("role_desc")
	private String roleDesc;
	
	@JsonProperty("permissions")
	private List<PermissionModel> permissions;
	
	


	/**
	 * @return the permissions
	 */
	public List<PermissionModel> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(List<PermissionModel> permissions) {
		this.permissions = permissions;
	}

	
	/**
	 * Gets the role code.
	 *
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}



	/**
	 * Sets the role code.
	 *
	 * @param roleCode the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * Gets the role desc.
	 *
	 * @return the roleDesc
	 */
	public String getRoleDesc() {
		return roleDesc;
	}

	/**
	 * Sets the role desc.
	 *
	 * @param roleDesc the roleDesc to set
	 */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

		
	
}
