package com.agnext.unification.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QualityMapModel {
	@JsonProperty("collector")
	private String collector;

	@JsonProperty("center_name")
	private String centerName;

	@JsonProperty("avg_quality")
	private BigDecimal avgQuality;

	@JsonProperty("coordinates")
	private List<CordinateModel> coordinates;


	public String getCollector() {
		return collector;
	}

	public void setCollector(String collector) {
		this.collector = collector;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public BigDecimal getAvgQuality() {
		return avgQuality;
	}

	public void setAvgQuality(BigDecimal avgQuality) {
		this.avgQuality = avgQuality;
	}

	public List<CordinateModel> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<CordinateModel> coordinates) {
		this.coordinates = coordinates;
	}

}
