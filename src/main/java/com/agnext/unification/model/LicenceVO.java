package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LicenceVO {

	private Long id;

	@JsonProperty("client_id")
	private Long clientId;

	@JsonProperty("pacakages")
	private Long packages;

	@JsonProperty("commodities")
	private List<Long> commodities;

	@JsonProperty("device_type_id")
	private Long deviceTypeId;

	@JsonProperty("created_on")
	private String createdOn;

	@JsonProperty("created_by")
	private Long createdBy;

	@JsonProperty("updated_on")
	private String updatedOn;

	@JsonProperty("updated_by")
	private Long updatedBy;

	@JsonProperty("status")
	private Integer status;

	@JsonProperty("device_codes")
	private List<String> devicesCode;

	@JsonProperty("device_ids")
	private List<Long> devicesId;
	
	@JsonProperty("package_id")
	private Long packageId;

	@JsonProperty("device_cost")
	private Double devicePrice;

	@JsonProperty("device_type_name")
	private String deviceTypeName;

	@JsonProperty("device_type_ids")
	private Integer dTypeId;

	@JsonProperty("license_no")
	private String licenceNo;
	
	@JsonProperty("number_of_devices")
	private Integer numberOfDevices;
	
	@JsonProperty("commodity_ids")
	private Long [] commodityIds;
	
	@JsonProperty("device_serial_no")
	private String deviceSerialNo;

	
	/**
	 * @return the numberOfDevices
	 */
	public Integer getNumberOfDevices() {
		return numberOfDevices;
	}

	/**
	 * @param numberOfDevices the numberOfDevices to set
	 */
	public void setNumberOfDevices(Integer numberOfDevices) {
		this.numberOfDevices = numberOfDevices;
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
	 * @return the devicesId
	 */
	public List<Long> getDevicesId() {
		return devicesId;
	}

	/**
	 * @param devicesId the devicesId to set
	 */
	public void setDevicesId(List<Long> devicesId) {
		this.devicesId = devicesId;
	}

	private List<PackagesVO> packagesList;

	/**
	 * @return the dTypeId
	 */
	public Integer getdTypeId() {
		return dTypeId;
	}

	/**
	 * @param dTypeId the dTypeId to set
	 */
	public void setdTypeId(Integer dTypeId) {
		this.dTypeId = dTypeId;
	}

	/**
	 * @return the packagesList
	 */
	public List<PackagesVO> getPackagesList() {
		return packagesList;
	}

	/**
	 * @param packagesList the packagesList to set
	 */
	public void setPackagesList(List<PackagesVO> packagesList) {
		this.packagesList = packagesList;
	}

	/**
	 * @return the devicePrice
	 */
	public Double getDevicePrice() {
		return devicePrice;
	}

	/**
	 * @param devicePrice the devicePrice to set
	 */
	public void setDevicePrice(Double devicePrice) {
		this.devicePrice = devicePrice;
	}

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
	 * @return the devicesCode
	 */
	public List<String> getDevicesCode() {
		return devicesCode;
	}

	/**
	 * @param devicesCode the devicesCode to set
	 */
	public void setDevicesCode(List<String> devicesCode) {
		this.devicesCode = devicesCode;
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
	public Long getPackages() {
		return packages;
	}

	/**
	 * @param packages the packages to set
	 */
	public void setPackages(Long packages) {
		this.packages = packages;
	}

	/**
	 * @return the commodities
	 */
	public List<Long> getCommodities() {
		return commodities;
	}

	/**
	 * @param commodities the commodities to set
	 */
	public void setCommodities(List<Long> commodities) {
		this.commodities = commodities;
	}

	/**
	 * @return the createdOn
	 */
	public String getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(String createdOn) {
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

	public Long[] getCommodityIds() {
		return commodityIds;
	}

	public void setCommodityIds(Long[] commodityIds) {
		this.commodityIds = commodityIds;
	}

	public String getDeviceSerialNo() {
		return deviceSerialNo;
	}

	public void setDeviceSerialNo(String deviceSerialNo) {
		this.deviceSerialNo = deviceSerialNo;
	}

}
