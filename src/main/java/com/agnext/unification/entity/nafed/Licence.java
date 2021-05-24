package com.agnext.unification.entity.nafed;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "licence")
public class Licence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "client_id")
	private Long clientId;

	@ManyToOne
	@JoinColumn(name = "package_id")
	private Packages packages;

	@ManyToOne
	@JoinColumn(name = "commodity_id")
	private DcmCommodity commodities;

	@Column(name = "device_serial_number")
	private String deviceSerialNo;

	@Column(name = "commodity_code")
	private String commodityCode;

	@Column(name = "licence_no")
	private String licenceNo;

	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "status")
	private Integer status;

	@Column(name = "device_type_id")
	private Long deviceType;

	@Column(name = "expired_on")
	private LocalDateTime expiredOn;

	@Column(name = "renew_on")
	private LocalDateTime renewedOn;

	@Column(name = "total_scan")
	private Long totalScans;

	@Column(name = "consumed_scan")
	private Long consumedScan;

	@Column(name = "device_code")
	private String deviceCode;

	/**
	 * @return the deviceCode
	 */
	public String getDeviceCode() {
		return deviceCode;
	}

	/**
	 * @param deviceCode the deviceCode to set
	 */
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	/**
	 * @return the totalScans
	 */
	public Long getTotalScans() {
		return totalScans;
	}

	/**
	 * @param totalScans the totalScans to set
	 */
	public void setTotalScans(Long totalScans) {
		this.totalScans = totalScans;
	}

	/**
	 * @return the consumedScan
	 */
	public Long getConsumedScan() {
		return consumedScan;
	}

	/**
	 * @param consumedScan the consumedScan to set
	 */
	public void setConsumedScan(Long consumedScan) {
		this.consumedScan = consumedScan;
	}

	/**
	 * @return the renewedOn
	 */
	public LocalDateTime getRenewedOn() {
		return renewedOn;
	}

	/**
	 * @param renewedOn the renewedOn to set
	 */
	public void setRenewedOn(LocalDateTime renewedOn) {
		this.renewedOn = renewedOn;
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
	 * @return the deviceType
	 */
	public Long getDeviceType() {
		return deviceType;
	}

	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(Long deviceType) {
		this.deviceType = deviceType;
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
	 * @return the packages
	 */
	public Packages getPackages() {
		return packages;
	}

	/**
	 * @param packages the packages to set
	 */
	public void setPackages(Packages packages) {
		this.packages = packages;
	}

	/**
	 * @return the licenceNo
	 */
	public String getLicenceNo() {
		return licenceNo;
	}

	/**
	 * @param licenceNo the licenceNo to set
	 */
	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
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
	 * @return the deviceSerialNo
	 */
	public String getDeviceSerialNo() {
		return deviceSerialNo;
	}

	/**
	 * @param deviceSerialNo the deviceSerialNo to set
	 */
	public void setDeviceSerialNo(String deviceSerialNo) {
		this.deviceSerialNo = deviceSerialNo;
	}

	/**
	 * @return the commodityCode
	 */
	public String getCommodityCode() {
		return commodityCode;
	}

	/**
	 * @param commodityCode the commodityCode to set
	 */
	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}

	/**
	 * @return the commodities
	 */
	public DcmCommodity getCommodities() {
		return commodities;
	}

	/**
	 * @param commodities the commodities to set
	 */
	public void setCommodities(DcmCommodity commodities) {
		this.commodities = commodities;
	}

}
