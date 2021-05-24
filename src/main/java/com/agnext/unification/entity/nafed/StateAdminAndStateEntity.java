package com.agnext.unification.entity.nafed;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="state_admin_state_association")
public class StateAdminAndStateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="state_admin_id")
	private UserEntity stateAdmin;
	
	@ManyToOne
	@JoinColumn(name="state_id")
	private StateEntity state;
	
	@ManyToOne
	@JoinColumn(name="status")
	private StatusEntity status; 

	public StateAdminAndStateEntity() {
	    
	}

	public Long getId() {
	    return id;
	}

	public void setId(Long id) {
	    this.id = id;
	}

	public UserEntity getStateAdmin() {
	    return stateAdmin;
	}

	public void setStateAdmin(UserEntity stateAdmin) {
	    this.stateAdmin = stateAdmin;
	}

	public StateEntity getState() {
	    return state;
	}

	public void setState(StateEntity state) {
	    this.state = state;
	}

	public StatusEntity getStatus() {
	    return status;
	}

	public void setStatus(StatusEntity status) {
	    this.status = status;
	}	

}