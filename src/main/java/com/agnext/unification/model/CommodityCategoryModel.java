package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommodityCategoryModel {
	@JsonProperty("commodity_category_id")
	private Long commodityCategoryId;
	
	@JsonProperty("commodity_category_name")
	private String commodityCategoryName;


	/**
	 * @return the commodityCategoryId
	 */
	public Long getCommodityCategoryId() {
		return commodityCategoryId;
	}

	/**
	 * @param commodityCategoryId the commodityCategoryId to set
	 */
	public void setCommodityCategoryId(Long commodityCategoryId) {
		this.commodityCategoryId = commodityCategoryId;
	}

	/**
	 * @return the commodityCategoryName
	 */
	public String getCommodityCategoryName() {
		return commodityCategoryName;
	}

	/**
	 * @param commodityCategoryName the commodityCategoryName to set
	 */
	public void setCommodityCategoryName(String commodityCategoryName) {
		this.commodityCategoryName = commodityCategoryName;
	}
	 
}
