package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryCommodityVarietyWrapperModel {

	@JsonProperty("commodity_details")
	private List<CategoryCommodityVarietyModel> ccvModel;

	public List<CategoryCommodityVarietyModel> getCcvModel() {
		return ccvModel;
	}

	public void setCcvModel(List<CategoryCommodityVarietyModel> ccvModel) {
		this.ccvModel = ccvModel;
	}
	
}
