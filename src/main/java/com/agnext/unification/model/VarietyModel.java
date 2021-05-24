package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties
public class VarietyModel {
	
	@JsonProperty("variety_id")
	private Long varietyId;
	
	@JsonProperty("variety_name")
	private String varietyName;

	public Long getVarietyId() {
		return varietyId;
	}

	public void setVarietyId(Long varietyId) {
		this.varietyId = varietyId;
	}

	public String getVarietyName() {
		return varietyName;
	}

	public void setVarietyName(String varietyName) {
		this.varietyName = varietyName;
	}
	
	
}
