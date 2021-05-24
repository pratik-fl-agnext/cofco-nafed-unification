package com.agnext.unification.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class ClientDetailModel {
	
	@JsonProperty("farmer_id")
	private Long farmerId;
	
	@JsonProperty("farmer_email")
	private String farmerEmail;

	@JsonProperty("phone_number")
	private String phoneNumber;
	
	@JsonProperty("area")
	private BigDecimal area;
	
	@JsonProperty("inst_center_id")
	private Long instCenterId;

	@JsonProperty("avg_colection")
	private BigDecimal avgCollection;
	

	@JsonProperty("avg_quality")
	private BigDecimal avgQuality;

	

	@JsonProperty("farmer_name")
	private String farmerName;

	@JsonProperty("vendor_name")
	private String vendorName;
	
	@JsonProperty("account_number")
	private String accountNumber;
	
	
	@JsonProperty("vendor_email")
	private String vendorEmail;

	@JsonProperty("contact_number")
	private String contactNumber;

	@JsonProperty("vendor_address")
	private String location;
	
	@JsonProperty("unit")
	private String unit;
	
	@JsonProperty("inst_center_name")
	private String instCenterName;
	
	public BigDecimal getAvgQuality() {
		return avgQuality;
	}

	public void setAvgQuality(BigDecimal avgQuality) {
		this.avgQuality = avgQuality;
	}

	public Long getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(Long farmerId) {
		this.farmerId = farmerId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public  BigDecimal getArea() {
		return area;
	}

	public void setArea( BigDecimal area) {
		this.area = area;
	}

	public Long getInstCenterId() {
		return instCenterId;
	}

	public void setInstCenterId(Long instCenterId) {
		this.instCenterId = instCenterId;
	}

	public BigDecimal getAvgCollection() {
		return avgCollection;
	}

	public void setAvgCollection(BigDecimal avgCollection) {
		this.avgCollection = avgCollection;
	}

	public String getFarmerName() {
		return farmerName;
	}

	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}

	/**
	 * @return the farmerEmail
	 */
	public String getFarmerEmail() {
		return farmerEmail;
	}

	/**
	 * @param farmerEmail the farmerEmail to set
	 */
	public void setFarmerEmail(String farmerEmail) {
		this.farmerEmail = farmerEmail;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getInstCenterName() {
		return instCenterName;
	}

	public void setInstCenterName(String instCenterName) {
		this.instCenterName = instCenterName;
	}

}
