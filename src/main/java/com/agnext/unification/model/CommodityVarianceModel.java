package com.agnext.unification.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(Include.NON_NULL)
public class CommodityVarianceModel {

	@JsonProperty("commodity_name")
	private String commodityName;

	@JsonProperty("variance")
	private Long variance;

	@JsonProperty("acceptance")
	private BigDecimal acceptance;
	
	@JsonProperty("scan_count")
	private Long scanCount;

	public Long getScanCount() {
		return scanCount;
	}

	public void setScanCount(Long scanCount) {
		this.scanCount = scanCount;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public Long getVariance() {
		return variance;
	}

	public void setVariance(Long variance) {
		this.variance = variance;
	}

	public BigDecimal getAcceptance() {
		return acceptance;
	}

	public void setAcceptance(BigDecimal acceptance) {
		this.acceptance = acceptance;
	}

}
