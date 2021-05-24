package com.agnext.unification.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class FarmerDetailsModel {
	@JsonProperty("quantity_difference")
	private BigDecimal quantityDifference;

	@JsonProperty("quantity_difference_percentage")
	private BigDecimal quantityDifferencePercentage;

	@JsonProperty("quality_difference")
	private BigDecimal qualityDifference;

	@JsonProperty("quality_difference_percentage")
	private BigDecimal qualityDifferencePercentage;

	@JsonProperty("quantity_graph_data")
	private List<GraphDataModel> quantityGraphData;

	@JsonProperty("quality_graph_data")
	private List<GraphDataModel> qualityGraphData;
	
	@JsonProperty("unit")
	private String unit ; 

	public BigDecimal getQuantityDifference() {
		return quantityDifference;
	}

	public void setQuantityDifference(BigDecimal quantityDifference) {
		this.quantityDifference = quantityDifference;
	}

	public BigDecimal getQuantityDifferencePercentage() {
		return quantityDifferencePercentage;
	}

	public void setQuantityDifferencePercentage(BigDecimal quantityDifferencePercentage) {
		this.quantityDifferencePercentage = quantityDifferencePercentage;
	}

	public BigDecimal getQualityDifference() {
		return qualityDifference;
	}

	public void setQualityDifference(BigDecimal qualityDifference) {
		this.qualityDifference = qualityDifference;
	}

	public BigDecimal getQualityDifferencePercentage() {
		return qualityDifferencePercentage;
	}

	public void setQualityDifferencePercentage(BigDecimal qualityDifferencePercentage) {
		this.qualityDifferencePercentage = qualityDifferencePercentage;
	}

	public List<GraphDataModel> getQuantityGraphData() {
		return quantityGraphData;
	}

	public void setQuantityGraphData(List<GraphDataModel> quantityGraphData) {
		this.quantityGraphData = quantityGraphData;
	}

	public List<GraphDataModel> getQualityGraphData() {
		return qualityGraphData;
	}

	public void setQualityGraphData(List<GraphDataModel> qualityGraphData) {
		this.qualityGraphData = qualityGraphData;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
