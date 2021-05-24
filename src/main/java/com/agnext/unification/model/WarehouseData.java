package com.agnext.unification.model;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WarehouseData {

	@JsonProperty("warehouse_id")
	private Long warehouseId;

	@JsonProperty("warehouse_name")
	private String warehouseName;

	@JsonProperty("location_name")
	private String locationName;

	@JsonProperty("warehouse_code")
	private String warehouseCode;

	@JsonProperty("total_quantity")
	private BigDecimal totalQuantity;

	@JsonProperty("quantity_unit")
	private String quantityUnit;

	@JsonProperty("device_serial_no")
	private String deviceSerialNo;

	@JsonProperty("warehouse_daily_data")
	private LinkedHashMap<String, BigDecimal> dailyData;

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
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

	public LinkedHashMap<String, BigDecimal> getDailyData() {
		return dailyData;
	}

	public void setDailyData(LinkedHashMap<String, BigDecimal> dailyData) {
		this.dailyData = dailyData;
	}

	@Override
	public String toString() {
		return "WarehouseData [warehouseId=" + warehouseId + ", warehouseName=" + warehouseName + ", locationName="
				+ locationName + ", warehouseCode=" + warehouseCode + ", totalQuantity=" + totalQuantity
				+ ", quantityUnit=" + quantityUnit + ", deviceSerialNo=" + deviceSerialNo + ", dailyData=" + dailyData
				+ "]";
	}

	public String getDeviceSerialNo() {
		return deviceSerialNo;
	}

	public void setDeviceSerialNo(String deviceSerialNo) {
		this.deviceSerialNo = deviceSerialNo;
	}
}
