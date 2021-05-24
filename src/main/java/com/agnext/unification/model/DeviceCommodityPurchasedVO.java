package com.agnext.unification.model;

import java.time.LocalDateTime;

public class DeviceCommodityPurchasedVO {
	private Long id;

	private Long clientId;

	private Long packageId;

	private Long commodity;

	private Long device;

	private String licenseNo;

	private LocalDateTime createdOn;

	private Long createdBy;

	private LocalDateTime updatedOn;

	private Long updatedBy;

	private Integer status;

	private Long deviceTypeId;
	
	private LocalDateTime expiredOn;
	
	private Long totalScan;
	
	
	
	

	/**
	 * @return the totalScan
	 */
	public Long getTotalScan() {
		return totalScan;
	}

	/**
	 * @param totalScan the totalScan to set
	 */
	public void setTotalScan(Long totalScan) {
		this.totalScan = totalScan;
	}

	/**
	 * @return the expiredOn
	 */
	public LocalDateTime getExpiredOn() {
		return expiredOn;
	}

	/**
	 * @param expiredOn the expiredOn to set
	 */
	public void setExpiredOn(LocalDateTime expiredOn) {
		this.expiredOn = expiredOn;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the clientId
	 */
	public Long getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the packageId
	 */
	public Long getPackageId() {
		return packageId;
	}

	/**
	 * @param packageId the packageId to set
	 */
	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	/**
	 * @return the commodity
	 */
	public Long getCommodity() {
		return commodity;
	}

	/**
	 * @param commodity the commodity to set
	 */
	public void setCommodity(Long commodity) {
		this.commodity = commodity;
	}

	/**
	 * @return the device
	 */
	public Long getDevice() {
		return device;
	}

	/**
	 * @param device the device to set
	 */
	public void setDevice(Long device) {
		this.device = device;
	}

	/**
	 * @return the licenseNo
	 */
	public String getLicenseNo() {
		return licenseNo;
	}

	/**
	 * @param licenseNo the licenseNo to set
	 */
	public void setLicenseNo(String licenceNo) {
		this.licenseNo = licenceNo;
	}

	/**
	 * @return the createdOn
	 */
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the createdBy
	 */
	public Long getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the updatedOn
	 */
	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * @return the updatedBy
	 */
	public Long getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the deviceTypeId
	 */
	public Long getDeviceTypeId() {
		return deviceTypeId;
	}

	/**
	 * @param deviceTypeId the deviceTypeId to set
	 */
	public void setDeviceTypeId(Long deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

}
