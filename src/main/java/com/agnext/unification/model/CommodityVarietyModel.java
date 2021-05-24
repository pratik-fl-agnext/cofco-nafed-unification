package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class CommodityVarietyModel.
 */
@JsonIgnoreProperties
public class CommodityVarietyModel {

	@JsonProperty("commodity_id")
	private Long commodityId;
	
	@JsonProperty("commodity_name")
	private String commodityName;
	
	@JsonProperty("varieties")
	private List<VarietyModel> varieties;

	public Long getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public List<VarietyModel> getVarieties() {
		return varieties;
	}

	public void setVarieties(List<VarietyModel> varieties) {
		this.varieties = varieties;
	}
	
}
