package com.agnext.unification.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class DeviceOrderModel {
	
	@JsonProperty("device_order_id")
	private Long deviceOrderId;

	@JsonProperty("commodities")
	private List<Long> commodities;

	@JsonProperty("mode")
	private String mode;


	@JsonProperty("customer_id")
	private Long customerId;

	@JsonProperty("device_type_id")
	private Long deviceTypeId;

	@JsonProperty("device_type_desc")
	private String  deviceTypeDesc;

	@JsonProperty("device_count")
	private Integer deviceCount;

	@JsonProperty("remarks")
	private String remarks;
	
	@JsonProperty("commodity_model")
	private List<CommodityModel> commodityModel;
	
	@JsonProperty("device_order_random_id")
	private String deviceOrderRandomId;
	
	@JsonProperty("status_id")
	private Long statusId;
	
	@JsonProperty("status_name")
	private String statusName;
	
	@JsonProperty("total_count")
	private Long count;

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
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
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
	 * @return the deviceTypeDesc
	 */
	public String getDeviceTypeDesc() {
		return deviceTypeDesc;
	}

	/**
	 * @param deviceTypeDesc the deviceTypeDesc to set
	 */
	public void setDeviceTypeDesc(String deviceTypeDesc) {
		this.deviceTypeDesc = deviceTypeDesc;
	}

	/**
	 * @return the deviceCount
	 */
	public Integer getDeviceCount() {
		return deviceCount;
	}

	/**
	 * @param deviceCount the deviceCount to set
	 */
	public void setDeviceCount(Integer deviceCount) {
		this.deviceCount = deviceCount;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the deviceOrderId
	 */
	public Long getDeviceOrderId() {
		return deviceOrderId;
	}

	/**
	 * @param deviceOrderId the deviceOrderId to set
	 */
	public void setDeviceOrderId(Long deviceOrderId) {
		this.deviceOrderId = deviceOrderId;
	}

	/**
	 * @return the commodityModel
	 */
	public List<CommodityModel> getCommodityModel() {
		return commodityModel;
	}

	/**
	 * @param commodityModel the commodityModel to set
	 */
	public void setCommodityModel(List<CommodityModel> commodityModel) {
		this.commodityModel = commodityModel;
	}

	/**
	 * @return the deviceOrderRandomId
	 */
	public String getDeviceOrderRandomId() {
		return deviceOrderRandomId;
	}

	/**
	 * @param deviceOrderRandomId the deviceOrderRandomId to set
	 */
	public void setDeviceOrderRandomId(String deviceOrderRandomId) {
		this.deviceOrderRandomId = deviceOrderRandomId;
	}

	/**
	 * @return the statusId
	 */
	public Long getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}
