package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class AvgScanCountModel {

	@JsonProperty("per_day_accepted")
	List<ScanCountModel> list;
	
	@JsonProperty("commodity_variance")
	private List<CommodityVarianceModel> commodityVarianceList;
	
	@JsonProperty("commodity_scan")
	private List<CommodityVarianceModel> commodityScanList;
	
	@JsonProperty("total_scan_count")
	private Long totalScanCount;

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

	public Long getTotalScanCount() {
		return totalScanCount;
	}

	public void setTotalScanCount(Long totalScanCount) {
		this.totalScanCount = totalScanCount;
	}

	public List<CommodityVarianceModel> getCommodityScanList() {
		return commodityScanList;
	}

	public void setCommodityScanList(List<CommodityVarianceModel> commodityScanList) {
		this.commodityScanList = commodityScanList;
	}
	
	
	
	
}
