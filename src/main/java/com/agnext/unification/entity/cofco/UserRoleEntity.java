package com.agnext.unification.entity.cofco;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.io.Serializable;
@Entity
@Table(name="im_user_role")
public class UserRoleEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="role_id")
	private Long roleId;
		
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;

	public UserRoleEntity() {
	}

	public Long getRoleId() {
	    return roleId;
	}

	public void setRoleId(Long roleId) {
	    this.roleId = roleId;
	}

	public UserEntity getUserEntity() {
	    return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
	    this.userEntity = userEntity;
	}

	 
}
