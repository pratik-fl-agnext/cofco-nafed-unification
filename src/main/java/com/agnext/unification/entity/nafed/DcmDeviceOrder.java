package com.agnext.unification.entity.nafed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the device_order database table.
 * 
 */
@Entity
@Table(name = "device_order")
public class DcmDeviceOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "created_on")
	private Long createdOn;

	@Column(name = "commodity_id")
	private Long commodities;
	
	@Column(name = "mode")
	private String mode;

	// bi-directional many-to-one association to DcmDeviceType
	@ManyToOne
	@JoinColumn(name = "device_type_id")
	private DcmDeviceType dcmDeviceType;

	// bi-directional many-to-one association to DcmStatus
	@ManyToOne
	@JoinColumn(name = "status_id")
	private StatusEntity status;

	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "device_count")
	private Integer deviceCount;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "modified_by")
	private Long modifiedBy;

	@Column(name = "modified_on")
	private Long modifiedOn;
	
	@Column(name = "remark")
	private String remarks;
	
	@Column(name="device_order_random_id")
	private String deviceOrderRandomId;


	public DcmDeviceOrder() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}


	public Long getCommodities() {
		return commodities;
	}


	public void setCommodities(Long commodities) {
		this.commodities = commodities;
	}


	public String getMode() {
		return mode;
	}


	public void setMode(String mode) {
		this.mode = mode;
	}


	public DcmDeviceType getDcmDeviceType() {
		return dcmDeviceType;
	}


	public void setDcmDeviceType(DcmDeviceType dcmDeviceType) {
		this.dcmDeviceType = dcmDeviceType;
	}


	public StatusEntity getStatus() {
		return status;
	}


	public void setStatus(StatusEntity status) {
		this.status = status;
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public Integer getDeviceCount() {
		return deviceCount;
	}


	public void setDeviceCount(Integer deviceCount) {
		this.deviceCount = deviceCount;
	}


	public Long getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
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


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getDeviceOrderRandomId() {
		return deviceOrderRandomId;
	}


	public void setDeviceOrderRandomId(String deviceOrderRandomId) {
		this.deviceOrderRandomId = deviceOrderRandomId;
	}


	
}
