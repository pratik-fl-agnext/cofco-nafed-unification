/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class PermissionModel.
 */
@JsonIgnoreProperties
@JsonInclude(content = Include.NON_EMPTY)
public class PermissionModel {

	@JsonProperty("permission_code")
	private String permissionCode;
	
	@JsonProperty("permission_desc")
	private String permissionDesc;

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public String getPermissionDesc() {
		return permissionDesc;
	}

	public void setPermissionDesc(String permissionDesc) {
		this.permissionDesc = permissionDesc;
	}
	
	
	
	}
