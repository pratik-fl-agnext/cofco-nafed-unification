package com.agnext.unification.model;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class NanoScanModel {

	@JsonProperty("nano_scan_id")
	private UUID nanoId;

	@JsonProperty("protein")
	private BigDecimal protein;

	@JsonProperty("moisture")
	private BigDecimal moisture;

	@JsonProperty("curcumin")
	private BigDecimal curcumin;

	@JsonProperty("oil")
	private BigDecimal oil;

	@JsonProperty("fat")
	private BigDecimal fat;

	@JsonProperty("snf")
	private BigDecimal snf;

	@JsonProperty("urea")
	private BigDecimal urea;

	@JsonProperty("detergent")
	private Boolean detergent;

	@JsonProperty("glabridin")
	private BigDecimal glabridin;

	@JsonProperty("palmOil")
	private BigDecimal palmOil;

	@JsonProperty("scan_id")
	private String scanId;

	@JsonProperty("commodity_id")
	private Long commodityId;

	@JsonProperty("scan_by_user_code")
	private String scanByUserCode;

	@JsonProperty("location_code")
	private String locationCode;

	@JsonProperty("device_serial_no")
	private String deviceSerialNo;

	@JsonProperty("vendor_code")
	private String vendorCode;

	@JsonProperty("scan_type_id")
	private Long scanTypeId;

	@JsonProperty("client_code")
	private String clientCode;

	@JsonProperty("file_path")
	private String filePath;

	@JsonProperty("farmer_code")
	private String farmerCode;

	@JsonProperty("created_on")
	private Long createdOn;

	@JsonProperty("variety_id")
	private Long varietyId;

	@JsonProperty("quantity")
	private BigDecimal quantity;

	@JsonProperty("quantity_unit")
	private String quantityUnit;

	@JsonProperty("batch_id")
	private String batchId;

	@JsonProperty("lot_id")
	private String lotId;

	@JsonProperty("sample_id")
	private String sampleId;

	@JsonProperty("location")
	private String location;
	
	@JsonProperty("avg_quality")
	private BigDecimal avgQuality;
	
	@JsonProperty("min_quality")
	private BigDecimal minQuality;
	
	@JsonProperty("max_quality")
	private BigDecimal maxQuality;
	
	@JsonProperty("quality_grade")
	private String qualityGrade;

	@JsonProperty("scan_count")
	private Long scanCount;
	
	
	
	public String getQualityGrade() {
		return qualityGrade;
	}

	public void setQualityGrade(String qualityGrade) {
		this.qualityGrade = qualityGrade;
	}

	public Long getScanCount() {
		return scanCount;
	}

	public void setScanCount(Long scanCount) {
		this.scanCount = scanCount;
	}

	public BigDecimal getAvgQuality() {
		return avgQuality;
	}

	public void setAvgQuality(BigDecimal avgQuality) {
		this.avgQuality = avgQuality;
	}

	public BigDecimal getMinQuality() {
		return minQuality;
	}

	public void setMinQuality(BigDecimal minQuality) {
		this.minQuality = minQuality;
	}

	public BigDecimal getMaxQuality() {
		return maxQuality;
	}

	public void setMaxQuality(BigDecimal maxQuality) {
		this.maxQuality = maxQuality;
	}

	public UUID getNanoId() {
		return nanoId;
	}

	public void setNanoId(UUID nanoId) {
		this.nanoId = nanoId;
	}

	public BigDecimal getProtein() {
		return protein;
	}

	public void setProtein(BigDecimal protein) {
		this.protein = protein;
	}

	public BigDecimal getMoisture() {
		return moisture;
	}

	public void setMoisture(BigDecimal moisture) {
		this.moisture = moisture;
	}

	public BigDecimal getCurcumin() {
		return curcumin;
	}

	public void setCurcumin(BigDecimal curcumin) {
		this.curcumin = curcumin;
	}

	public BigDecimal getOil() {
		return oil;
	}

	public void setOil(BigDecimal oil) {
		this.oil = oil;
	}

	public BigDecimal getFat() {
		return fat;
	}

	public void setFat(BigDecimal fat) {
		this.fat = fat;
	}

	public BigDecimal getSnf() {
		return snf;
	}

	public void setSnf(BigDecimal snf) {
		this.snf = snf;
	}

	public BigDecimal getUrea() {
		return urea;
	}

	public void setUrea(BigDecimal urea) {
		this.urea = urea;
	}

	public Boolean getDetergent() {
		return detergent;
	}

	public void setDetergent(Boolean detergent) {
		this.detergent = detergent;
	}

	public BigDecimal getGlabridin() {
		return glabridin;
	}

	public void setGlabridin(BigDecimal glabridin) {
		this.glabridin = glabridin;
	}

	public BigDecimal getPalmOil() {
		return palmOil;
	}

	public void setPalmOil(BigDecimal palmOil) {
		this.palmOil = palmOil;
	}

	public String getScanId() {
		return scanId;
	}

	public void setScanId(String scanId) {
		this.scanId = scanId;
	}

	public Long getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}

	public String getScanByUserCode() {
		return scanByUserCode;
	}

	public void setScanByUserCode(String scanByUserCode) {
		this.scanByUserCode = scanByUserCode;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getDeviceSerialNo() {
		return deviceSerialNo;
	}

	public void setDeviceSerialNo(String deviceSerialNo) {
		this.deviceSerialNo = deviceSerialNo;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public Long getScanTypeId() {
		return scanTypeId;
	}

	public void setScanTypeId(Long scanTypeId) {
		this.scanTypeId = scanTypeId;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFarmerCode() {
		return farmerCode;
	}

	public void setFarmerCode(String farmerCode) {
		this.farmerCode = farmerCode;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public Long getVarietyId() {
		return varietyId;
	}

	public void setVarietyId(Long varietyId) {
		this.varietyId = varietyId;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getLotId() {
		return lotId;
	}

	public void setLotId(String lotId) {
		this.lotId = lotId;
	}

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "NanoScanModel [nanoId=" + nanoId + ", protein=" + protein + ", moisture=" + moisture + ", curcumin="
				+ curcumin + ", oil=" + oil + ", fat=" + fat + ", snf=" + snf + ", urea=" + urea + ", detergent="
				+ detergent + ", glabridin=" + glabridin + ", palmOil=" + palmOil + ", scanId=" + scanId
				+ ", commodityId=" + commodityId + ", scanByUserCode=" + scanByUserCode + ", locationCode="
				+ locationCode + ", deviceSerialNo=" + deviceSerialNo + ", vendorCode=" + vendorCode + ", scanTypeId="
				+ scanTypeId + ", clientCode=" + clientCode + ", filePath=" + filePath + ", farmerCode=" + farmerCode
				+ ", createdOn=" + createdOn + ", varietyId=" + varietyId + ", quantity=" + quantity + ", quantityUnit="
				+ quantityUnit + ", batchId=" + batchId + ", lotId=" + lotId + ", sampleId=" + sampleId + ", location="
				+ location + "]";
	}

}
