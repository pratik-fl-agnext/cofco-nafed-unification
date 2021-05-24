/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class CustomerAddressModel.
 */
@JsonIgnoreProperties
public class CommodityAnalyticModel {

	@JsonProperty("commodity_analytic_id")
	private Long commodityAnalyticId;
	
	@JsonProperty("analytic_code")
	private String analyticCode;
	
	@JsonProperty("analytic_name")
	private String analyticName;
	
	@JsonProperty("commodity_variety_id")
	private Long commodityVarietyId;
	
	@JsonProperty("commodity_variety_name")
	private String commodityVarietyName;

	/**
	 * @return the commodityAnalyticId
	 */
	public Long getCommodityAnalyticId() {
		return commodityAnalyticId;
	}

	/**
	 * @param commodityAnalyticId the commodityAnalyticId to set
	 */
	public void setCommodityAnalyticId(Long commodityAnalyticId) {
		this.commodityAnalyticId = commodityAnalyticId;
	}

	/**
	 * @return the analyticCode
	 */
	public String getAnalyticCode() {
		return analyticCode;
	}

	/**
	 * @param analyticCode the analyticCode to set
	 */
	public void setAnalyticCode(String analyticCode) {
		this.analyticCode = analyticCode;
	}

	/**
	 * @return the analyticName
	 */
	public String getAnalyticName() {
		return analyticName;
	}

	/**
	 * @param analyticName the analyticName to set
	 */
	public void setAnalyticName(String analyticName) {
		this.analyticName = analyticName;
	}

	/**
	 * @return the commodityVarietyId
	 */
	public Long getCommodityVarietyId() {
		return commodityVarietyId;
	}

	/**
	 * @param commodityVarietyId the commodityVarietyId to set
	 */
	public void setCommodityVarietyId(Long commodityVarietyId) {
		this.commodityVarietyId = commodityVarietyId;
	}

	/**
	 * @return the commodityVarietyName
	 */
	public String getCommodityVarietyName() {
		return commodityVarietyName;
	}

	/**
	 * @param commodityVarietyName the commodityVarietyName to set
	 */
	public void setCommodityVarietyName(String commodityVarietyName) {
		this.commodityVarietyName = commodityVarietyName;
	}
	 
}
