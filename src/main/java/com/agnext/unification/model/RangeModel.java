package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class RangeModel {

	@JsonProperty("id")
	private Long id;
   
    @JsonProperty("analytic_id")
	private Long analyticId;
    
    @JsonProperty("analytic_name")
	private String analyticName;
	
	@JsonProperty("commodity_id")
	private Long commodityId;
	
	@JsonProperty("commodity_name")
    private String commodityName;
	
    @JsonProperty("client_id")
	private Long clientId;
	
	@JsonProperty("client_name")
	private String clientName;
	
	@JsonProperty("status_id")
	private Long status;
	
	@JsonProperty("status_desc")
	private String StatusDesc;
	
	@JsonProperty("location_id")
	private Long locationId;
	
	@JsonProperty("location_name")
	private String locationName;
	
	@JsonProperty("warehouse_name")
	private String warehouseName;
	
	@JsonProperty("created_on")
	private Long createdOn;
	
	@JsonProperty("updated_on")
	private Long updatedOn;
	
	@JsonProperty("threshold_value")
	private String maxRange;
	
	@JsonProperty("min_range")
	private String minRange;

	public Long getId() {
		return id;
	}

	public Long getAnalyticId() {
		return analyticId;
	}

	public String getAnalyticName() {
		return analyticName;
	}

	public Long getCommodityId() {
		return commodityId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public Long getClientId() {
		return clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public Long getStatus() {
		return status;
	}

	public String getStatusDesc() {
		return StatusDesc;
	}

	public Long getLocationId() {
		return locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	

	public Long getCreatedOn() {
		return createdOn;
	}

	public Long getUpdatedOn() {
		return updatedOn;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAnalyticId(Long analyticId) {
		this.analyticId = analyticId;
	}

	public void setAnalyticName(String analyticName) {
		this.analyticName = analyticName;
	}

	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public void setStatusDesc(String statusDesc) {
		StatusDesc = statusDesc;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "AnalyticsRangeModel [id=" + id + ", analyticId=" + analyticId + ", analyticName=" + analyticName
				+ ", commodityId=" + commodityId + ", commodityName=" + commodityName + ", clientId=" + clientId
				+ ", clientName=" + clientName + ", status=" + status + ", StatusDesc=" + StatusDesc + ", locationId="
				+ locationId + ", locationName=" + locationName + ", warehouse_name=" + warehouseName + ", createdOn="
				+ createdOn + ", updatedOn=" + updatedOn + "]";
	}

	public String getMaxRange() {
		return maxRange;
	}

	public String getMinRange() {
		return minRange;
	}

	public void setMaxRange(String maxRange) {
		this.maxRange = maxRange;
	}

	public void setMinRange(String minRange) {
		this.minRange = minRange;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	

}
