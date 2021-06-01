package com.agnext.unification.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class CommodityBaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
/*
	@Column(name = "commodity_code")
	private String commodityCode;

	@Column(name = "commodity_name")
	private String commodityName;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "created_on")
	private Long createdOn;

	@Column(name = "modified_by")
	private Long modifiedBy;

	@Column(name = "modified_on")
	private Long modifiedOn;

	@Column(name = "count")
	private Integer count;
	
	@ManyToOne
	@JoinColumn(name = "commodity_category_id")
	private DcmCommodityCategory dcmCommodityCategory;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private StatusEntity status;

	public CommodityBaseEntity() {
	}

	public Long getId() {
	    return id;
	}

	public void setId(Long id) {
	    this.id = id;
	}

	public String getCommodityCode() {
	    return commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
	    this.commodityCode = commodityCode;
	}

	public String getCommodityName() {
	    return commodityName;
	}

	public void setCommodityName(String commodityName) {
	    this.commodityName = commodityName;
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

	public Integer getCount() {
	    return count;
	}

	public void setCount(Integer count) {
	    this.count = count;
	}

	public DcmCommodityCategory getDcmCommodityCategory() {
	    return dcmCommodityCategory;
	}

	public void setDcmCommodityCategory(DcmCommodityCategory dcmCommodityCategory) {
	    this.dcmCommodityCategory = dcmCommodityCategory;
	}

	public StatusEntity getStatus() {
	    return status;
	}

	public void setStatus(StatusEntity status) {
	    this.status = status;
	}

	public static long getSerialversionuid() {
	    return serialVersionUID;
	}*/

	
	public CommodityBaseEntity() {
	}


	public Long getId() {
	    return id;
	}


	public void setId(Long id) {
	    this.id = id;
	}
	
	
	
	


}