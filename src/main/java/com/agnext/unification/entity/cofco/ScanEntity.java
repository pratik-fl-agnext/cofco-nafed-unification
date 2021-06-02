package com.agnext.unification.entity.cofco;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.agnext.unification.entity.BaseEntityClass;

@Entity
@Table(name = "scm_scans")
public class ScanEntity extends BaseEntityClass {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "batch_id")
    private String batchId;

    @Column(name = "commodity_id")
    private Long commodityId;

    //	@Column(name = "cc_id")
    //	private Long collectionCenterId;

    @Column(name = "created_on")
    private Long createdOn;

    @Column(name = "farmer_code")
    private String farmerCode;

    @Column(name = "location")
    private String location;

    @Column(name = "lot_id")
    private String lotId;

    @Column(name = "scan_by_user_code")
    private String scanByUserCode;

    @Column(name = "location_code")
    private String locationCode;

    @Column(name = "device_code")
    private String deviceCode;

    @Column(name = "vendor_code")
    private String vendorCode;

    @Column(name = "scan_type")
    private String scanType;

    @Column(name = "client_code")
    private String clientCode;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "quantity_unit")
    private String quantityUnit;

    @Column(name = "variety_id")
    private Long varietyId;

    @Column(name = "refernce_id")
    private String refernceId;

    @Column(name = "installation_center_id")
    private Long installatonCenterId;

    @Column(name = "company_id")
    private Long customerId;

    @Column(name = "date_done")
    private Long dateDone;

    @Column(name = "device_serial_no")
    private String deviceSerialNo;

    @Column(name = "farmer_id")
    private Long farmerId;

    @Column(name = "quality_score")
    private BigDecimal qualityScore;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "weight_amt")
    private BigDecimal weightAmt;

    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "inst_center_type_Id")
    private Long instCenterTypeId;

    @Column(name = "commodity_variety_id")
    private Long commodityVarietyId;

    @Column(name = "commodity_category_id")
    private Long commodityCategoryId;

    @Column(name = "commodity_name")
    private String commodityName;

    @Column(name = "grade")
    private String grade;

    @Column(name = "device_type")
    private String deviceType;

    @Column(name = "device_type_id")
    private Long deviceTypeId;

    @Column(name = "plot_id")
    private Long plotId;

    @Column(name = "accepted")
    private Integer accepted;

    @Column(name = "variance")
    private Long variance;

    @Column(name = "created_date")
    private BigDecimal createdDate;

    @Column(name = "created_on_date")
    private Date createdOnDate;

    @Column(name = "area_covered")
    private BigDecimal areaCovered;

    @Column(name = "sample_id")
    private String sampleId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "section_id")
    private Long sectionId;

    @Column(name = "bar_code")
    private String barCode;

    @Column(name = "image_unique_id")
    private String imageUniqueId;

    @Column(name = "approval")
    private Integer approval;

    @Column(name = "density")
    private String density;

    @Column(name = "truck_number")
    private String truckNumber;

    @Column(name = "slip_no")
    private String slipNo;

    @Column(name = "gate_pass")
    private String gatePass;

    @Column(name = "cad_no")
    private String cadNo;

    @Column(name = "bag")
    private String bag;

    @Column(name = "truck_gross_weight")
    private String truckGrossWeight;

    @Column(name = "truck_tare_weight")
    private String truckTareWeight;

    @Column(name = "truck_net_weight")
    private String truckNetWeight;

    @Column(name = "stack_number")
    private String stackNumber;

    @Column(name = "chamber_number")
    private String chamberNumber;

    @Column(name = "avg_weight_per_bag")
    private String avgWeightPerBag;

    @Column(name = "packing_size")
    private String packingSize;

    @Column(name = "state")
    private Integer state;

    @Column(name = "weighbridge_name")
    private String weighbridgeName;

    @Column(name = "crop_year")
    private String cropYear;

    @Column(name = "grn_number")
    private String GRNNumber;

    @Column(name = "operator_id")
    private Long operatorId;

    @Column(name = "message")
    private String message;

    @Column(name = "status_changed_on")
    private LocalDateTime statusChangedOn;

    @Column(name = "society_name")
    private String societyName;

    @Column(name = "sample_weight")
    private BigDecimal sampleWeight;

    @Column(name = "sample_weight_unit")
    private String sampleWeightUnit;

    @Column(name = "variety")
    private String varietyName;

    @Column(name = "remark")
    private String remark;

    @Column(name = "state_admin")
    private Long stateAdmin;

    @Column(name = "accepted_bags")
    private String acceptedBags;

    @Column(name = "rejected_bags")
    private String rejectedBags;

    @Column(name = "godown_number")
    private String godownNumber;

    @Column(name = "weighbridge_slip_no")
    private String weighbridgeSlipNo;

    @Column(name = "cis_cdf_no")
    private String cisCdfNo;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "supplier_address")
    private String supplierAddress;

    @Column(name = "ai_results")
    private String aiResults;

    /**
     * @return the statusChangedOn
     */
    public LocalDateTime getStatusChangedOn() {
	return statusChangedOn;
    }

    /**
     * @param statusChangedOn
     *            the statusChangedOn to set
     */
    public void setStatusChangedOn(LocalDateTime statusChangedOn) {
	this.statusChangedOn = statusChangedOn;
    }

    /**
     * @return the message
     */
    public String getMessage() {
	return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
	this.message = message;
    }

    /**
     * @return the cropYear
     */
    public String getCropYear() {
	return cropYear;
    }

    /**
     * @param cropYear
     *            the cropYear to set
     */
    public void setCropYear(String cropYear) {
	this.cropYear = cropYear;
    }

    /**
     * @return the gRNNumber
     */
    public String getGRNNumber() {
	return GRNNumber;
    }

    /**
     * @param gRNNumber
     *            the gRNNumber to set
     */
    public void setGRNNumber(String gRNNumber) {
	GRNNumber = gRNNumber;
    }

    /**
     * @return the state
     */
    public Integer getState() {
	return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(Integer state) {
	this.state = state;
    }

    /**
     * @return the weighbridgeName
     */
    public String getWeighbridgeName() {
	return weighbridgeName;
    }

    /**
     * @param weighbridgeName
     *            the weighbridgeName to set
     */
    public void setWeighbridgeName(String weighbridgeName) {
	this.weighbridgeName = weighbridgeName;
    }

    /**
     * @return the truckNumber
     */
    public String getTruckNumber() {
	return truckNumber;
    }

    /**
     * @param truckNumber
     *            the truckNumber to set
     */
    public void setTruckNumber(String truckNumber) {
	this.truckNumber = truckNumber;
    }

    /**
     * @return the slipNo
     */
    public String getSlipNo() {
	return slipNo;
    }

    /**
     * @param slipNo
     *            the slipNo to set
     */
    public void setSlipNo(String slipNo) {
	this.slipNo = slipNo;
    }

    /**
     * @return the gatePass
     */
    public String getGatePass() {
	return gatePass;
    }

    /**
     * @param gatePass
     *            the gatePass to set
     */
    public void setGatePass(String gatePass) {
	this.gatePass = gatePass;
    }

    /**
     * @return the cadNo
     */
    public String getCadNo() {
	return cadNo;
    }

    /**
     * @param cadNo
     *            the cadNo to set
     */
    public void setCadNo(String cadNo) {
	this.cadNo = cadNo;
    }

    /**
     * @return the bag
     */
    public String getBag() {
	return bag;
    }

    /**
     * @param bag
     *            the bag to set
     */
    public void setBag(String bag) {
	this.bag = bag;
    }

    /**
     * @return the truckGrossWeight
     */
    public String getTruckGrossWeight() {
	return truckGrossWeight;
    }

    /**
     * @param truckGrossWeight
     *            the truckGrossWeight to set
     */
    public void setTruckGrossWeight(String truckGrossWeight) {
	this.truckGrossWeight = truckGrossWeight;
    }

    /**
     * @return the truckTareWeight
     */
    public String getTruckTareWeight() {
	return truckTareWeight;
    }

    /**
     * @param truckTareWeight
     *            the truckTareWeight to set
     */
    public void setTruckTareWeight(String truckTareWeight) {
	this.truckTareWeight = truckTareWeight;
    }

    /**
     * @return the truckNetWeight
     */
    public String getTruckNetWeight() {
	return truckNetWeight;
    }

    /**
     * @param truckNetWeight
     *            the truckNetWeight to set
     */
    public void setTruckNetWeight(String truckNetWeight) {
	this.truckNetWeight = truckNetWeight;
    }

    /**
     * @return the stackNumber
     */
    public String getStackNumber() {
	return stackNumber;
    }

    /**
     * @param stackNumber
     *            the stackNumber to set
     */
    public void setStackNumber(String stackNumber) {
	this.stackNumber = stackNumber;
    }

    /**
     * @return the chamberNumber
     */
    public String getChamberNumber() {
	return chamberNumber;
    }

    /**
     * @param chamberNumber
     *            the chamberNumber to set
     */
    public void setChamberNumber(String chamberNumber) {
	this.chamberNumber = chamberNumber;
    }

    /**
     * @return the avgWeightPerBag
     */
    public String getAvgWeightPerBag() {
	return avgWeightPerBag;
    }

    /**
     * @param avgWeightPerBag
     *            the avgWeightPerBag to set
     */
    public void setAvgWeightPerBag(String avgWeightPerBag) {
	this.avgWeightPerBag = avgWeightPerBag;
    }

    /**
     * @return the packingSize
     */
    public String getPackingSize() {
	return packingSize;
    }

    /**
     * @param packingSize
     *            the packingSize to set
     */
    public void setPackingSize(String packingSize) {
	this.packingSize = packingSize;
    }

    /**
     * @return the approval
     */
    public Integer getApproval() {
	return approval;
    }

    /**
     * @param approval
     *            the approval to set
     */
    public void setApproval(Integer approval) {
	this.approval = approval;
    }

    public String getImageUniqueId() {
	return imageUniqueId;
    }

    public void setImageUniqueId(String imageUniqueId) {
	this.imageUniqueId = imageUniqueId;
    }

    public String getBarCode() {
	return barCode;
    }

    public void setBarCode(String barCode) {
	this.barCode = barCode;
    }

    @OneToMany(mappedBy = "scanEntity", cascade = CascadeType.ALL)
    private List<ScanResultEntity> results;

    public BigDecimal getCreatedDate() {
	return createdDate;
    }

    public void setCreatedDate(BigDecimal createdDate) {
	this.createdDate = createdDate;
    }

    public String getSampleId() {
	return sampleId;
    }

    public void setSampleId(String sampleId) {
	this.sampleId = sampleId;
    }

    /**
     * @return the results
     */
    public List<ScanResultEntity> getResults() {
	return results;
    }

    /**
     * @param results
     *            the results to set
     */
    public void setResults(List<ScanResultEntity> results) {
	this.results = results;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

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

    public Long getCreatedOn() {
	return createdOn;
    }

    public void setCreatedOn(Long createdOn) {
	this.createdOn = createdOn;
    }

    public String getFarmerCode() {
	return farmerCode;
    }

    public void setFarmerCode(String farmerCode) {
	this.farmerCode = farmerCode;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public String getLotId() {
	return lotId;
    }

    public void setLotId(String lotId) {
	this.lotId = lotId;
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

    public String getDeviceCode() {
	return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
	this.deviceCode = deviceCode;
    }

    public String getVendorCode() {
	return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
	this.vendorCode = vendorCode;
    }

    public String getScanType() {
	return scanType;
    }

    public void setScanType(String scanType) {
	this.scanType = scanType;
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

    public Long getVarietyId() {
	return varietyId;
    }

    public void setVarietyId(Long varietyId) {
	this.varietyId = varietyId;
    }

    public Long getInstallatonCenterId() {
	return installatonCenterId;
    }

    public void setInstallatonCenterId(Long installatonCenterId) {
	this.installatonCenterId = installatonCenterId;
    }

    public Long getCustomerId() {
	return customerId;
    }

    public void setCustomerId(Long customerId) {
	this.customerId = customerId;
    }

    public Long getDateDone() {
	return dateDone;
    }

    public void setDateDone(Long dateDone) {
	this.dateDone = dateDone;
    }

    public String getDeviceSerialNo() {
	return deviceSerialNo;
    }

    public void setDeviceSerialNo(String deviceSerialNo) {
	this.deviceSerialNo = deviceSerialNo;
    }

    public Long getFarmerId() {
	return farmerId;
    }

    public void setFarmerId(Long farmerId) {
	this.farmerId = farmerId;
    }

    public BigDecimal getQualityScore() {
	return qualityScore;
    }

    public void setQualityScore(BigDecimal qualityScore) {
	this.qualityScore = qualityScore;
    }

    public Long getUserId() {
	return userId;
    }

    public void setUserId(Long userId) {
	this.userId = userId;
    }

    public Long getCreatedBy() {
	return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
	this.createdBy = createdBy;
    }

    public String getFileName() {
	return fileName;
    }

    public void setFileName(String fileName) {
	this.fileName = fileName;
    }

    public Long getVendorId() {
	return vendorId;
    }

    public void setVendorId(Long vendorId) {
	this.vendorId = vendorId;
    }

    public BigDecimal getWeight() {
	return weight;
    }

    public void setWeight(BigDecimal weight) {
	this.weight = weight;
    }

    public BigDecimal getAmount() {
	return amount;
    }

    public void setAmount(BigDecimal amount) {
	this.amount = amount;
    }

    public BigDecimal getWeightAmt() {
	return weightAmt;
    }

    public void setWeightAmt(BigDecimal weightAmt) {
	this.weightAmt = weightAmt;
    }

    public Long getRegionId() {
	return regionId;
    }

    public void setRegionId(Long regionId) {
	this.regionId = regionId;
    }

    public Long getInstCenterTypeId() {
	return instCenterTypeId;
    }

    public void setInstCenterTypeId(Long instCenterTypeId) {
	this.instCenterTypeId = instCenterTypeId;
    }

    public Long getCommodityVarietyId() {
	return commodityVarietyId;
    }

    public void setCommodityVarietyId(Long commodityVarietyId) {
	this.commodityVarietyId = commodityVarietyId;
    }

    public Long getCommodityCategoryId() {
	return commodityCategoryId;
    }

    public void setCommodityCategoryId(Long commodityCategoryId) {
	this.commodityCategoryId = commodityCategoryId;
    }

    public String getCommodityName() {
	return commodityName;
    }

    public void setCommodityName(String commodityName) {
	this.commodityName = commodityName;
    }

    public String getGrade() {
	return grade;
    }

    public void setGrade(String grade) {
	this.grade = grade;
    }

    public String getDeviceType() {
	return deviceType;
    }

    public void setDeviceType(String deviceType) {
	this.deviceType = deviceType;
    }

    public Long getDeviceTypeId() {
	return deviceTypeId;
    }

    public void setDeviceTypeId(Long deviceTypeId) {
	this.deviceTypeId = deviceTypeId;
    }

    public Long getPlotId() {
	return plotId;
    }

    public void setPlotId(Long plotId) {
	this.plotId = plotId;
    }

    public Integer getAccepted() {
	return accepted;
    }

    public void setAccepted(Integer accepted) {
	this.accepted = accepted;
    }

    public Long getVariance() {
	return variance;
    }

    public void setVariance(Long variance) {
	this.variance = variance;
    }

    public void setCreatedOnDate(Date createdOnDate) {
	this.createdOnDate = createdOnDate;
    }

    @Override
    public String toString() {
	return "ScanEntity [id=" + id + ", batchId=" + batchId + ", commodityId=" + commodityId + ", createdOn="
		+ createdOn + ", farmerCode=" + farmerCode + ", location=" + location + ", lotId=" + lotId
		+ ", scanByUserCode=" + scanByUserCode + ", locationCode=" + locationCode + ", deviceCode=" + deviceCode
		+ ", vendorCode=" + vendorCode + ", scanType=" + scanType + ", clientCode=" + clientCode + ", filePath="
		+ filePath + ", quantity=" + quantity + ", quantityUnit=" + quantityUnit + ", varietyId=" + varietyId
		+ ", refernceId=" + refernceId + ", installatonCenterId=" + installatonCenterId + ", customerId="
		+ customerId + ", dateDone=" + dateDone + ", deviceSerialNo=" + deviceSerialNo + ", farmerId="
		+ farmerId + ", qualityScore=" + qualityScore + ", userId=" + userId + ", createdBy=" + createdBy
		+ ", fileName=" + fileName + ", vendorId=" + vendorId + ", weight=" + weight + ", amount=" + amount
		+ ", weightAmt=" + weightAmt + ", regionId=" + regionId + ", instCenterTypeId=" + instCenterTypeId
		+ ", commodityVarietyId=" + commodityVarietyId + ", commodityCategoryId=" + commodityCategoryId
		+ ", commodityName=" + commodityName + ", grade=" + grade + ", deviceType=" + deviceType
		+ ", deviceTypeId=" + deviceTypeId + ", plotId=" + plotId + ", accepted=" + accepted + ", variance="
		+ variance + ", createdDate=" + createdDate + ", createdOnDate=" + createdOnDate + ", areaCovered="
		+ areaCovered + ", sampleId=" + sampleId + ", userName=" + userName + ", sectionId=" + sectionId
		+ ", barCode=" + barCode + ", imageUniqueId=" + imageUniqueId + ", approval=" + approval + ", density="
		+ density + ", results=" + results + "]";
    }

    public BigDecimal getAreaCovered() {
	return areaCovered;
    }

    public void setAreaCovered(BigDecimal areaCovered) {
	this.areaCovered = areaCovered;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public Date getCreatedOnDate() {
	return createdOnDate;
    }

    public Long getSectionId() {
	return sectionId;
    }

    public void setSectionId(Long sectionId) {
	this.sectionId = sectionId;
    }

    public String getRefernceId() {
	return refernceId;
    }

    public void setRefernceId(String refernceId) {
	this.refernceId = refernceId;
    }

    public String getDensity() {
	return density;
    }

    public void setDensity(String density) {
	this.density = density;
    }

    public Long getOperatorId() {
	return operatorId;
    }

    public void setOperatorId(Long operatorId) {
	this.operatorId = operatorId;
    }

    public String getSocietyName() {
	return societyName;
    }

    public void setSocietyName(String societyName) {
	this.societyName = societyName;
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

    public String getVarietyName() {
	return varietyName;
    }

    public void setVarietyName(String varietyName) {
	this.varietyName = varietyName;
    }

    public String getRemark() {
	return remark;
    }

    public void setRemark(String remark) {
	this.remark = remark;
    }

    public Long getStateAdmin() {
	return stateAdmin;
    }

    public void setStateAdmin(Long stateAdmin) {
	this.stateAdmin = stateAdmin;
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

    public String getGodownNumber() {
	return godownNumber;
    }

    public void setGodownNumber(String godownNumber) {
	this.godownNumber = godownNumber;
    }

    public String getWeighbridgeSlipNo() {
	return weighbridgeSlipNo;
    }

    public void setWeighbridgeSlipNo(String weighbridgeSlipNo) {
	this.weighbridgeSlipNo = weighbridgeSlipNo;
    }

    public String getCisCdfNo() {
	return cisCdfNo;
    }

    public void setCisCdfNo(String cisCdfNo) {
	this.cisCdfNo = cisCdfNo;
    }

    public String getSupplierName() {
	return supplierName;
    }

    public void setSupplierName(String supplierName) {
	this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
	return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
	this.supplierAddress = supplierAddress;
    }

    public String getAiResults() {
	return aiResults;
    }

    public void setAiResults(String aiResults) {
	this.aiResults = aiResults;
    }

}
