package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class VarianceModel {

	
	@JsonProperty("commodity_variance")
	private List<CommodityVarianceModel> commodityVarianceList;
//	
//	@JsonProperty("graph_data")
//	private ScanCountModel scanGraph;
//	

	@JsonProperty("total_avg_variance_rate")
	private Long totalAvgVariance;
	
	@JsonProperty("per_day_variance")
	List<ScanCountModel> list;

	public List<CommodityVarianceModel> getCommodityVarianceList() {
		return commodityVarianceList;
	}

	public void setCommodityVarianceList(List<CommodityVarianceModel> commodityVarianceList) {
		this.commodityVarianceList = commodityVarianceList;
	}


	public List<ScanCountModel> getList() {
		return list;
	}

	public void setList(List<ScanCountModel> list) {
		this.list = list;
	}

	public Long getTotalAvgVariance() {
		return totalAvgVariance;
	}

	public void setTotalAvgVariance(Long totalAvgVariance) {
		this.totalAvgVariance = totalAvgVariance;
	}
	
	
	
}
