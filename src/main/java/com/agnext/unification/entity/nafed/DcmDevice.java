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

/**
 * The persistent class for the dcm_device database table.
 * 
 */
@Entity
@Table(name = "dcm_device")
@NamedQuery(name = "DcmDevice.findAll", query = "SELECT d FROM DcmDevice d")
public class DcmDevice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "created_on")
	private Long createdOn;

	@Column(name = "serial_number")
	private String serialNumber;

	// bi-directional many-to-one association to DcmDeviceType
	@ManyToOne
	@JoinColumn(name = "device_type_id")
	private DcmDeviceType dcmDeviceType;

//	@Column(name = "status_id")
//	private Integer dcmStatus;

	@Column(name = "user_uuid")
	private String userUuid;

	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "hw_revision")
	private String hwRevision;

	@Column(name = "fw_revision")
	private String fwRevision;

	@Column(name = "start_of_life")
	private Long startOfLife;

	@Column(name = "end_of_life")
	private Long endOfLife;

	@Column(name = "start_of_service")
	private Long startOfService;

	@Column(name = "end_of_service")
	private Long endOfService;

	@Column(name = "user_id")
	private Long userId;

	@ManyToOne
	@JoinColumn(name = "installation_center_id")
	ScanLocation scanLocation;

	@ManyToOne
	@JoinColumn(name = "status_id")
	StatusEntity dcmStatus;

	public DcmDevice() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public DcmDeviceType getDcmDeviceType() {
		return this.dcmDeviceType;
	}

	public void setDcmDeviceType(DcmDeviceType dcmDeviceType) {
		this.dcmDeviceType = dcmDeviceType;
	}

//	/**
//	 * @return the dcmStatus
//	 */
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

	/**
	 * @return the userUuid
	 */
	public String getUserUuid() {
		return userUuid;
	}

	/**
	 * @param userUuid the userUuid to set
	 */
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	/**
	 * @return the customerId
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the hwRevision
	 */
	public String getHwRevision() {
		return hwRevision;
	}

	/**
	 * @param hwRevision the hwRevision to set
	 */
	public void setHwRevision(String hwRevision) {
		this.hwRevision = hwRevision;
	}

	/**
	 * @return the fwRevision
	 */
	public String getFwRevision() {
		return fwRevision;
	}

	/**
	 * @param fwRevision the fwRevision to set
	 */
	public void setFwRevision(String fwRevision) {
		this.fwRevision = fwRevision;
	}

	/**
	 * @return the startOfLife
	 */
	public Long getStartOfLife() {
		return startOfLife;
	}

	/**
	 * @param startOfLife the startOfLife to set
	 */
	public void setStartOfLife(Long startOfLife) {
		this.startOfLife = startOfLife;
	}

	/**
	 * @return the endOfLife
	 */
	public Long getEndOfLife() {
		return endOfLife;
	}

	/**
	 * @param endOfLife the endOfLife to set
	 */
	public void setEndOfLife(Long endOfLife) {
		this.endOfLife = endOfLife;
	}

	/**
	 * @return the startOfService
	 */
	public Long getStartOfService() {
		return startOfService;
	}

	/**
	 * @param startOfService the startOfService to set
	 */
	public void setStartOfService(Long startOfService) {
		this.startOfService = startOfService;
	}

	/**
	 * @return the endOfService
	 */
	public Long getEndOfService() {
		return endOfService;
	}

	/**
	 * @param endOfService the endOfService to set
	 */
	public void setEndOfService(Long endOfService) {
		this.endOfService = endOfService;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ScanLocation getScanLocation() {
		return scanLocation;
	}

	public void setScanLocation(ScanLocation scanLocation) {
		this.scanLocation = scanLocation;
	}

	@Override
	public String toString() {
		return "DcmDevice [id=" + id + ", createdOn=" + createdOn + ", serialNumber=" + serialNumber
				+ ", dcmDeviceType=" + dcmDeviceType + ", userUuid=" + userUuid + ", customerId=" + customerId
				+ ", hwRevision=" + hwRevision + ", fwRevision=" + fwRevision + ", startOfLife=" + startOfLife
				+ ", endOfLife=" + endOfLife + ", startOfService=" + startOfService + ", endOfService=" + endOfService
				+ ", userId=" + userId + ", scanLocation=" + scanLocation + "]";
	}

	public StatusEntity getDcmStatus() {
		return dcmStatus;
	}

	public void setDcmStatus(StatusEntity dcmStatus) {
		this.dcmStatus = dcmStatus;
	}

}