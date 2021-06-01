package com.agnext.unification.entity.nafed;

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

import com.agnext.unification.entity.CommodityBaseEntity;

/**
 * The persistent class for the dcm_commodity database table.
 * 
 */
@Entity
@Table(name = "dcm_commodity")
@NamedQuery(name = "DcmCommodity.findAll", query = "SELECT d FROM DcmCommodity d")
public class DcmCommodity extends CommodityBaseEntity implements Serializable  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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

//	@Column(name = "status")
//	private Integer dcmStatus;

	@Column(name = "count")
	private Integer count;
	
	@ManyToOne
	@JoinColumn(name = "commodity_category_id")
	private DcmCommodityCategory dcmCommodityCategory;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private StatusEntity status;

	public DcmCommodity() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommodityCode() {
		return this.commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}

	public String getCommodityName() {
		return this.commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Long getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Long modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	

	/**
	 * @return the dcmStatus
	 */
//	public Integer getDcmStatus() {
//		return dcmStatus;
//	}
//
//	/**
//	 * @param dcmStatus the dcmStatus to set
//	 */
//	public void setDcmStatus(Integer dcmStatus) {
//		this.dcmStatus = dcmStatus;
//	}

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

	@Override
	public String toString() {
		return "DcmCommodity [id=" + id + ", commodityCode=" + commodityCode + ", commodityName=" + commodityName
				+ ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", modifiedBy=" + modifiedBy
				+ ", modifiedOn=" + modifiedOn + ",   count=" + count
				+ ", dcmCommodityCategory=" + dcmCommodityCategory + "]";
	}

	public StatusEntity getStatus() {
		return status;
	}

	public void setStatus(StatusEntity status) {
		this.status = status;
	}

}