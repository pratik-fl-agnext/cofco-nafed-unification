package com.agnext.unification.model;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class DeviceModel {
    @JsonProperty("serial_number")
    private String serialNumber;

    @JsonProperty("device_type")
    private String deviceType;

    @JsonProperty("installation_center_name")
    private String installationCenterName;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("user_uuid")
    private String userUuid;

    @JsonProperty("device_id")
    private Long deviceId;

    @JsonProperty("device_type_id")
    private Long deviceTypeId;

    @JsonProperty("city")
    private Long city;

    @JsonProperty("state")
    private Long state;

    @JsonProperty("country")
    private Long country;

    @JsonProperty("installation_center_type_id")
    private Long installationCenterTypeId;

    @JsonProperty("installation_center_type_name")
    private String installationCenterTypeName;

    @JsonProperty("commercial_location_id")
    private Long commercialLocationId;

    @JsonProperty("total_count")
    private Long totalCount;

    @JsonProperty("state_id")
    private Long stateId;

    @JsonProperty("device_group_id")
    private Long deviceGroupId;

    @JsonProperty("device_sub_type_id")
    private Long deviceSubTypeId;

    @JsonProperty("hw_revision")
    private String hwRevision;

    @JsonProperty("fw_revision")
    private String fwRevision;

    @JsonProperty("start_of_life")
    private Long startOfLife;

    @JsonProperty("end_of_life")
    private Long endOfLife;

    @JsonProperty("start_of_service")
    private Long startOfService;

    @JsonProperty("end_of_service")
    private Long endOfService;

    @JsonProperty("sensor_profile_id")
    private Long sensorProfileId;

    @JsonProperty("device_group_desc")
    private String deviceGroupDesc;

    @JsonProperty("device_sub_type_desc")
    private String deviceSubTypeDesc;

    @JsonProperty("sensor_profile_desc")
    private String sensorProfileDesc;

    @JsonProperty("status_id")
    private Long statusId;

    @JsonProperty("status_desc")
    private String statusDesc;

    @JsonProperty("vendor_name")
    private String vendorName;

    @JsonProperty("cold_store_id")
    private Long coldStoreId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("created_on")
    private Long createdOn;

    @JsonProperty("deviation_profile_id")
    private Long deviationProfileId;

    @JsonProperty("deviation_profile_desc")
    private String deviationProfileDesc;

    @JsonProperty("escalation_level")
    private Integer escalationLevel;

    @JsonProperty("escalation_counter")
    private Integer escalationCounter;

    @JsonProperty("escalation_initiated_on")
    private Long escalationInitiatedOn;

    @JsonProperty("count")
    private Long count;

    @JsonProperty("customer_name")
    private String customerName;

    private List<Double> sfValues;

    @JsonProperty("sample_type")
    private Integer sampleType;

    @JsonProperty("cc_name")
    private String ccName;

    @JsonProperty("crop_name")
    private String cropName;

    @JsonProperty("crop_id")
    private Long cropId;

    private String serialNo;

    @JsonProperty("cropName")
    private String cropNameC;

    @JsonProperty("cropId")
    private Long cropIdC;

    @JsonProperty("is_subscribed")
    private Boolean isSubscribed;

    @JsonProperty("category_id")
    private Long categoryId;
    
    @JsonProperty("commodity_ids")
    private Long [] commodityIds;
    
    @JsonProperty("user_name")
    private String userName;
    
    @JsonProperty("customer_email")
    private String customerEmail;
    
    @JsonProperty("user_email")
    private String userEmail;
    
    @JsonProperty("state_manager_name")
    private String stateManagerName;
    
    @JsonProperty("state_manager_email")
    private String stateManagerEmail;
    
    @JsonProperty("state_manager_id")
    private Long stateManagerId;
    
    @JsonProperty("device_commodity_model")
    private List<DeviceCommodityModel> deviceCommodities;
    
    /**
     * @return the serialNo
     */
    public String getSerialNo() {
	return serialNo;
    }

    /**
     * @param serialNo
     *            the serialNo to set
     */
    public void setSerialNo(String serialNo) {
	this.serialNo = serialNo;
    }

    /**
     * @return the cropNameC
     */
    public String getCropNameC() {
	return cropNameC;
    }

    /**
     * @param cropNameC
     *            the cropNameC to set
     */
    public void setCropNameC(String cropNameC) {
	this.cropNameC = cropNameC;
    }

    /**
     * @return the cropIdC
     */
    public Long getCropIdC() {
	return cropIdC;
    }

    /**
     * @param cropIdC
     *            the cropIdC to set
     */
    public void setCropIdC(Long cropIdC) {
	this.cropIdC = cropIdC;
    }

    /**
     * @return the cropId
     */
    public Long getCropId() {
	return cropId;
    }

    /**
     * @param cropId
     *            the cropId to set
     */
    public void setCropId(Long cropId) {
	this.cropId = cropId;
    }

    /**
     * @return the sfValues
     */
    public List<Double> getSfValues() {
	return sfValues;
    }

    /**
     * @param sfValues
     *            the sfValues to set
     */
    public void setSfValues(List<Double> sfValues) {
	this.sfValues = sfValues;
    }

    /**
     * @return the sampleType
     */
    public Integer getSampleType() {
	return sampleType;
    }

    /**
     * @param sampleType
     *            the sampleType to set
     */
    public void setSampleType(Integer sampleType) {
	this.sampleType = sampleType;
    }

    /**
     * @return the ccName
     */
    public String getCcName() {
	return ccName;
    }

    /**
     * @param ccName
     *            the ccName to set
     */
    public void setCcName(String ccName) {
	this.ccName = ccName;
    }

    /**
     * @return the cropName
     */
    public String getCropName() {
	return cropName;
    }

    /**
     * @param cropName
     *            the cropName to set
     */
    public void setCropName(String cropName) {
	this.cropName = cropName;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
	return customerName;
    }

    /**
     * @param customerName
     *            the customerName to set
     */
    public void setCustomerName(String customerName) {
	this.customerName = customerName;
    }

    /**
     * @return the serialNumber
     */
    public String getSerialNumber() {
	return serialNumber;
    }

    /**
     * @param serialNumber
     *            the serialNumber to set
     */
    public void setSerialNumber(String serialNumber) {
	this.serialNumber = serialNumber;
    }

    /**
     * @return the deviceType
     */
    public String getDeviceType() {
	return deviceType;
    }

    /**
     * @param deviceType
     *            the deviceType to set
     */
    public void setDeviceType(String deviceType) {
	this.deviceType = deviceType;
    }

    /**
     * @return the installationCenterName
     */
    public String getInstallationCenterName() {
	return installationCenterName;
    }

    /**
     * @param installationCenterName
     *            the installationCenterName to set
     */
    public void setInstallationCenterName(String installationCenterName) {
	this.installationCenterName = installationCenterName;
    }

    /**
     * @return the customerUuid
     */
    //	public String getCustomerUuid() {
    //		return customerUuid;
    //	}
    //
    //	/**
    //	 * @param customerUuid the customerUuid to set
    //	 */
    //	public void setCustomerUuid(String customerUuid) {
    //		this.customerUuid = customerUuid;
    //	}

    /**
     * @return the userUuid
     */
    public String getUserUuid() {
	return userUuid;
    }

    /**
     * @param userUuid
     *            the userUuid to set
     */
    public void setUserUuid(String userUuid) {
	this.userUuid = userUuid;
    }

    /**
     * @return the deviceId
     */
    public Long getDeviceId() {
	return deviceId;
    }

    /**
     * @param deviceId
     *            the deviceId to set
     */
    public void setDeviceId(Long deviceId) {
	this.deviceId = deviceId;
    }

    /**
     * @return the deviceTypeId
     */
    public Long getDeviceTypeId() {
	return deviceTypeId;
    }

    /**
     * @param deviceTypeId
     *            the deviceTypeId to set
     */
    public void setDeviceTypeId(Long deviceTypeId) {
	this.deviceTypeId = deviceTypeId;
    }

    /**
     * @return the installationCenterTypeId
     */
    public Long getInstallationCenterTypeId() {
	return installationCenterTypeId;
    }

    /**
     * @param installationCenterTypeId
     *            the installationCenterTypeId to set
     */
    public void setInstallationCenterTypeId(Long installationCenterTypeId) {
	this.installationCenterTypeId = installationCenterTypeId;
    }

    /**
     * @return the installationCenterTypeName
     */
    public String getInstallationCenterTypeName() {
	return installationCenterTypeName;
    }

    /**
     * @param installationCenterTypeName
     *            the installationCenterTypeName to set
     */
    public void setInstallationCenterTypeName(String installationCenterTypeName) {
	this.installationCenterTypeName = installationCenterTypeName;
    }

    /**
     * @return the commercialLocationId
     */
    public Long getCommercialLocationId() {
	return commercialLocationId;
    }

    /**
     * @param commercialLocationId
     *            the commercialLocationId to set
     */
    public void setCommercialLocationId(Long commercialLocationId) {
	this.commercialLocationId = commercialLocationId;
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
     * @return the stateId
     */
    public Long getStateId() {
	return stateId;
    }

    /**
     * @param stateId
     *            the stateId to set
     */
    public void setStateId(Long stateId) {
	this.stateId = stateId;
    }

    /**
     * @return the deviceGroupId
     */
    public Long getDeviceGroupId() {
	return deviceGroupId;
    }

    /**
     * @param deviceGroupId
     *            the deviceGroupId to set
     */
    public void setDeviceGroupId(Long deviceGroupId) {
	this.deviceGroupId = deviceGroupId;
    }

    /**
     * @return the deviceSubTypeId
     */
    public Long getDeviceSubTypeId() {
	return deviceSubTypeId;
    }

    /**
     * @param deviceSubTypeId
     *            the deviceSubTypeId to set
     */
    public void setDeviceSubTypeId(Long deviceSubTypeId) {
	this.deviceSubTypeId = deviceSubTypeId;
    }

    /**
     * @return the hwRevision
     */
    public String getHwRevision() {
	return hwRevision;
    }

    /**
     * @param hwRevision
     *            the hwRevision to set
     */
    public void setHwRevision(String swRevision) {
	this.hwRevision = swRevision;
    }

    /**
     * @return the fwRevision
     */
    public String getFwRevision() {
	return fwRevision;
    }

    /**
     * @param fwRevision
     *            the fwRevision to set
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
     * @param startOfLife
     *            the startOfLife to set
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
     * @param endOfLife
     *            the endOfLife to set
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
     * @param startOfService
     *            the startOfService to set
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
     * @param endOfService
     *            the endOfService to set
     */
    public void setEndOfService(Long endOfService) {
	this.endOfService = endOfService;
    }

    /**
     * @return the sensorProfileId
     */
    public Long getSensorProfileId() {
	return sensorProfileId;
    }

    /**
     * @param sensorProfileId
     *            the sensorProfileId to set
     */
    public void setSensorProfileId(Long sensorProfileId) {
	this.sensorProfileId = sensorProfileId;
    }

    /**
     * @return the deviceGroupDesc
     */
    public String getDeviceGroupDesc() {
	return deviceGroupDesc;
    }

    /**
     * @param deviceGroupDesc
     *            the deviceGroupDesc to set
     */
    public void setDeviceGroupDesc(String deviceGroupDesc) {
	this.deviceGroupDesc = deviceGroupDesc;
    }

    /**
     * @return the deviceSubTypeDesc
     */
    public String getDeviceSubTypeDesc() {
	return deviceSubTypeDesc;
    }

    /**
     * @param deviceSubTypeDesc
     *            the deviceSubTypeDesc to set
     */
    public void setDeviceSubTypeDesc(String deviceSubTypeDesc) {
	this.deviceSubTypeDesc = deviceSubTypeDesc;
    }

    /**
     * @return the sensorProfileDesc
     */
    public String getSensorProfileDesc() {
	return sensorProfileDesc;
    }

    /**
     * @param sensorProfileDesc
     *            the sensorProfileDesc to set
     */
    public void setSensorProfileDesc(String sensorProfileDesc) {
	this.sensorProfileDesc = sensorProfileDesc;
    }

    /**
     * @return the statusId
     */
    public Long getStatusId() {
	return statusId;
    }

    /**
     * @param statusId
     *            the statusId to set
     */
    public void setStatusId(Long statusId) {
	this.statusId = statusId;
    }

    /**
     * @return the statusDesc
     */
    public String getStatusDesc() {
	return statusDesc;
    }

    /**
     * @param statusDesc
     *            the statusDesc to set
     */
    public void setStatusDesc(String statusDesc) {
	this.statusDesc = statusDesc;
    }

    public Long getCustomerId() {
	return customerId;
    }

    public void setCustomerId(Long customerId) {
	this.customerId = customerId;
    }

    public String getVendorName() {
	return vendorName;
    }

    public void setVendorName(String vendorName) {
	this.vendorName = vendorName;
    }

    public Long getCity() {
	return city;
    }

    public void setCity(Long city) {
	this.city = city;
    }

    public Long getState() {
	return state;
    }

    public void setState(Long state) {
	this.state = state;
    }

    public Long getCountry() {
	return country;
    }

    public void setCountry(Long country) {
	this.country = country;
    }

    /**
     * @return the coldStoreId
     */
    public Long getColdStoreId() {
	return coldStoreId;
    }

    /**
     * @param coldStoreId
     *            the coldStoreId to set
     */
    public void setColdStoreId(Long coldStoreId) {
	this.coldStoreId = coldStoreId;
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

    @Override
	public String toString() {
		return "DeviceModel [serialNumber=" + serialNumber + ", deviceType=" + deviceType + ", installationCenterName="
				+ installationCenterName + ", customerId=" + customerId + ", userUuid=" + userUuid + ", deviceId="
				+ deviceId + ", deviceTypeId=" + deviceTypeId + ", city=" + city + ", state=" + state + ", country="
				+ country + ", installationCenterTypeId=" + installationCenterTypeId + ", installationCenterTypeName="
				+ installationCenterTypeName + ", commercialLocationId=" + commercialLocationId + ", totalCount="
				+ totalCount + ", stateId=" + stateId + ", deviceGroupId=" + deviceGroupId + ", deviceSubTypeId="
				+ deviceSubTypeId + ", hwRevision=" + hwRevision + ", fwRevision=" + fwRevision + ", startOfLife="
				+ startOfLife + ", endOfLife=" + endOfLife + ", startOfService=" + startOfService + ", endOfService="
				+ endOfService + ", sensorProfileId=" + sensorProfileId + ", deviceGroupDesc=" + deviceGroupDesc
				+ ", deviceSubTypeDesc=" + deviceSubTypeDesc + ", sensorProfileDesc=" + sensorProfileDesc
				+ ", statusId=" + statusId + ", statusDesc=" + statusDesc + ", vendorName=" + vendorName
				+ ", coldStoreId=" + coldStoreId + ", userId=" + userId + ", createdOn=" + createdOn
				+ ", deviationProfileId=" + deviationProfileId + ", deviationProfileDesc=" + deviationProfileDesc
				+ ", escalationLevel=" + escalationLevel + ", escalationCounter=" + escalationCounter
				+ ", escalationInitiatedOn=" + escalationInitiatedOn + ", count=" + count + ", customerName="
				+ customerName + ", sfValues=" + sfValues + ", sampleType=" + sampleType + ", ccName=" + ccName
				+ ", cropName=" + cropName + ", cropId=" + cropId + ", serialNo=" + serialNo + ", cropNameC="
				+ cropNameC + ", cropIdC=" + cropIdC + ", isSubscribed=" + isSubscribed + ", categoryId=" + categoryId
				+ ", commodityIds=" + Arrays.toString(commodityIds) + ", userName=" + userName + ", customerEmail="
				+ customerEmail + ", userEmail=" + userEmail + ", stateManagerName=" + stateManagerName
				+ ", stateManagerEmail=" + stateManagerEmail + ", stateManagerId=" + stateManagerId + "]";
	}

    public Long getCreatedOn() {
	return createdOn;
    }

    public void setCreatedOn(Long createdOn) {
	this.createdOn = createdOn;
    }

    public Long getDeviationProfileId() {
	return deviationProfileId;
    }

    public void setDeviationProfileId(Long deviationProfileId) {
	this.deviationProfileId = deviationProfileId;
    }

    public String getDeviationProfileDesc() {
	return deviationProfileDesc;
    }

    public void setDeviationProfileDesc(String deviationProfileDesc) {
	this.deviationProfileDesc = deviationProfileDesc;
    }

    public Integer getEscalationLevel() {
	return escalationLevel;
    }

    public void setEscalationLevel(Integer escalationLevel) {
	this.escalationLevel = escalationLevel;
    }

    public Integer getEscalationCounter() {
	return escalationCounter;
    }

    public void setEscalationCounter(Integer escalationCounter) {
	this.escalationCounter = escalationCounter;
    }

    public Long getEscalationInitiatedOn() {
	return escalationInitiatedOn;
    }

    public void setEscalationInitiatedOn(Long escalationInitiatedOn) {
	this.escalationInitiatedOn = escalationInitiatedOn;
    }

    public Long getCount() {
	return count;
    }

    public void setCount(Long count) {
	this.count = count;
    }

    public Boolean getIsSubscribed() {
	return isSubscribed;
    }

    public void setIsSubscribed(Boolean isSubscribed) {
	this.isSubscribed = isSubscribed;
    }

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long[] getCommodityIds() {
		return commodityIds;
	}

	public void setCommodityIds(Long[] commodityIds) {
		this.commodityIds = commodityIds;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getStateManagerName() {
		return stateManagerName;
	}

	public void setStateManagerName(String stateManagerName) {
		this.stateManagerName = stateManagerName;
	}

	public String getStateManagerEmail() {
		return stateManagerEmail;
	}

	public void setStateManagerEmail(String stateManagerEmail) {
		this.stateManagerEmail = stateManagerEmail;
	}

	public Long getStateManagerId() {
		return stateManagerId;
	}

	public void setStateManagerId(Long stateManagerId) {
		this.stateManagerId = stateManagerId;
	}

	public List<DeviceCommodityModel> getDeviceCommodities() {
		return deviceCommodities;
	}

	public void setDeviceCommodities(List<DeviceCommodityModel> deviceCommodities) {
		this.deviceCommodities = deviceCommodities;
	}

}
