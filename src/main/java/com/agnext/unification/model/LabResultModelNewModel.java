package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LabResultModelNewModel {

	@JsonProperty("batch_id")
	private String batchId;
	
	@JsonProperty("scan_id")
	private Long scanId;
	
	@JsonProperty("commodity_name")
	private String commodityName;
	
	@JsonProperty("commodity_id")
	private Long commodityId;
	
	@JsonProperty("device_serial_no")
	private String deviceSerialNo;
	
	@JsonProperty("date_done")
	private Long dateDone;
	
	@JsonProperty("weight")
	private Double weight;
	
	@JsonProperty("approval")
	private Integer approval;
	
	@JsonProperty("unit")
	private String unit;
	
	@JsonProperty("analytics")
	private List<Analytics> analytics;
	
	@JsonProperty("customer_name")
	private String customerName;
	
	@JsonProperty("customer_id")
	private Long customerId;
	
	
	public String getDeviceSerialNo() {
		return deviceSerialNo;
	}
	public void setDeviceSerialNo(String deviceSerialNo) {
		this.deviceSerialNo = deviceSerialNo;
	}
	public Long getScanId() {
		return scanId;
	}
	public void setScanId(Long scanId) {
		this.scanId = scanId;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public Long getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}
	public List<Analytics> getAnalytics() {
		return analytics;
	}
	public void setAnalytics(List<Analytics> analytics) {
		this.analytics = analytics;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Integer getApproval() {
		return approval;
	}
	public void setApproval(Integer approval) {
		this.approval = approval;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getDateDone() {
		return dateDone;
	}
	public void setDateDone(Long dateDone) {
		this.dateDone = dateDone;
	}
	
	
}
