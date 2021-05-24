package com.agnext.unification.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class PackagesModel {
	private Long id;

	@JsonProperty("licence_package")
	private String licencePackage;

	@JsonProperty("created_on")
	private LocalDateTime createdOn;

	@JsonProperty("created_by")
	private Long createdBy;

	@JsonProperty("updated_on")
	private String updatedOn;

	@JsonProperty("updated_by")
	private Long updatedBy;

	@JsonProperty("status")
	private Integer status;

	@JsonProperty("number_of_scans")
	private Long totalScans;

	@JsonProperty("duration_period")
	private Integer durationPeriod;

	@JsonProperty("duration_unit")
	private String durationUnit;

	private List<PackageCommoditiesPriceVO> commodities;

	@JsonProperty("package_name")
	private String packageName;

	@JsonProperty("package_id")
	private Long packageId;

	@JsonProperty("package_type")
	private Integer packageSubscriptionType;

	@JsonProperty("device_type")
	private Integer deviceType;

	@JsonProperty("device_type_name")
	private String deviceTypeName;

	@JsonProperty("package_subscription_name")
	private String subscriptionName;

	/**
	 * @return the deviceTypeName
	 */
	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	/**
	 * @param deviceTypeName the deviceTypeName to set
	 */
	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	/**
	 * @return the subscriptionName
	 */
	public String getSubscriptionName() {
		return subscriptionName;
	}

	/**
	 * @param subscriptionName the subscriptionName to set
	 */
	public void setSubscriptionName(String subscriptionName) {
		this.subscriptionName = subscriptionName;
	}

	/**
	 * @return the packageSubscriptionType
	 */
	public Integer getPackageSubscriptionType() {
		return packageSubscriptionType;
	}

	/**
	 * @param packageSubscriptionType the packageSubscriptionType to set
	 */
	public void setPackageSubscriptionType(Integer packageSubscriptionType) {
		this.packageSubscriptionType = packageSubscriptionType;
	}

	/**
	 * @return the deviceType
	 */
	public Integer getDeviceType() {
		return deviceType;
	}

	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(Integer deviceType) {
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
	 * @return the commodities
	 */
	public List<PackageCommoditiesPriceVO> getCommodities() {
		return commodities;
	}

	/**
	 * @param commodities the commodities to set
	 */
	public void setCommodities(List<PackageCommoditiesPriceVO> commodities) {
		this.commodities = commodities;
	}

	/**
	 * @return the licencePackage
	 */
	public String getLicencePackage() {
		return licencePackage;
	}

	/**
	 * @param licencePackage the licencePackage to set
	 */
	public void setLicencePackage(String licencePackage) {
		this.licencePackage = licencePackage;
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
	public String getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(String updatedOn) {
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
	 * @return the durationPeriod
	 */
	public Integer getDurationPeriod() {
		return durationPeriod;
	}

	/**
	 * @param durationPeriod the durationPeriod to set
	 */
	public void setDurationPeriod(Integer durationPeriod) {
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

	@Override
	public String toString() {
		return "PackagesModel [id=" + id + ", licencePackage=" + licencePackage + ", createdOn=" + createdOn
				+ ", createdBy=" + createdBy + ", updatedOn=" + updatedOn + ", updatedBy=" + updatedBy + ", status="
				+ status + ", totalScans=" + totalScans + ", durationPeriod=" + durationPeriod + ", durationUnit="
				+ durationUnit + ", commodities=" + commodities + ", packageName=" + packageName + ", packageId="
				+ packageId + ", packageSubscriptionType=" + packageSubscriptionType + ", deviceType=" + deviceType
				+ ", deviceTypeName=" + deviceTypeName + ", subscriptionName=" + subscriptionName + "]";
	}

}
