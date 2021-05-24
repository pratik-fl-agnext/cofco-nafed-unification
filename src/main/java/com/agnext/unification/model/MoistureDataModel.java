package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoistureDataModel {
	
	@JsonProperty("moisture_data")
	private List<MoistureMeterResultModel> moistureData;

	@JsonProperty("total_count")
	private Long totalCount;

	public List<MoistureMeterResultModel> getMoistureData() {
		return moistureData;
	}

	public void setMoistureData(List<MoistureMeterResultModel> moistureData) {
		this.moistureData = moistureData;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

}
