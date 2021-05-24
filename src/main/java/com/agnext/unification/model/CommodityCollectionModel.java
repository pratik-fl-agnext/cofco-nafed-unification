package com.agnext.unification.model;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommodityCollectionModel {

	@JsonProperty("commodity_name")
	private String commodityName;

	@JsonProperty("commodity_id")
	private Long commodityId;
	
	@JsonProperty("total")
	private BigDecimal total;

	@JsonProperty("unit")
	private String unit;

	@JsonProperty("daily_data")
	private Map<String, BigDecimal> dailyData;
	
//	@JsonProperty("daily_data")
//	private List<String> dailyData;

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

//	public List<String> getDailyData() {
//		return dailyData;
//	}
//
//	public void setDailyData(List<String> dailyData) {
//		this.dailyData = dailyData;
//	}

	public Long getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}

	public Map<String, BigDecimal> getDailyData() {
		return dailyData;
	}

	public void setDailyData(Map<String, BigDecimal> dailyData) {
		this.dailyData = dailyData;
	}

//	public List<DailyDataModel> getDailyData() {
//		return dailyData;
//	}
//
//	public void setDailyData(List<DailyDataModel> dailyData) {
//		this.dailyData = dailyData;
//	}

	
}
