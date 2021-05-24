package com.agnext.unification.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class ClientAvgMinMaxModel {

	@JsonProperty("max_inst_center_id")
	private Long maxInstCenterId;

	@JsonProperty("max_farmer_id")
	private Long maxClientId;

	@JsonProperty("min_farmer_id")
	private Long minClientId;

	@JsonProperty("min_inst_center_id")
	private Long minInstCenterId;
	
	
	@JsonProperty("max_inst_center_name")
	private String maxInstCenterName;

	@JsonProperty("max_farmer_name")
	private String maxClientName;

	@JsonProperty("min_farmer_name")
	private String minClientName;

	@JsonProperty("min_inst_center_name")
	private String minInstCenterName;

	@JsonProperty("max_quantity")
	private BigDecimal maxCollection;

	@JsonProperty("min_quantity")
	private BigDecimal minCollection;

	@JsonProperty("increment")
	private BigDecimal increment;

	@JsonProperty("decrement")
	private BigDecimal decrement;

	@JsonProperty("increment_percentage ")
	private BigDecimal incrementPercentage;

	@JsonProperty("decrement_percentage ")
	private BigDecimal decrementPercentage;

	@JsonProperty("increment_graph_data")
	private List<GraphDataModel> incrementGraphData;

	@JsonProperty("decrement_graph_data")
	private List<GraphDataModel> decrementGraphData;

	public Long getMaxInstCenterId() {
		return maxInstCenterId;
	}

	public void setMaxInstCenterId(Long maxInstCenterId) {
		this.maxInstCenterId = maxInstCenterId;
	}

	public Long getMaxClientId() {
		return maxClientId;
	}

	public void setMaxClientId(Long maxFarmerId) {
		this.maxClientId = maxFarmerId;
	}

	public Long getMinClientId() {
		return minClientId;
	}

	public void setMinClientId(Long minFarmerId) {
		this.minClientId = minFarmerId;
	}

	public Long getMinInstCenterId() {
		return minInstCenterId;
	}

	public void setMinInstCenterId(Long minInstCenterId) {
		this.minInstCenterId = minInstCenterId;
	}

	public String getMaxInstCenterName() {
		return maxInstCenterName;
	}

	public void setMaxInstCenterName(String maxInstCenterName) {
		this.maxInstCenterName = maxInstCenterName;
	}

	public String getMaxClientName() {
		return maxClientName;
	}

	public void setMaxClientName(String maxFarmerName) {
		this.maxClientName = maxFarmerName;
	}

	public String getMinClientName() {
		return minClientName;
	}

	public void setMinClientName(String minFarmerName) {
		this.minClientName = minFarmerName;
	}

	public String getMinInstCenterName() {
		return minInstCenterName;
	}

	public void setMinInstCenterName(String minInstCenterName) {
		this.minInstCenterName = minInstCenterName;
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

	public BigDecimal getIncrement() {
		return increment;
	}

	public void setIncrement(BigDecimal increment) {
		this.increment = increment;
	}

	public BigDecimal getDecrement() {
		return decrement;
	}

	public void setDecrement(BigDecimal decrement) {
		this.decrement = decrement;
	}

	public BigDecimal getIncrementPercentage() {
		return incrementPercentage;
	}

	public void setIncrementPercentage(BigDecimal incrementPercentage) {
		this.incrementPercentage = incrementPercentage;
	}

	public BigDecimal getDecrementPercentage() {
		return decrementPercentage;
	}

	public void setDecrementPercentage(BigDecimal decrementPercentage) {
		this.decrementPercentage = decrementPercentage;
	}

	public List<GraphDataModel> getIncrementGraphData() {
		return incrementGraphData;
	}

	public void setIncrementGraphData(List<GraphDataModel> incrementGraphData) {
		this.incrementGraphData = incrementGraphData;
	}

	public List<GraphDataModel> getDecrementGraphData() {
		return decrementGraphData;
	}

	public void setDecrementGraphData(List<GraphDataModel> decrementGraphData) {
		this.decrementGraphData = decrementGraphData;
	}

	@Override
	public String toString() {
		return "ClientAvgMinMaxModel [maxInstCenterId=" + maxInstCenterId + ", maxClientId=" + maxClientId
				+ ", minClientId=" + minClientId + ", minInstCenterId=" + minInstCenterId + ", maxInstCenterName="
				+ maxInstCenterName + ", maxClientName=" + maxClientName + ", minClientName=" + minClientName
				+ ", minInstCenterName=" + minInstCenterName + ", maxCollection=" + maxCollection + ", minCollection="
				+ minCollection + ", increment=" + increment + ", decrement=" + decrement + ", incrementPercentage="
				+ incrementPercentage + ", decrementPercentage=" + decrementPercentage + ", incrementGraphData="
				+ incrementGraphData + ", decrementGraphData=" + decrementGraphData + "]";
	}
	


}
