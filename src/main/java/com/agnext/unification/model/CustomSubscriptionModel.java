package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class CustomSubscriptionModel {
	@JsonProperty("device_serial_number")
	private String deviceSerialNumber;

	@JsonProperty("customer_id")
	private Long customerId;

	@JsonProperty("package_name")
	private String packageName;

	@JsonProperty("number_of_scan")
	private Long numberOfScan;

	@JsonProperty("from_date")
	private String fromDate;

	@JsonProperty("to_date")
	private String toDate;

	@JsonProperty("commodity")
	private List<CommodityPriceModel> commodity;

	public CustomSubscriptionModel() {

	}

	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}

	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Long getNumberOfScan() {
		return numberOfScan;
	}

	public void setNumberOfScan(Long numberOfScan) {
		this.numberOfScan = numberOfScan;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public List<CommodityPriceModel> getCommodity() {
		return commodity;
	}

	public void setCommodity(List<CommodityPriceModel> commodity) {
		this.commodity = commodity;
	}

}
