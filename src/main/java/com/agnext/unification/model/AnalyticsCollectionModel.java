package com.agnext.unification.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class AnalyticsCollectionModel {
	
	@JsonProperty("weight")
	private BigDecimal weight;

	@JsonProperty("inst_center_id")
	private Long instCenterId;

	@JsonProperty("date_done")
	private Long dateDone;

	@JsonProperty("inst_center_collection")
	private BigDecimal instCenterCollection;

	@JsonProperty("region_collection")
	private BigDecimal regionCollection;

	@JsonProperty("region_date_done")
	private Long regionDateDone;

	@JsonProperty("center_date_done")
	private Long centerDateDone;

	@JsonProperty("inst_center_name")
	private String instCenterName;
	
	@JsonProperty("quantity_unit")
	private String quantityUnit;	

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public Long getInstCenterId() {
		return instCenterId;
	}

	public void setInstCenterId(Long instCenterId) {
		this.instCenterId = instCenterId;
	}

	public Long getDateDone() {
		return dateDone;
	}

	public void setDateDone(Long dateDone) {
		this.dateDone = dateDone;
	}

	public BigDecimal getInstCenterCollection() {
		return instCenterCollection;
	}

	public void setInstCenterCollection(BigDecimal instCenterCollection) {
		this.instCenterCollection = instCenterCollection;
	}

	public BigDecimal getRegionCollection() {
		return regionCollection;
	}

	public void setRegionCollection(BigDecimal regionCollection) {
		this.regionCollection = regionCollection;
	}

	public Long getRegionDateDone() {
		return regionDateDone;
	}

	public void setRegionDateDone(Long regionDateDone) {
		this.regionDateDone = regionDateDone;
	}

	public Long getCenterDateDone() {
		return centerDateDone;
	}

	public void setCenterDateDone(Long centerDateDone) {
		this.centerDateDone = centerDateDone;
	}

	public String getInstCenterName() {
		return instCenterName;
	}

	public void setInstCenterName(String instCenterName) {
		this.instCenterName = instCenterName;
	}

	@Override
	public String toString() {
		return "AnalyticsCollectionModel [weight=" + weight + ", instCenterId=" + instCenterId + ", dateDone="
				+ dateDone + ", instCenterCollection=" + instCenterCollection + ", regionCollection=" + regionCollection
				+ ", regionDateDone=" + regionDateDone + ", centerDateDone=" + centerDateDone + ", instCenterName="
				+ instCenterName + "]";
	}

	public String getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
	
}
