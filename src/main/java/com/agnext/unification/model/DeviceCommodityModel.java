package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceCommodityModel {

	@JsonProperty("category_id")
	private Long categoryId;

	@JsonProperty("category_name")
	private String categoryName;
	
	@JsonProperty("commodities")
	private List<CommodityModel> commodities;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<CommodityModel> getCommodities() {
		return commodities;
	}

	public void setCommodities(List<CommodityModel> commodities) {
		this.commodities = commodities;
	}
	
	
}
