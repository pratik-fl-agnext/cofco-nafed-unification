package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class DeviceTypeVO {

	@JsonProperty("device_type_id")
	private Long id;

	@JsonProperty("device_type_name")
	private String deviceType;

	@JsonProperty("available_device_count")
	private Long deviceCount;

	;

	@JsonProperty("device_cost")
	private Double devicePrice;

	@JsonProperty("device_type")
	private String deviceTypeName;

	@JsonProperty("device_t_id")
	private Long deviceTypeId;

	private List<DevicesVO> devices;
	
	@JsonProperty("licence_no")
	private String licenceNo;

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
	 * @return the deviceType
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * @return the deviceCount
	 */
	public Long getDeviceCount() {
		return deviceCount;
	}

	/**
	 * @param deviceCount the deviceCount to set
	 */
	public void setDeviceCount(Long deviceCount) {
		this.deviceCount = deviceCount;
	}

	public List<DevicesVO> getDevices() {
		return devices;
	}

	public void setDevices(List<DevicesVO> devices) {
		this.devices = devices;
	}

}
