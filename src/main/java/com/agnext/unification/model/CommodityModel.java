package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class CommodityModel.
 */
@JsonIgnoreProperties
public class CommodityModel {

    @JsonProperty("commodity_id")
    private Long commodityId;

    @JsonProperty("commodity_code")
    private String commodityCode;

    @JsonProperty("commodity_name")
    private String commodityName;

    @JsonProperty("commodity_category_id")
    private Long commodityCategoryId;

    @JsonProperty("commodity_category_name")
    private String commodityCategoryName;

    @JsonProperty("count")
    private Integer count;

    public CommodityModel(Long commodityId, String commodityName) {
	super();
	this.commodityId = commodityId;
	this.commodityName = commodityName;
    }

    public CommodityModel() {
	// TODO Auto-generated constructor stub
    }

    /**
     * @return the commodityId
     */
    public Long getCommodityId() {
	return commodityId;
    }

    /**
     * @param commodityId
     *            the commodityId to set
     */
    public void setCommodityId(Long commodityId) {
	this.commodityId = commodityId;
    }

    /**
     * @return the commodityCode
     */
    public String getCommodityCode() {
	return commodityCode;
    }

    /**
     * @param commodityCode
     *            the commodityCode to set
     */
    public void setCommodityCode(String commodityCode) {
	this.commodityCode = commodityCode;
    }

    /**
     * @return the commodityName
     */
    public String getCommodityName() {
	return commodityName;
    }

    /**
     * @param commodityName
     *            the commodityName to set
     */
    public void setCommodityName(String commodityName) {
	this.commodityName = commodityName;
    }

    /**
     * @return the commodityCategoryId
     */
    public Long getCommodityCategoryId() {
	return commodityCategoryId;
    }

    /**
     * @param commodityCategoryId
     *            the commodityCategoryId to set
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
     * @param commodityCategoryName
     *            the commodityCategoryName to set
     */
    public void setCommodityCategoryName(String commodityCategoryName) {
	this.commodityCategoryName = commodityCategoryName;
    }

    public Integer getCount() {
	return count;
    }

    public void setCount(Integer count) {
	this.count = count;
    }
}
