package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class PackageCommoditiesPriceVO {
	@JsonProperty("id")
	private Long id;

	@JsonProperty("price")
	private Double price;

	@JsonProperty("package_name")
	private String packagesName;
	
	@JsonProperty("package_id")
	private Long packagesId;

	@JsonProperty("commodity_name")
	private String commodityName;
	
	@JsonProperty("commodity_id")
	private Long commodityId;

	@JsonProperty("created_on")
	private String createdOn;

	@JsonProperty("update_on")
	private String updatedOn;

	@JsonProperty("status")
	private Integer status;
	
	@JsonProperty("expired_on")
	private String expiredOn;
	
	@JsonProperty("total_scan")
	private Long totalScans;
	
	@JsonProperty("consumed_scan")
	private Long consumedScan;
	
	@JsonProperty("licence_no")
	private String licenceNo;
	
	@JsonProperty("category_id")
	private Long categoryId;

	@JsonProperty("category_name")
	private String categoryName;

	/**
	 * @return the licenceNo
	 */
	public String getLicenceNo() {
		return licenceNo;
	}



	/**
	 * @param licenceNo the licenceNo to set
	 */
	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}



	/**
	 * @return the expiredOn
	 */
	public String getExpiredOn() {
		return expiredOn;
	}



	/**
	 * @param expiredOn the expiredOn to set
	 */
	public void setExpiredOn(String expiredOn) {
		this.expiredOn = expiredOn;
	}



	/**
	 * @return the totalScans
	 */
	public Long getTotalScans() {
		return totalScans;
	}



	/**
	 * @param totalScans the totalScans to set
	 */
	public void setTotalScans(Long totalScans) {
		this.totalScans = totalScans;
	}



	/**
	 * @return the consumedScan
	 */
	public Long getConsumedScan() {
		return consumedScan;
	}



	/**
	 * @param consumedScan the consumedScan to set
	 */
	public void setConsumedScan(Long consumedScan) {
		this.consumedScan = consumedScan;
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}



	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}



	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}



	/**
	 * @return the packagesName
	 */
	public String getPackagesName() {
		return packagesName;
	}



	/**
	 * @param packagesName the packagesName to set
	 */
	public void setPackagesName(String packagesName) {
		this.packagesName = packagesName;
	}



	/**
	 * @return the packagesId
	 */
	public Long getPackagesId() {
		return packagesId;
	}



	/**
	 * @param packagesId the packagesId to set
	 */
	public void setPackagesId(Long packagesId) {
		this.packagesId = packagesId;
	}



	/**
	 * @return the commodityName
	 */
	public String getCommodityName() {
		return commodityName;
	}



	/**
	 * @param commodityName the commodityName to set
	 */
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}



	/**
	 * @return the commodityId
	 */
	public Long getCommodityId() {
		return commodityId;
	}



	/**
	 * @param commodityId the commodityId to set
	 */
	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}



	/**
	 * @return the createdOn
	 */
	public String getCreatedOn() {
		return createdOn;
	}



	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}



	/**
	 * @return the updatedOn
	 */
	public String getUpdatedOn() {
		return updatedOn;
	}



	/**
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}



	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}



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
	
	
	
}
