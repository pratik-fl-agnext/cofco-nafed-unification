package com.agnext.unification.entity.cofco;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the dcm_commodity_category database table.
 * 
 */
@Entity
@Table(name = "dcm_commodity_category")
@NamedQuery(name = "CofcoCommodityCategory.findAll", query = "SELECT d FROM CofcoCommodityCategory d")
public class CofcoCommodityCategory implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="commodity_category_name")
	private String commodityCategoryName;

	@Column(name="created_by")
	private Long createdBy;

	@Column(name="created_on")
	private Long createdOn;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Column(name="modified_on")
	private Long modifiedOn;


//	@OneToMany(mappedBy="dcmCommodityCategory")
//	private List<DcmCommodity> dcmCommodities;

	//bi-directional many-to-one association to DcmStatus
	@ManyToOne
	@JoinColumn(name="status_id")
	private StatusEntity status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommodityCategoryName() {
		return commodityCategoryName;
	}

	public void setCommodityCategoryName(String commodityCategoryName) {
		this.commodityCategoryName = commodityCategoryName;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Long getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Long modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

//	public List<DcmCommodity> getDcmCommodities() {
//		return dcmCommodities;
//	}
//
//	public void setDcmCommodities(List<DcmCommodity> dcmCommodities) {
//		this.dcmCommodities = dcmCommodities;
//	}

	public StatusEntity getStatus() {
		return status;
	}

	public void setStatus(StatusEntity status) {
		this.status = status;
	}
	
	
}
