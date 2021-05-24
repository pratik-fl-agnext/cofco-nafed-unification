package com.agnext.unification.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Value Object class for {@link PhysicalScan}.<br>
 * Used to transfer objects
 * 
 * @author VISHAL B.
 * @since 1.0
 */
@JsonInclude(Include.NON_NULL)
public class PhysicalScanModel {

    private Long id;

    @JsonProperty("bar_code")
    private String barCode;

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("commodity_name")
    private String commodityName;

    @JsonProperty("reference_id")
    private String referenceId;

    @JsonProperty("final_status")
    private String finalStatus;

    private BigDecimal weight;

    private BigDecimal sampleWeight;

    private String sampleWeightUnit;

    @JsonProperty("created_on")
    private Long createdOn;

    private PhysicalScanResultVO scanResults;

    @JsonProperty("total_count")
    private Long totalCount;

    @JsonProperty("scan_result")
    private PhysicalScanResultVO scanResult;

    @JsonProperty("analysis_result")
    private List<PhysicalScanResultVO> analysisResults;

    @JsonProperty("user_name")
    private String username;

    @JsonProperty("batch_id")
    private String batchId;

    @JsonProperty("analysis_result_scan")
    private List<Analytics> sAnalysisResults;

    @JsonProperty("device_serial_number")
    private String deviceSerialNumber;

    @JsonProperty("address")
    private String address;

    @JsonProperty("truck_number")
    private String truckNumber;

    @JsonProperty("device_type")
    private String deviceType;

    @JsonProperty("sample_id")
    private String sampleId;

    @JsonProperty("variety")
    private String variety;
    
    @JsonProperty("commodity_id")
    private Long commodityId;
    
    @JsonProperty("grade")
    private String grade;

    public List<Analytics> getsAnalysisResults() {
	return sAnalysisResults;
    }

    public void setsAnalysisResults(List<Analytics> sAnalysisResults) {
	this.sAnalysisResults = sAnalysisResults;
    }

    /**
     * @return the scanResult
     */
    public PhysicalScanResultVO getScanResult() {
	return scanResult;
    }

    /**
     * @param scanResult
     *            the scanResult to set
     */
    public void setScanResult(PhysicalScanResultVO scanResult) {
	this.scanResult = scanResult;
    }

    public List<PhysicalScanResultVO> getAnalysisResults() {
	return analysisResults;
    }

    public void setAnalysisResults(List<PhysicalScanResultVO> analysisResults) {
	this.analysisResults = analysisResults;
    }

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * @return the barCode
     */
    public String getBarCode() {
	return barCode;
    }

    /**
     * @param barCode
     *            the barCode to set
     */
    public void setBarCode(String barCode) {
	this.barCode = barCode;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
	return fileName;
    }

    /**
     * @param fileName
     *            the fileName to set
     */
    public void setFileName(String fileName) {
	this.fileName = fileName;
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
     * @return the referenceId
     */
    public String getReferenceId() {
	return referenceId;
    }

    /**
     * @param referenceId
     *            the referenceId to set
     */
    public void setReferenceId(String referenceId) {
	this.referenceId = referenceId;
    }

    /**
     * @return the finalStatus
     */
    public String getFinalStatus() {
	return finalStatus;
    }

    /**
     * @param finalStatus
     *            the finalStatus to set
     */
    public void setFinalStatus(String finalStatus) {
	this.finalStatus = finalStatus;
    }

    /**
     * @return the weight
     */
    public BigDecimal getWeight() {
	return weight;
    }

    /**
     * @param weight
     *            the weight to set
     */
    public void setWeight(BigDecimal weight) {
	this.weight = weight;
    }

    /**
     * @return the scanResults
     */
    public PhysicalScanResultVO getScanResults() {
	return scanResults;
    }

    /**
     * @param scanResults
     *            the scanResults to set
     */
    public void setScanResults(PhysicalScanResultVO scanResults) {
	this.scanResults = scanResults;
    }

    /**
     * @return the createdOn
     */
    public Long getCreatedOn() {
	return createdOn;
    }

    /**
     * @param createdOn
     *            the createdOn to set
     */
    public void setCreatedOn(Long createdOn) {
	this.createdOn = createdOn;
    }

    /**
     * @return the totalCount
     */
    public Long getTotalCount() {
	return totalCount;
    }

    /**
     * @param totalCount
     *            the totalCount to set
     */
    public void setTotalCount(Long totalCount) {
	this.totalCount = totalCount;
    }

    /**
     * @return the username
     */
    public String getUsername() {
	return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
	this.username = username;
    }

    public String getBatchId() {
	return batchId;
    }

    public void setBatchId(String batchId) {
	this.batchId = batchId;
    }

    public String getDeviceSerialNumber() {
	return deviceSerialNumber;
    }

    public void setDeviceSerialNumber(String deviceSerialNumber) {
	this.deviceSerialNumber = deviceSerialNumber;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getTruckNumber() {
	return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
	this.truckNumber = truckNumber;
    }

    public String getDeviceType() {
	return deviceType;
    }

    public void setDeviceType(String deviceType) {
	this.deviceType = deviceType;
    }

    public String getSampleId() {
	return sampleId;
    }

    public void setSampleId(String sampleId) {
	this.sampleId = sampleId;
    }

    public String getVariety() {
	return variety;
    }

    public void setVariety(String variety) {
	this.variety = variety;
    }

    public BigDecimal getSampleWeight() {
	return sampleWeight;
    }

    public void setSampleWeight(BigDecimal sampleWeight) {
	this.sampleWeight = sampleWeight;
    }

    public String getSampleWeightUnit() {
	return sampleWeightUnit;
    }

    public void setSampleWeightUnit(String sampleWeightUnit) {
	this.sampleWeightUnit = sampleWeightUnit;
    }

	public Long getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}

	public String getGrade() {
	    return grade;
	}

	public void setGrade(String grade) {
	    this.grade = grade;
	}
	
	

}
