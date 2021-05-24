package com.agnext.unification.model;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TotalCollectionModel {

	@JsonProperty("total_collections")
	private BigDecimal totalCollection;

	@JsonProperty("collections_unit")
	private String collectionUnit;
	
	@JsonProperty("commodity_wise_collection")
	private LinkedHashMap<String, BigDecimal> commodityWiseCollection;
	
	@JsonProperty("collection_daily_data")
	private LinkedHashMap<String, BigDecimal> dailyData;

	public BigDecimal getTotalCollection() {
		return totalCollection;
	}

	public void setTotalCollection(BigDecimal totalCollection) {
		this.totalCollection = totalCollection;
	}

	public String getCollectionUnit() {
		return collectionUnit;
	}

	public void setCollectionUnit(String collectionUnit) {
		this.collectionUnit = collectionUnit;
	}

	public LinkedHashMap<String, BigDecimal> getCommodityWiseCollection() {
		return commodityWiseCollection;
	}

	public void setCommodityWiseCollection(LinkedHashMap<String, BigDecimal> commodityWiseCollection) {
		this.commodityWiseCollection = commodityWiseCollection;
	}

	public LinkedHashMap<String, BigDecimal> getDailyData() {
		return dailyData;
	}

	public void setDailyData(LinkedHashMap<String, BigDecimal> dailyData) {
		this.dailyData = dailyData;
	}

	@Override
	public String toString() {
		return "TotalCollectionModel [totalCollection=" + totalCollection + ", collectionUnit=" + collectionUnit
				+ ", commodityWiseCollection=" + commodityWiseCollection + ", dailyData=" + dailyData + "]";
	}

	
	
}
