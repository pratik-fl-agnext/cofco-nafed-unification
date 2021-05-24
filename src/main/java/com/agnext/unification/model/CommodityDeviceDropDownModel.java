package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommodityDeviceDropDownModel {
	
	@JsonProperty("commodities")
	private List<CommodityNewModel> commodities;

	public List<CommodityNewModel> getCommodities() {
		return commodities;
	}

	public void setCommodities(List<CommodityNewModel> commodities) {
		this.commodities = commodities;
	}

}
