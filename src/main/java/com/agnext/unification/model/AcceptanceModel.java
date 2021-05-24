package com.agnext.unification.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class AcceptanceModel {

	@JsonProperty("total_avg_accepted_rate")
	private BigDecimal totalAcceptedRate;
	
	@JsonProperty("per_day_accepted")
	List<ScanCountModel> list;
	
	@JsonProperty("commodity_variance")
	private List<CommodityVarianceModel> commodityVarianceList;
	@JsonProperty("commodity_accepted_rate")
	private List<CommodityVarianceModel> commodityAcceptedList;

	public BigDecimal getTotalAcceptedRate() {
		return totalAcceptedRate;
	}

	public void setTotalAcceptedRate(BigDecimal totalAcceptedRate) {
		this.totalAcceptedRate = totalAcceptedRate;
	}

	public List<ScanCountModel> getList() {
		return list;
	}

	public void setList(List<ScanCountModel> list) {
		this.list = list;
	}

	public List<CommodityVarianceModel> getCommodityVarianceList() {
		return commodityVarianceList;
	}

	public void setCommodityVarianceList(List<CommodityVarianceModel> commodityVarianceList) {
		this.commodityVarianceList = commodityVarianceList;
	}

	public List<CommodityVarianceModel> getCommodityAcceptedList() {
		return commodityAcceptedList;
	}

	public void setCommodityAcceptedList(List<CommodityVarianceModel> commodityAcceptedList) {
		this.commodityAcceptedList = commodityAcceptedList;
	}
	
	
}
