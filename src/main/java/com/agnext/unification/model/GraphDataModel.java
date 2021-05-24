package com.agnext.unification.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class GraphDataModel {

	@JsonProperty("scan_date")
	private String scanDate;

	@JsonProperty("total_collection")
	private BigDecimal totalCollection;

	@JsonProperty("date_done")
	private Long dateDone;

	@JsonProperty("increment_graph_date")
	private Long incrementGraphDate;

	@JsonProperty("decrement_graph_date")
	private Long decrementGraphDate;

	@JsonProperty("increment_graph_total_weight")
	private BigDecimal incrementGraphTotalWeight;

	@JsonProperty("decrement_graph_total_weight")
	private BigDecimal decrementGraphTotalWeight;

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	public BigDecimal getTotalCollection() {
		return totalCollection;
	}

	public void setTotalCollection(BigDecimal totalCollection) {
		this.totalCollection = totalCollection;
	}

	public Long getDateDone() {
		return dateDone;
	}

	public void setDateDone(Long dateDone) {
		this.dateDone = dateDone;
	}

	@Override
	public String toString() {
		return "GraphDataModel [scanDate=" + scanDate + ", totalCollection=" + totalCollection + ", dateDone="
				+ dateDone + "]";
	}

	public Long getIncrementGraphDate() {
		return incrementGraphDate;
	}

	public void setIncrementGraphDate(Long incrementGraphDate) {
		this.incrementGraphDate = incrementGraphDate;
	}

	public Long getDecrementGraphDate() {
		return decrementGraphDate;
	}

	public void setDecrementGraphDate(Long decrementGraphDate) {
		this.decrementGraphDate = decrementGraphDate;
	}

	public BigDecimal getIncrementGraphTotalWeight() {
		return incrementGraphTotalWeight;
	}

	public void setIncrementGraphTotalWeight(BigDecimal incrementGraphTotalWeight) {
		this.incrementGraphTotalWeight = incrementGraphTotalWeight;
	}

	public BigDecimal getDecrementGraphTotalWeight() {
		return decrementGraphTotalWeight;
	}

	public void setDecrementGraphTotalWeight(BigDecimal decrementGraphTotalWeight) {
		this.decrementGraphTotalWeight = decrementGraphTotalWeight;
	}

}
