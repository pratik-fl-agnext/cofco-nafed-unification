package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class ScanDataModel {
	
//	@JsonProperty("scan_data")
//	List<ScanCountDataModel> scanData;
//	
//	@JsonProperty("commodity_data")
//	List<CommodityDataModel> commodityData;
//
//	public List<ScanCountDataModel> getScanData() {
//		return scanData;
//	}
//
//	public void setScanData(List<ScanCountDataModel> scanData) {
//		this.scanData = scanData;
//	}
//
//	public List<CommodityDataModel> getCommodityData() {
//		return commodityData;
//	}
//
//	public void setCommodityData(List<CommodityDataModel> commodityData) {
//		this.commodityData = commodityData;
//	}
//	
//	

}
