package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WarehouseDetailsModel {

	@JsonProperty("warehouse_data")
	List<WarehouseData> warehouseData;

	public List<WarehouseData> getWarehouseData() {
		return warehouseData;
	}

	public void setWarehouseData(List<WarehouseData> warehouseData) {
		this.warehouseData = warehouseData;
	}

	@Override
	public String toString() {
		return "WarehouseDetailsModel [warehouseData=" + warehouseData + "]";
	}
	
}
