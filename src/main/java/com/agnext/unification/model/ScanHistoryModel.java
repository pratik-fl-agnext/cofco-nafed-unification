package com.agnext.unification.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
//@JsonInclude(Include.NON_EMPTY)
public class ScanHistoryModel {

    @JsonProperty("batch_id")
    private String batchId;

    @JsonProperty("commodity_id")
    private Long commodityId;

    @JsonProperty("date_done")
    private String date_done;

    @JsonProperty("device_type")
    private String deviceType;

    @JsonProperty("device_serial_no")
    private String deviceSerialNo;

    @JsonProperty("scan_id")
    private Long scanId;

    @JsonProperty("device_id")
    private String deviceId;

    @JsonProperty("device_type_id")
    private Long deviceTypeId;

    @JsonProperty("commodity_name")
    private String commodityName;

    @JsonProperty("quality_score")
    private BigDecimal qualityScore;

    @JsonProperty("total_count")
    private Integer totalCount;

    @JsonProperty("weight")
    private BigDecimal weight;

    @JsonProperty("approval")
    private Integer approval;

    @JsonProperty("approval_desc")
    private String approvalDesc;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("moisture")
    private Double moisture;

    @JsonProperty("temperature")
    private Double temperature;

    @JsonProperty("sample_id")
    private String sampleId;

    @JsonProperty("variety_id")
    private Long varietyId;

    @JsonProperty("variery_name")
    private String varietyName;

    @JsonProperty("truck_number")
    private String truckNumber;

    @JsonProperty("society_name")
    private String societyName;

    @JsonProperty("number_of_bags")
    private String numberOfBags;

    @JsonProperty("accepted_bags")
    private String acceptedBags;

    @JsonProperty("rejected_bags")
    private String rejectedBags;

    @JsonProperty("quantity")
    private BigDecimal quantity;

    @JsonProperty("analysis_results")
    private List<AnalysisResultVO> analysisResultList;
    
    @JsonProperty("is_image_exist")
    private Boolean isImageExist;

    @JsonProperty("imageDetails")
    private List<ImageModel> imageModels;

    public String getBatchId() {
	return batchId;
    }

    public void setBatchId(String batchId) {
	this.batchId = batchId;
    }

    public Long getCommodityId() {
	return commodityId;
    }

    public void setCommodityId(Long commodityId) {
	this.commodityId = commodityId;
    }

    public String getDate_done() {
	return date_done;
    }

    public void setDate_done(String date_done) {
	this.date_done = date_done;
    }

    public String getDeviceType() {
	return deviceType;
    }

    public void setDeviceType(String deviceType) {
	this.deviceType = deviceType;
    }

    public String getDeviceSerialNo() {
	return deviceSerialNo;
    }

    public void setDeviceSerialNo(String deviceSerialNo) {
	this.deviceSerialNo = deviceSerialNo;
    }

    public Long getScanId() {
	return scanId;
    }

    public void setScanId(Long scanId) {
	this.scanId = scanId;
    }

    public String getDeviceId() {
	return deviceId;
    }

    public void setDeviceId(String deviceId) {
	this.deviceId = deviceId;
    }

    public Long getDeviceTypeId() {
	return deviceTypeId;
    }

    public void setDeviceTypeId(Long deviceTypeId) {
	this.deviceTypeId = deviceTypeId;
    }

    public String getCommodityName() {
	return commodityName;
    }

    public void setCommodityName(String commodityName) {
	this.commodityName = commodityName;
    }

    public BigDecimal getQualityScore() {
	return qualityScore;
    }

    public void setQualityScore(BigDecimal qualityScore) {
	this.qualityScore = qualityScore;
    }

    public Integer getTotalCount() {
	return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
	this.totalCount = totalCount;
    }

    public BigDecimal getWeight() {
	return weight;
    }

    public void setWeight(BigDecimal weight) {
	this.weight = weight;
    }

    public Integer getApproval() {
	return approval;
    }

    public void setApproval(Integer approval) {
	this.approval = approval;
    }

    public String getApprovalDesc() {
	return approvalDesc;
    }

    public void setApprovalDesc(String approvalDesc) {
	this.approvalDesc = approvalDesc;
    }

    public String getUnit() {
	return unit;
    }

    public void setUnit(String unit) {
	this.unit = unit;
    }

    public String getCustomerName() {
	return customerName;
    }

    public void setCustomerName(String customerName) {
	this.customerName = customerName;
    }

    public Long getCustomerId() {
	return customerId;
    }

    public void setCustomerId(Long customerId) {
	this.customerId = customerId;
    }

    public Double getMoisture() {
	return moisture;
    }

    public void setMoisture(Double moisture) {
	this.moisture = moisture;
    }

    public Double getTemperature() {
	return temperature;
    }

    public void setTemperature(Double temperature) {
	this.temperature = temperature;
    }

    public String getSampleId() {
	return sampleId;
    }

    public void setSampleId(String sampleId) {
	this.sampleId = sampleId;
    }

    public Long getVarietyId() {
	return varietyId;
    }

    public void setVarietyId(Long varietyId) {
	this.varietyId = varietyId;
    }

    public String getVarietyName() {
	return varietyName;
    }

    public void setVarietyName(String varietyName) {
	this.varietyName = varietyName;
    }

    public List<AnalysisResultVO> getAnalysisResultList() {
	return analysisResultList;
    }

    public void setAnalysisResultList(List<AnalysisResultVO> analysisResultList) {
	this.analysisResultList = analysisResultList;
    }

    public String getTruckNumber() {
	return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
	this.truckNumber = truckNumber;
    }

    public String getSocietyName() {
	return societyName;
    }

    public void setSocietyName(String societyName) {
	this.societyName = societyName;
    }

    public String getNumberOfBags() {
	return numberOfBags;
    }

    public void setNumberOfBags(String numberOfBags) {
	this.numberOfBags = numberOfBags;
    }

    public String getAcceptedBags() {
	return acceptedBags;
    }

    public void setAcceptedBags(String acceptedBags) {
	this.acceptedBags = acceptedBags;
    }

    public String getRejectedBags() {
	return rejectedBags;
    }

    public void setRejectedBags(String rejectedBags) {
	this.rejectedBags = rejectedBags;
    }

    public BigDecimal getQuantity() {
	return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
	this.quantity = quantity;
    }

    @Override
    public String toString() {
	return "ScanHistoryModel [batchId=" + batchId + ", commodityId=" + commodityId + ", date_done=" + date_done
		+ ", deviceType=" + deviceType + ", deviceSerialNo=" + deviceSerialNo + ", scanId=" + scanId
		+ ", deviceId=" + deviceId + ", deviceTypeId=" + deviceTypeId + ", commodityName=" + commodityName
		+ ", qualityScore=" + qualityScore + ", totalCount=" + totalCount + ", weight=" + weight + ", approval="
		+ approval + ", approvalDesc=" + approvalDesc + ", unit=" + unit + ", customerName=" + customerName
		+ ", customerId=" + customerId + ", moisture=" + moisture + ", temperature=" + temperature
		+ ", sampleId=" + sampleId + ", varietyId=" + varietyId + ", varietyName=" + varietyName + "]";
    }

	public Boolean getIsImageExist() {
		return isImageExist;
	}

	public void setIsImageExist(Boolean isImageExist) {
		this.isImageExist = isImageExist;
	}

	public List<ImageModel> getImageModels() {
		return imageModels;
	}

	public void setImageModels(List<ImageModel> imageModels) {
		this.imageModels = imageModels;
	}

}
