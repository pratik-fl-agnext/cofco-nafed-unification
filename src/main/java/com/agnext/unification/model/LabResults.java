package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LabResults {

	@JsonProperty("total_count")
	private Long totalCount;

	@JsonProperty("lab_results")
	private List<LabResultModelNewModel> labModel;

	public List<LabResultModelNewModel> getLabModel() {
		return labModel;
	}

	public void setLabModel(List<LabResultModelNewModel> labModel) {
		this.labModel = labModel;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	

}
