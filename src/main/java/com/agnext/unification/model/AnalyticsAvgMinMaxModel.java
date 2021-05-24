package com.agnext.unification.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnalyticsAvgMinMaxModel {

	@JsonProperty("average_quantity")
	private BigDecimal averageCollection;

	@JsonProperty("total_quantity")
	private BigDecimal totalCollection;

	@JsonProperty("max_quantity")
	private BigDecimal maxCollection;

	@JsonProperty("min_quantity")
	private BigDecimal minCollection;
	
	@JsonProperty("unit")
	private String unit;

	public BigDecimal getAverageCollection() {
		return averageCollection;
	}

	public void setAverageCollection(BigDecimal averageCollection) {
		this.averageCollection = averageCollection;
	}

	public BigDecimal getTotalCollection() {
		return totalCollection;
	}

	public void setTotalCollection(BigDecimal totalCollection) {
		this.totalCollection = totalCollection;
	}

	public BigDecimal getMaxCollection() {
		return maxCollection;
	}

	public void setMaxCollection(BigDecimal maxCollection) {
		this.maxCollection = maxCollection;
	}

	public BigDecimal getMinCollection() {
		return minCollection;
	}

	public void setMinCollection(BigDecimal minCollection) {
		this.minCollection = minCollection;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "AnalyticsAvgMinMaxModel [averageCollection=" + averageCollection + ", totalCollection="
				+ totalCollection + ", maxCollection=" + maxCollection + ", minCollection=" + minCollection + ", unit="
				+ unit + "]";
	}
	
}
