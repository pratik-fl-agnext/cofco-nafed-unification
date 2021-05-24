package com.agnext.unification.entity.nafed;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the state database table.
 * 
 */
@Entity
@Table(name="state_manager_operator")
public class StateManagerOperatorsEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	
	@Column(name="state_manager_id")
	private Long stateManagerId;
	
	
	@Column(name="operator_id")
	private Long operatorId;
	
	
	@Column(name="status")
	private Integer status;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getStateManagerId() {
		return stateManagerId;
	}


	public void setStateManagerId(Long stateManagerId) {
		this.stateManagerId = stateManagerId;
	}


	public Long getOperatorId() {
		return operatorId;
	}


	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
