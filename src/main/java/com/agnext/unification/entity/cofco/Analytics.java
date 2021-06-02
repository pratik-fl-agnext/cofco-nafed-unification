package com.agnext.unification.entity.cofco;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the Analytics database table.
 * 
 */
@Entity
@Table(name="analytics")
public class Analytics implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	
	@Column(name="analytic_name")
	private String analyticName;
	
	@ManyToOne
	@JoinColumn(name = "commodity_id")
	private CofcoCommodityEntity commodityId;
	
	@ManyToOne
	@JoinColumn(name="status_id")
	private StatusEntity status;
	
	@Column(name="created_on")
	private Long createdOn;
	
	@Column(name="updated_on")
	private Long updatedOn;

	public Long getId() {
		return id;
	}

	public String getAnalyticName() {
		return analyticName;
	}

	public CofcoCommodityEntity getCommodityId() {
		return commodityId;
	}

	public StatusEntity getStatus() {
		return status;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public Long getUpdatedOn() {
		return updatedOn;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAnalyticName(String analyticName) {
		this.analyticName = analyticName;
	}

	public void setCommodityId(CofcoCommodityEntity commodityId) {
		this.commodityId = commodityId;
	}

	public void setStatus(StatusEntity status) {
		this.status = status;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	
	

}
