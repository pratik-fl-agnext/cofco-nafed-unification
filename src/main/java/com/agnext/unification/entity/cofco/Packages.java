package com.agnext.unification.entity.cofco;

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
@Table(name = "packages")
public class Packages {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "package_name")
	private String packageName;

//	@Column(name = "code")
//	private String code;

	@Column(name = "duration_period")
	private String durationPeriod;

	@Column(name = "duration_unit")
	private String durationUnit;

	@Column(name = "total_scans")
	private Long totalScans;

	@Column(name = "created_on")
	private Long createdOn;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "status")
	private Integer status;

//	@ManyToOne
//	@JoinColumn(name = "package_type")
//	private PackageSubscriptionType subscriptionType;

	@ManyToOne
	@JoinColumn(name = "device_type")
	private DcmDeviceType deviceType;

	@Column(name = "is_default_package")
	private Boolean isDefaultPackage;

	@Column(name = "client_id")
	private Long clientId;

	/**
	 * @return the deviceType
	 */
	public DcmDeviceType getDeviceType() {
		return deviceType;
	}

	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(DcmDeviceType deviceType) {
		this.deviceType = deviceType;
	}

//	/**
//	 * @return the subscriptionType
//	 */
//	public PackageSubscriptionType getSubscriptionType() {
//		return subscriptionType;
//	}
//
//	/**
//	 * @param subscriptionType the subscriptionType to set
//	 */
//	public void setSubscriptionType(PackageSubscriptionType subscriptionType) {
//		this.subscriptionType = subscriptionType;
//	}

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
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

//	/**
//	 * @return the code
//	 */
//	public String getCode() {
//		return code;
//	}
//
//	/**
//	 * @param code the code to set
//	 */
//	public void setCode(String code) {
//		this.code = code;
//	}

	/**
	 * @return the createdOn
	 */
	public Long getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Long createdOn) {
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
	 * @return the durationPeriod
	 */
	public String getDurationPeriod() {
		return durationPeriod;
	}

	/**
	 * @param durationPeriod the durationPeriod to set
	 */
	public void setDurationPeriod(String durationPeriod) {
		this.durationPeriod = durationPeriod;
	}

	/**
	 * @return the durationUnit
	 */
	public String getDurationUnit() {
		return durationUnit;
	}

	/**
	 * @param durationUnit the durationUnit to set
	 */
	public void setDurationUnit(String durationUnit) {
		this.durationUnit = durationUnit;
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

	public Boolean getIsDefaultPackage() {
		return isDefaultPackage;
	}

	public void setIsDefaultPackage(Boolean isDefaultPackage) {
		this.isDefaultPackage = isDefaultPackage;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	@Override
	public String toString() {
		return "Packages [id=" + id + ", packageName=" + packageName + ", durationPeriod=" + durationPeriod
				+ ", durationUnit=" + durationUnit + ", totalScans=" + totalScans + ", createdOn=" + createdOn
				+ ", createdBy=" + createdBy + ", updatedOn=" + updatedOn + ", updatedBy=" + updatedBy + ", status="
				+ status + ", subscriptionType="  + ", deviceType=" + deviceType
				+ ", isDefaultPackage=" + isDefaultPackage + ", clientId=" + clientId + ", getDeviceType()="
				+ getDeviceType() +  ", getId()=" + getId()
				+ ", getPackageName()=" + getPackageName() + ", getCreatedOn()=" + getCreatedOn() + ", getCreatedBy()="
				+ getCreatedBy() + ", getUpdatedOn()=" + getUpdatedOn() + ", getUpdatedBy()=" + getUpdatedBy()
				+ ", getStatus()=" + getStatus() + ", getDurationPeriod()=" + getDurationPeriod()
				+ ", getDurationUnit()=" + getDurationUnit() + ", getTotalScans()=" + getTotalScans()
				+ ", getIsDefaultPackage()=" + getIsDefaultPackage() + ", getClientId()=" + getClientId()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
