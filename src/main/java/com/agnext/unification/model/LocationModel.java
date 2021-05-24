package com.agnext.unification.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class LocationModel {
    @JsonProperty("installation_center_id")
    private Long installationCenterId;

    @JsonProperty("created_by")
    private Long createdBy;

    @JsonProperty("created_on")
    private Long createdOn;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("inst_center_name")
    private String instCenterName;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("modified_by")
    private Long modifiedBy;

    @JsonProperty("modified_on")
    private Long modifiedOn;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("commercial_location_type_id")
    private Long commercialLocationTypeId;

    @JsonProperty("commercial_location_type_desc")
    private String commercialLocationTypeDesc;

    @JsonProperty("device_list")
    List<DeviceModel> deviceModelLis = new ArrayList<DeviceModel>();

    @JsonProperty("total_count")
    private Long totalCount;

    @JsonProperty("site_id")
    private Long siteId;

    @JsonProperty("site_name")
    private String siteName;

    @JsonProperty("region_id")
    private Long regionId;

    @JsonProperty("region_name")
    private String regionName;

    @JsonProperty("allocated")
    private boolean isAllocated;

    @JsonProperty("count")
    private Long count;

    @JsonProperty("state_id")
    private Long stateId;

    @JsonProperty("state_name")
    private String stateName;

    @JsonProperty("warehouse_name")
    private String warehouseName;

    @JsonProperty("status_id")
    private Long statusId;

    @JsonProperty("status_name")
    private String statusName;

    @JsonProperty("code")
    private String code;
    
    @JsonProperty("location_name")
    private String locationName;

    public LocationModel(String instCenterName, String notes, Long commercialLocationTypeId) {
	super();
	this.instCenterName = instCenterName;
	this.notes = notes;
	this.commercialLocationTypeId = commercialLocationTypeId;
    }

    public LocationModel() {
	// TODO Auto-generated constructor stub
    }

    /**
     * @return the deviceModelLis
     */
    public List<DeviceModel> getDeviceModelLis() {
	return deviceModelLis;
    }

    /**
     * @param deviceModelLis
     *            the deviceModelLis to set
     */
    public void setDeviceModelLis(List<DeviceModel> deviceModelLis) {
	this.deviceModelLis = deviceModelLis;
    }

    /**
     * @return the installationCenterId
     */
    public Long getInstallationCenterId() {
	return installationCenterId;
    }

    /**
     * @param installationCenterId
     *            the installationCenterId to set
     */
    public void setInstallationCenterId(Long installationCenterId) {
	this.installationCenterId = installationCenterId;
    }

    /**
     * @return the createdBy
     */
    public Long getCreatedBy() {
	return createdBy;
    }

    /**
     * @param createdBy
     *            the createdBy to set
     */
    public void setCreatedBy(Long createdBy) {
	this.createdBy = createdBy;
    }

    /**
     * @return the createdOn
     */
    public Long getCreatedOn() {
	return createdOn;
    }

    /**
     * @param createdOn
     *            the createdOn to set
     */
    public void setCreatedOn(Long createdOn) {
	this.createdOn = createdOn;
    }

    /**
     * @return the instCenterName
     */
    public String getInstCenterName() {
	return instCenterName;
    }

    /**
     * @param instCenterName
     *            the instCenterName to set
     */
    public void setInstCenterName(String instCenterName) {
	this.instCenterName = instCenterName;
    }

    /**
     * @return the modifiedBy
     */
    public Long getModifiedBy() {
	return modifiedBy;
    }

    /**
     * @param modifiedBy
     *            the modifiedBy to set
     */
    public void setModifiedBy(Long modifiedBy) {
	this.modifiedBy = modifiedBy;
    }

    /**
     * @return the modifiedOn
     */
    public Long getModifiedOn() {
	return modifiedOn;
    }

    /**
     * @param modifiedOn
     *            the modifiedOn to set
     */
    public void setModifiedOn(Long modifiedOn) {
	this.modifiedOn = modifiedOn;
    }

    /**
     * @return the commercialLocationTypeId
     */
    public Long getCommercialLocationTypeId() {
	return commercialLocationTypeId;
    }

    /**
     * @param commercialLocationTypeId
     *            the commercialLocationTypeId to set
     */
    public void setCommercialLocationTypeId(Long commercialLocationTypeId) {
	this.commercialLocationTypeId = commercialLocationTypeId;
    }

    /**
     * @return the commercialLocationTypeDesc
     */
    public String getCommercialLocationTypeDesc() {
	return commercialLocationTypeDesc;
    }

    /**
     * @param commercialLocationTypeDesc
     *            the commercialLocationTypeDesc to set
     */
    public void setCommercialLocationTypeDesc(String commercialLocationTypeDesc) {
	this.commercialLocationTypeDesc = commercialLocationTypeDesc;
    }

    /**
     * @return the customerId
     */
    public Long getCustomerId() {
	return customerId;
    }

    /**
     * @param customerId
     *            the customerId to set
     */
    public void setCustomerId(Long customerId) {
	this.customerId = customerId;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
	return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(Long userId) {
	this.userId = userId;
    }

    /**
     * @return the totalCount
     */
    public Long getTotalCount() {
	return totalCount;
    }

    /**
     * @param totalCount
     *            the totalCount to set
     */
    public void setTotalCount(Long totalCount) {
	this.totalCount = totalCount;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
	return notes;
    }

    /**
     * @param notes
     *            the notes to set
     */
    public void setNotes(String notes) {
	this.notes = notes;
    }

    /**
     * @return the siteId
     */
    public Long getSiteId() {
	return siteId;
    }

    /**
     * @param siteId
     *            the siteId to set
     */
    public void setSiteId(Long siteId) {
	this.siteId = siteId;
    }

    /**
     * @return the siteName
     */
    public String getSiteName() {
	return siteName;
    }

    /**
     * @param siteName
     *            the siteName to set
     */
    public void setSiteName(String siteName) {
	this.siteName = siteName;
    }

    /**
     * @return the regionId
     */
    public Long getRegionId() {
	return regionId;
    }

    /**
     * @param regionId
     *            the regionId to set
     */
    public void setRegionId(Long regionId) {
	this.regionId = regionId;
    }

    /**
     * @return the regionName
     */
    public String getRegionName() {
	return regionName;
    }

    /**
     * @param regionName
     *            the regionName to set
     */
    public void setRegionName(String regionName) {
	this.regionName = regionName;
    }

    /**
     * @return the isAllocated
     */
    public boolean isAllocated() {
	return isAllocated;
    }

    /**
     * @param isAllocated
     *            the isAllocated to set
     */
    public void setAllocated(boolean isAllocated) {
	this.isAllocated = isAllocated;
    }

    public Long getCount() {
	return count;
    }

    public void setCount(Long count) {
	this.count = count;
    }

    @Override
    public String toString() {
	return "LocationModel [installationCenterId=" + installationCenterId + ", createdBy=" + createdBy
		+ ", createdOn=" + createdOn + ", customerId=" + customerId + ", instCenterName=" + instCenterName
		+ ", notes=" + notes + ", modifiedBy=" + modifiedBy + ", modifiedOn=" + modifiedOn + ", userId="
		+ userId + ", commercialLocationTypeId=" + commercialLocationTypeId + ", commercialLocationTypeDesc="
		+ commercialLocationTypeDesc + ", deviceModelLis=" + deviceModelLis + ", totalCount=" + totalCount
		+ ", siteId=" + siteId + ", siteName=" + siteName + ", regionId=" + regionId + ", regionName="
		+ regionName + ", isAllocated=" + isAllocated + ", count=" + count + ", stateId=" + stateId
		+ ", stateName=" + stateName + ", warehouseName=" + warehouseName + "]";
    }

    public Long getStateId() {
	return stateId;
    }

    public void setStateId(Long stateId) {
	this.stateId = stateId;
    }

    public String getStateName() {
	return stateName;
    }

    public void setStateName(String stateName) {
	this.stateName = stateName;
    }

    public String getWarehouseName() {
	return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
	this.warehouseName = warehouseName;
    }

    public Long getStatusId() {
	return statusId;
    }

    public void setStatusId(Long statusId) {
	this.statusId = statusId;
    }

    public String getStatusName() {
	return statusName;
    }

    public void setStatusName(String statusName) {
	this.statusName = statusName;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

}
