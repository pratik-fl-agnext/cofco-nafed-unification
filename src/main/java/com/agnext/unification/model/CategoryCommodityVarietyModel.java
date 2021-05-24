package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryCommodityVarietyModel {
	
	@JsonProperty("category_id")
	private Long commodityCategoryId;
	
	@JsonProperty("category_name")
	private String commodityCategoryName;
	
	
	@JsonProperty("commodities")
	private List<CommoditiesModel> commodities;


	public Long getCommodityCategoryId() {
		return commodityCategoryId;
	}


	public void setCommodityCategoryId(Long commodityCategoryId) {
		this.commodityCategoryId = commodityCategoryId;
	}


	public String getCommodityCategoryName() {
		return commodityCategoryName;
	}


	public void setCommodityCategoryName(String commodityCategoryName) {
		this.commodityCategoryName = commodityCategoryName;
	}


	public List<CommoditiesModel> getCommodities() {
		return commodities;
	}


	public void setCommodities(List<CommoditiesModel> commodities) {
		this.commodities = commodities;
	}
	

}
