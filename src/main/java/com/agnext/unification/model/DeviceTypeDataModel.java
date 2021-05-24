package com.agnext.unification.model;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceTypeDataModel {
	@JsonProperty("device_type_name")
	private String deviceTypeName;
	
	@JsonProperty("total_quantity")
	private BigDecimal totalQuantity;

	@JsonProperty("quantity_unit")
	private String quantityUnit;

	@JsonProperty("difference")
	private BigDecimal difference;

	@JsonProperty("difference_percentage")
	private BigDecimal differencePercentage;
	
	@JsonProperty("device_type_id")
	private Long deviceTypeId;
	
	@JsonProperty("device_id")
	private Long deviceId;
	
	@JsonProperty("device_serial_no")
	private String deviceSerialNo;
	
	@JsonProperty("device_daily_data")
	private Map<String, BigDecimal> dailyData;

	public String getDeviceSerialNo() {
		return deviceSerialNo;
	}

	public void setDeviceSerialNo(String deviceSerialNo) {
		this.deviceSerialNo = deviceSerialNo;
	}

	public Long getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(Long deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	public BigDecimal getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(BigDecimal totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public String getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public BigDecimal getDifference() {
		return difference;
	}

	public void setDifference(BigDecimal difference) {
		this.difference = difference;
	}

	public BigDecimal getDifferencePercentage() {
		return differencePercentage;
	}

	public void setDifferencePercentage(BigDecimal differencePercentage) {
		this.differencePercentage = differencePercentage;
	}

	public Map<String, BigDecimal> getDailyData() {
		return dailyData;
	}

	public void setDailyData(Map<String, BigDecimal> dailyData) {
		this.dailyData = dailyData;
	}

	@Override
	public String toString() {
		return "DeviceTypeDataModel [deviceTypeName=" + deviceTypeName + ", totalQuantity=" + totalQuantity
				+ ", quantityUnit=" + quantityUnit + ", difference=" + difference + ", differencePercentage="
				+ differencePercentage + ", dailyData=" + dailyData + "]";
	}
	
	

}
