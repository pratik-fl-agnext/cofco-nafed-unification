/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.entity.nafed;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the im_permission database table.
 * 
 */
@Entity
@Table(name="im_permission")
@NamedQuery(name="PermissionEntity.findAll", query="SELECT p FROM PermissionEntity p")
public class PermissionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="permission_id")
	private Long permissionId;

	@Column(name="permission_code")
	private String permissionCode;

	@Column(name="permission_desc")
	private String permissionDesc;

	//bi-directional many-to-one association to StatusEntity
	@ManyToOne
	@JoinColumn(name="status_id")
	private StatusEntity status;

	//bi-directional many-to-many association to RoleEntity
	@ManyToMany(mappedBy="permissions")
	private Set<RoleEntity> roles = new HashSet<>();

	public PermissionEntity() {
	}

	public Long getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionCode() {
		return this.permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public String getPermissionDesc() {
		return this.permissionDesc;
	}

	public void setPermissionDesc(String permissionDesc) {
		this.permissionDesc = permissionDesc;
	}

	public StatusEntity getStatus() {
		return this.status;
	}

	public void setStatus(StatusEntity status) {
		this.status = status;
	}

	 /**
     * @return the roles
     */
    public Set<RoleEntity> getRoles() {
        return roles;
    }

    /**
     * @param roles
     *            the roles to set
     */
    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public void addRole(RoleEntity role) {
        this.roles.add(role);
        role.getPermissions()
            .add(this);
    }

    public void removeRole(RoleEntity role) {
        this.roles.remove(role);
        role.getPermissions()
            .remove(this);
    }
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PermissionEntity [permissionId=" + permissionId + ", permissionCode=" + permissionCode
				+ ", permissionDesc=" + permissionDesc + ", status=" + status + ", roles=" + roles + "]";
	}
}