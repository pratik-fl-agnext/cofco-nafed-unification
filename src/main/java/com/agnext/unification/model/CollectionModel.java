package com.agnext.unification.model;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class CollectionModel {
	@JsonProperty("total")
	private BigDecimal total;

	@JsonProperty("unit")
	private String unit;

	@JsonProperty("commodities")
	private List<CommodityCollectionModel> commodities;

//	@JsonProperty("commulative_daily_data")
//	private List<DailyDataModel> dailyData;

	@JsonProperty("commulative_daily_data")
	private Map<String, BigDecimal> dailyData;

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

	public List<CommodityCollectionModel> getCommodities() {
		return commodities;
	}

	public void setCommodities(List<CommodityCollectionModel> commodities) {
		this.commodities = commodities;
	}

	@Override
	public String toString() {
		return "CollectionModel [total=" + total + ", unit=" + unit + ", commodities=" + commodities + "]";
	}

	public Map<String, BigDecimal> getDailyData() {
		return dailyData;
	}

	public void setDailyData(Map<String, BigDecimal> dailyData) {
		this.dailyData = dailyData;
	}

//	public List<String> getDailyData() {
//		return dailyData;
//	}
//
//	public void setDailyData(List<String> dailyData) {
//		this.dailyData = dailyData;
//	}

//	public List<String> getDailyData() {
//		return dailyData;
//	}
//
//	public void setDailyData(List<String> dailyData) {
//		this.dailyData = dailyData;
//	}
}
