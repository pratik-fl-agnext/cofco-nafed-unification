package com.agnext.unification.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class ScanModel {

	@JsonProperty("scm_entity")
	private UUID scanId;

	@JsonProperty("scan_id")
	private Long id;

	@JsonProperty("batch_id")
	private String batchId;

	@JsonProperty("commodity_id")
	private Long commodityId;

	@JsonProperty("created_on")
	private Long createdOn;

	@JsonProperty("farmer_code")
	private String farmerCode;

	@JsonProperty("location")
	private String location;

	@JsonProperty("lot_id")
	private String lotId;

	@JsonProperty("scan_by_user_code")
	private String scanByUserCode;

	@JsonProperty("location_code")
	private String locationCode;

	@JsonProperty("device_code")
	private String deviceCode;

	@JsonProperty("vendor_code")
	private String vendorCode;

	@JsonProperty("scan_type")
	private String scanType;

	@JsonProperty("client_code")
	private String clientCode;

	@JsonProperty("file_path")
	private String filePath;

	@JsonProperty("result_data")
	private JSONObject resultData;

	@JsonProperty("quantity")
	private BigDecimal quantity;

	@JsonProperty("quantity_unit")
	private String quantityUnit;

	@JsonProperty("sample_id")
	private String sampleId;

	@JsonProperty("variety_id")
	private Long varietyId;

	@JsonProperty("area_covered")
	private BigDecimal areaCovered;

	@JsonProperty("assist_id")
	private Long assistId;

	@JsonProperty("cc_id")
	private Long ccId;

	@JsonProperty("company_id")
	private Long companyId;

	@JsonProperty("plot_id")
	private Long plotId;

	@JsonProperty("date_done")
	private Long dateDone;

	@JsonProperty("device_serial_no")
	private String deviceSerialNo;

	@JsonProperty("result")
	private String result;

	@JsonProperty("farmer_id")
	private Long farmerId;

	@JsonProperty("message")
	private String message;

	@JsonProperty("msg_time_stamp")
	private String msgTimeStamp;

	@JsonProperty("state_id")
	private Long stateId;

	@JsonProperty("approval_desc")
	private String approvalDesc;

	@JsonProperty("analytics")
	private AllAnalysisModel analytics;

	// @JsonProperty("scm_entity")
	// private UUID flc_scan_id ;

	@JsonProperty("section_id")
	private Long sectionId;

	@JsonProperty("season_id")
	private Long seasonId;

	@JsonProperty("status")
	private Integer status;

	@JsonProperty("user_id")
	private Long userId;

	@JsonProperty("version")
	private Long version;

	@JsonProperty("agent_code")
	private String agentcode;

	@JsonProperty("bar_code")
	private String barCode;

	@JsonProperty("created_by")
	private Long createdBy;

	@JsonProperty("file_name")
	private String fileName;

	@JsonProperty("image_unique_id")
	private String imageUniqueId;

	@JsonProperty("reference_id")
	private String referenceId;

	@JsonProperty("vendor_id")
	private Long vendorId;

	// @JsonProperty("scm_entity")
	// private visio_scan_id uuid,

	@JsonProperty("weight")
	private BigDecimal weight;

	@JsonProperty("amount")
	private BigDecimal amount;

	@JsonProperty("weight_amt")
	private BigDecimal weightAmt;

	@JsonProperty("region_id")
	private Long regionId;

	@JsonProperty("inst_center_type_Id")
	private Long instCenterTypeId;

	@JsonProperty("inst_center_id")
	private Long instCenterId;

	@JsonProperty("session_id")
	private Long sessionId;

	@JsonProperty("commodity_variety_id")
	private Long commodityVarietyId;

	@JsonProperty("commodity_category_id")
	private Long commodityCategoryId;

	@JsonProperty("commodity_name")
	private String commodityName;

	@JsonProperty("flc_type")
	private String flcType;

	@JsonProperty("grade")
	private String grade;

	@JsonProperty("grain_count")
	private Integer grainCount;

	@JsonProperty("detergent")
	private Boolean detergent;

	@JsonProperty("fat")
	private BigDecimal fat;

	@JsonProperty("glabridin")
	private BigDecimal glabridin;

	@JsonProperty("moisture")
	private BigDecimal moisture;

	@JsonProperty("oil")
	private BigDecimal oil;

	@JsonProperty("palm_oil")
	private BigDecimal palmOil;

	@JsonProperty("protein")
	private BigDecimal protein;

	@JsonProperty("snf")
	private BigDecimal snf;

	@JsonProperty("urea")
	private BigDecimal urea;

	@JsonProperty("one_banjhi_count")
	private Integer oneBanjhiCount;

	@JsonProperty("one_bud_count")
	private Integer oneBudCount;

	@JsonProperty("one_leaf_banjhi")
	private Integer oneLeafBanjhi;

	@JsonProperty("one_leaf_bud")
	private Integer oneLeafBud;

	@JsonProperty("one_leaf_count")
	private Integer oneLeafCount;

	@JsonProperty("quality_score")
	private BigDecimal qualityScore;

	@JsonProperty("three_leaf_bud")
	private Integer threeLeafBud;

	@JsonProperty("three_leaf_count")
	private Integer threeLeafCount;

	@JsonProperty("total_count")
	private BigDecimal totalCount;

	@JsonProperty("total_weight")
	private BigDecimal totalWeight;

	@JsonProperty("two_leaf_banjhi")
	private Integer twoLeafBanjhi;

	@JsonProperty("two_leaf_bud")
	private Integer twoLeafBud;

	@JsonProperty("two_leaf_count")
	private Integer twoLeafCount;

	@JsonProperty("admixture")
	private BigDecimal admixture;

	@JsonProperty("aspect_ratio")
	private BigDecimal aspectRatio;

	@JsonProperty("black")
	private BigDecimal black;

	@JsonProperty("broken")
	private BigDecimal broken;

	@JsonProperty("brown")
	private BigDecimal brown;

	@JsonProperty("chalky")
	private BigDecimal chalky;

	@JsonProperty("clean")
	private BigDecimal clean;

	@JsonProperty("count_per_oz")
	private String countPerOz;

	@JsonProperty("damaged")
	private BigDecimal damaged;

	@JsonProperty("density")
	private String density;

	@JsonProperty("discolored")
	private BigDecimal discolored;

	@JsonProperty("foreign_matters")
	private BigDecimal foreignMatters;

	@JsonProperty("green")
	private BigDecimal green;

	@JsonProperty("immature")
	private BigDecimal immature;

	@JsonProperty("other")
	private BigDecimal other;

	@JsonProperty("radius")
	private BigDecimal radius;

	@JsonProperty("red_rice")
	private BigDecimal redRice;

	@JsonProperty("shell")
	private BigDecimal shell;

	@JsonProperty("shrivelled")
	private BigDecimal shrivelled;

	@JsonProperty("weevilled")
	private BigDecimal weevilled;

	@JsonProperty("protein_dm")
	private BigDecimal proteinDm;

	@JsonProperty("ash_dm")
	private BigDecimal ashDm;

	@JsonProperty("starch")
	private BigDecimal starch;

	@JsonProperty("oil_dm")
	private BigDecimal oilDm;

	@JsonProperty("sol_protein")
	private BigDecimal solProtein;

	@JsonProperty("fibre")
	private BigDecimal fibre;

	@JsonProperty("ash")
	private BigDecimal ash;

	@JsonProperty("gluten")
	private BigDecimal gluten;

	@JsonProperty("amylose")
	private BigDecimal amylose;

	@JsonProperty("oil_wm")
	private BigDecimal oil_wm;

	@JsonProperty("anonymous")
	private String anonymous;

	@JsonProperty("curcumin")
	private BigDecimal curcumin;

	@JsonProperty("partner_code")
	private String partnerCode;

	@JsonProperty("prod_id")
	private Long prodId;

	@JsonProperty("device_type_id")
	private Long deviceTypeId;

	@JsonProperty("device_type")
	private String deviceType;

	@JsonProperty("data")
	private String data;

	@JsonProperty("analysis")
	private List<Analytics> quality;

	@JsonProperty("approval")
	private Integer approval;

	@JsonProperty("customer_name")
	private String customerName;

	@JsonProperty("truck_number")
	private String truckNumber;

	@JsonProperty("slip_no")
	private String slipNo;

	@JsonProperty("gate_pass")
	private String gatePass;

	@JsonProperty("cad_no")
	private String cadNo;

	@JsonProperty("bag")
	private String bag;

	@JsonProperty("truck_gross_weight")
	private String truckGrossWeight;

	@JsonProperty("truck_tare_weight")
	private String truckTareWeight;

	@JsonProperty("truck_net_weight")
	private String truckNetWeight;

	@JsonProperty("stack_number")
	private String stackNumber;

	@JsonProperty("chamber_number")
	private String chamberNumber;

	@JsonProperty("avg_weight_per_bag")
	private String avgWeightPerBag;

	@JsonProperty("packing_size")
	private String packingSize;

	@JsonProperty("state")
	private String state;

	@JsonProperty("weighbridge_name")
	private String weighbridgeName;

	@JsonProperty("crop_year")
	private String cropYear;

	@JsonProperty("grn_number")
	private String GRNNumber;

	@JsonProperty("w_house")
	private String wHouse;

	@JsonProperty("operator_id")
	private Long operatorId;

	@JsonProperty("client_id")
	private Long clientId;

	@JsonProperty("variety")
	private Long variety;

	@JsonProperty("society_name")
	private String societyName;

	@JsonProperty("quintal_quantity")
	private BigDecimal quintalQuantity;

	@JsonProperty("remark")
	private String remark;

	@JsonProperty("warehouse_name")
	private String warehouseName;

	@JsonProperty("number_of_bags")
	private String numberOfBags;

	@JsonProperty("accepted_bags")
	private String acceptedBags;

	@JsonProperty("rejected_bags")
	private String rejectedBags;

	@JsonProperty("state_admin")
	private Long stateAdmin;

	@JsonProperty("variety_name")
	private String varietyName;

	@JsonProperty("is_image_url_valid")
	private Boolean isImageUrlValid;

	@JsonProperty("device_model_no")
	private String deviceModelNo;

	@JsonProperty("mode_of_scan")
	private String modeOfScan;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the wHouse
	 */
	public String getwHouse() {
		return wHouse;
	}

	/**
	 * @param wHouse the wHouse to set
	 */
	public void setwHouse(String wHouse) {
		this.wHouse = wHouse;
	}

	/**
	 * @return the variety
	 */
	public Long getVariety() {
		return variety;
	}

	/**
	 * @param variety the variety to set
	 */
	public void setVariety(Long variety) {
		this.variety = variety;
	}

	/**
	 * @return the cropYear
	 */
	public String getCropYear() {
		return cropYear;
	}

	/**
	 * @param cropYear the cropYear to set
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
	 * @param gRNNumber the gRNNumber to set
	 */
	public void setGRNNumber(String gRNNumber) {
		GRNNumber = gRNNumber;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the weighbridgeName
	 */
	public String getWeighbridgeName() {
		return weighbridgeName;
	}

	/**
	 * @param weighbridgeName the weighbridgeName to set
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
	 * @param truckNumber the truckNumber to set
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
	 * @param slipNo the slipNo to set
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
	 * @param gatePass the gatePass to set
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
	 * @param cadNo the cadNo to set
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
	 * @param bag the bag to set
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
	 * @param truckGrossWeight the truckGrossWeight to set
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
	 * @param truckTareWeight the truckTareWeight to set
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
	 * @param truckNetWeight the truckNetWeight to set
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
	 * @param stackNumber the stackNumber to set
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
	 * @param chamberNumber the chamberNumber to set
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
	 * @param avgWeightPerBag the avgWeightPerBag to set
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
	 * @param packingSize the packingSize to set
	 */
	public void setPackingSize(String packingSize) {
		this.packingSize = packingSize;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the approval
	 */
	public Integer getApproval() {
		return approval;
	}

	/**
	 * @param approval the approval to set
	 */
	public void setApproval(Integer approval) {
		this.approval = approval;
	}

	public List<Analytics> getQuality() {
		return quality;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setQuality(List<Analytics> quality) {
		this.quality = quality;
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

	public Long getInstCenterId() {
		return instCenterId;
	}

	public void setInstCenterId(Long instCenterId) {
		this.instCenterId = instCenterId;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public UUID getScanId() {
		return scanId;
	}

	public void setScanId(UUID scanId) {
		this.scanId = scanId;
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

	public BigDecimal getAreaCovered() {
		return areaCovered;
	}

	public void setAreaCovered(BigDecimal areaCovered) {
		this.areaCovered = areaCovered;
	}

	public Long getAssistId() {
		return assistId;
	}

	public void setAssistId(Long assistId) {
		this.assistId = assistId;
	}

	public Long getCcId() {
		return ccId;
	}

	public void setCcId(Long ccId) {
		this.ccId = ccId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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

	public AllAnalysisModel getAnalytics() {
		return analytics;
	}

	public void setAnalytics(AllAnalysisModel analytics) {
		this.analytics = analytics;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Long getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Long seasonId) {
		this.seasonId = seasonId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getAgentcode() {
		return agentcode;
	}

	public void setAgentcode(String agentcode) {
		this.agentcode = agentcode;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
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

	public String getImageUniqueId() {
		return imageUniqueId;
	}

	public void setImageUniqueId(String imageUniqueId) {
		this.imageUniqueId = imageUniqueId;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
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

	public String getFlcType() {
		return flcType;
	}

	public void setFlcType(String flcType) {
		this.flcType = flcType;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Boolean getDetergent() {
		return detergent;
	}

	public void setDetergent(Boolean detergent) {
		this.detergent = detergent;
	}

	public BigDecimal getFat() {
		return fat;
	}

	public void setFat(BigDecimal fat) {
		this.fat = fat;
	}

	public BigDecimal getGlabridin() {
		return glabridin;
	}

	public void setGlabridin(BigDecimal glabridin) {
		this.glabridin = glabridin;
	}

	public BigDecimal getMoisture() {
		return moisture;
	}

	public void setMoisture(BigDecimal moisture) {
		this.moisture = moisture;
	}

	public BigDecimal getOil() {
		return oil;
	}

	public void setOil(BigDecimal oil) {
		this.oil = oil;
	}

	public BigDecimal getPalmOil() {
		return palmOil;
	}

	public void setPalmOil(BigDecimal palmOil) {
		this.palmOil = palmOil;
	}

	public BigDecimal getProtein() {
		return protein;
	}

	public void setProtein(BigDecimal protein) {
		this.protein = protein;
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

	public Integer getOneBanjhiCount() {
		return oneBanjhiCount;
	}

	public void setOneBanjhiCount(Integer oneBanjhiCount) {
		this.oneBanjhiCount = oneBanjhiCount;
	}

	public Integer getOneBudCount() {
		return oneBudCount;
	}

	public void setOneBudCount(Integer oneBudCount) {
		this.oneBudCount = oneBudCount;
	}

	public Integer getOneLeafBanjhi() {
		return oneLeafBanjhi;
	}

	public void setOneLeafBanjhi(Integer oneLeafBanjhi) {
		this.oneLeafBanjhi = oneLeafBanjhi;
	}

	public Integer getOneLeafBud() {
		return oneLeafBud;
	}

	public void setOneLeafBud(Integer oneLeafBud) {
		this.oneLeafBud = oneLeafBud;
	}

	public Integer getOneLeafCount() {
		return oneLeafCount;
	}

	public void setOneLeafCount(Integer oneLeafCount) {
		this.oneLeafCount = oneLeafCount;
	}

	public BigDecimal getQualityScore() {
		return qualityScore;
	}

	public void setQualityScore(BigDecimal qualityScore) {
		this.qualityScore = qualityScore;
	}

	public Integer getThreeLeafBud() {
		return threeLeafBud;
	}

	public void setThreeLeafBud(Integer threeLeafBud) {
		this.threeLeafBud = threeLeafBud;
	}

	public Integer getThreeLeafCount() {
		return threeLeafCount;
	}

	public void setThreeLeafCount(Integer threeLeafCount) {
		this.threeLeafCount = threeLeafCount;
	}

	public BigDecimal getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(BigDecimal totalCount) {
		this.totalCount = totalCount;
	}

	public BigDecimal getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(BigDecimal totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Integer getTwoLeafBanjhi() {
		return twoLeafBanjhi;
	}

	public void setTwoLeafBanjhi(Integer twoLeafBanjhi) {
		this.twoLeafBanjhi = twoLeafBanjhi;
	}

	public Integer getTwoLeafBud() {
		return twoLeafBud;
	}

	public void setTwoLeafBud(Integer twoLeafBud) {
		this.twoLeafBud = twoLeafBud;
	}

	public Integer getTwoLeafCount() {
		return twoLeafCount;
	}

	public void setTwoLeafCount(Integer twoLeafCount) {
		this.twoLeafCount = twoLeafCount;
	}

	public BigDecimal getAdmixture() {
		return admixture;
	}

	public void setAdmixture(BigDecimal admixture) {
		this.admixture = admixture;
	}

	public BigDecimal getAspectRatio() {
		return aspectRatio;
	}

	public void setAspectRatio(BigDecimal aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public BigDecimal getBlack() {
		return black;
	}

	public void setBlack(BigDecimal black) {
		this.black = black;
	}

	public BigDecimal getBroken() {
		return broken;
	}

	public void setBroken(BigDecimal broken) {
		this.broken = broken;
	}

	public BigDecimal getBrown() {
		return brown;
	}

	public void setBrown(BigDecimal brown) {
		this.brown = brown;
	}

	public BigDecimal getChalky() {
		return chalky;
	}

	public void setChalky(BigDecimal chalky) {
		this.chalky = chalky;
	}

	public BigDecimal getClean() {
		return clean;
	}

	public void setClean(BigDecimal clean) {
		this.clean = clean;
	}

	public String getCountPerOz() {
		return countPerOz;
	}

	public void setCountPerOz(String countPerOz) {
		this.countPerOz = countPerOz;
	}

	public BigDecimal getDamaged() {
		return damaged;
	}

	public void setDamaged(BigDecimal damaged) {
		this.damaged = damaged;
	}

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	public BigDecimal getDiscolored() {
		return discolored;
	}

	public void setDiscolored(BigDecimal discolored) {
		this.discolored = discolored;
	}

	public BigDecimal getForeignMatters() {
		return foreignMatters;
	}

	public void setForeignMatters(BigDecimal foreignMatters) {
		this.foreignMatters = foreignMatters;
	}

	public Integer getGrainCount() {
		return grainCount;
	}

	public void setGrainCount(Integer grainCount) {
		this.grainCount = grainCount;
	}

	public BigDecimal getGreen() {
		return green;
	}

	public void setGreen(BigDecimal green) {
		this.green = green;
	}

	public BigDecimal getImmature() {
		return immature;
	}

	public void setImmature(BigDecimal immature) {
		this.immature = immature;
	}

	public BigDecimal getOther() {
		return other;
	}

	public void setOther(BigDecimal other) {
		this.other = other;
	}

	public BigDecimal getRadius() {
		return radius;
	}

	public void setRadius(BigDecimal radius) {
		this.radius = radius;
	}

	public BigDecimal getRedRice() {
		return redRice;
	}

	public void setRedRice(BigDecimal redRice) {
		this.redRice = redRice;
	}

	public BigDecimal getShell() {
		return shell;
	}

	public void setShell(BigDecimal shell) {
		this.shell = shell;
	}

	public BigDecimal getShrivelled() {
		return shrivelled;
	}

	public void setShrivelled(BigDecimal shrivelled) {
		this.shrivelled = shrivelled;
	}

	public BigDecimal getWeevilled() {
		return weevilled;
	}

	public void setWeevilled(BigDecimal weevilled) {
		this.weevilled = weevilled;
	}

	public BigDecimal getProteinDm() {
		return proteinDm;
	}

	public void setProteinDm(BigDecimal proteinDm) {
		this.proteinDm = proteinDm;
	}

	public BigDecimal getAshDm() {
		return ashDm;
	}

	public void setAshDm(BigDecimal ashDm) {
		this.ashDm = ashDm;
	}

	public BigDecimal getStarch() {
		return starch;
	}

	public void setStarch(BigDecimal starch) {
		this.starch = starch;
	}

	public BigDecimal getOilDm() {
		return oilDm;
	}

	public void setOilDm(BigDecimal oilDm) {
		this.oilDm = oilDm;
	}

	public BigDecimal getSolProtein() {
		return solProtein;
	}

	public void setSolProtein(BigDecimal solProtein) {
		this.solProtein = solProtein;
	}

	public BigDecimal getFibre() {
		return fibre;
	}

	public void setFibre(BigDecimal fibre) {
		this.fibre = fibre;
	}

	public BigDecimal getAsh() {
		return ash;
	}

	public void setAsh(BigDecimal ash) {
		this.ash = ash;
	}

	public BigDecimal getGluten() {
		return gluten;
	}

	public void setGluten(BigDecimal gluten) {
		this.gluten = gluten;
	}

	public BigDecimal getAmylose() {
		return amylose;
	}

	public void setAmylose(BigDecimal amylose) {
		this.amylose = amylose;
	}

	public BigDecimal getOil_wm() {
		return oil_wm;
	}

	public void setOil_wm(BigDecimal oil_wm) {
		this.oil_wm = oil_wm;
	}

	public String getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(String anonymous) {
		this.anonymous = anonymous;
	}

	public BigDecimal getCurcumin() {
		return curcumin;
	}

	public void setCurcumin(BigDecimal curcumin) {
		this.curcumin = curcumin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPlotId() {
		return plotId;
	}

	public void setPlotId(Long plotId) {
		this.plotId = plotId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public JSONObject getResultData() {
		return resultData;
	}

	public void setResultData(JSONObject resultData) {
		this.resultData = resultData;
	}

	@Override
	public String toString() {
		return "ScanModel [scanId=" + scanId + ", id=" + id + ", batchId=" + batchId + ", commodityId=" + commodityId
				+ ", createdOn=" + createdOn + ", farmerCode=" + farmerCode + ", location=" + location + ", lotId="
				+ lotId + ", scanByUserCode=" + scanByUserCode + ", locationCode=" + locationCode + ", deviceCode="
				+ deviceCode + ", vendorCode=" + vendorCode + ", scanType=" + scanType + ", clientCode=" + clientCode
				+ ", filePath=" + filePath + ", resultData=" + resultData + ", quantity=" + quantity + ", quantityUnit="
				+ quantityUnit + ", sampleId=" + sampleId + ", varietyId=" + varietyId + ", areaCovered=" + areaCovered
				+ ", assistId=" + assistId + ", ccId=" + ccId + ", companyId=" + companyId + ", plotId=" + plotId
				+ ", dateDone=" + dateDone + ", deviceSerialNo=" + deviceSerialNo + ", result=" + result + ", farmerId="
				+ farmerId + ", message=" + message + ", msgTimeStamp=" + msgTimeStamp + ", stateId=" + stateId
				+ ", approvalDesc=" + approvalDesc + ", analytics=" + analytics + ", sectionId=" + sectionId
				+ ", seasonId=" + seasonId + ", status=" + status + ", userId=" + userId + ", version=" + version
				+ ", agentcode=" + agentcode + ", barCode=" + barCode + ", createdBy=" + createdBy + ", fileName="
				+ fileName + ", imageUniqueId=" + imageUniqueId + ", referenceId=" + referenceId + ", vendorId="
				+ vendorId + ", weight=" + weight + ", amount=" + amount + ", weightAmt=" + weightAmt + ", regionId="
				+ regionId + ", instCenterTypeId=" + instCenterTypeId + ", instCenterId=" + instCenterId
				+ ", sessionId=" + sessionId + ", commodityVarietyId=" + commodityVarietyId + ", commodityCategoryId="
				+ commodityCategoryId + ", commodityName=" + commodityName + ", flcType=" + flcType + ", grade=" + grade
				+ ", grainCount=" + grainCount + ", detergent=" + detergent + ", fat=" + fat + ", glabridin="
				+ glabridin + ", moisture=" + moisture + ", oil=" + oil + ", palmOil=" + palmOil + ", protein="
				+ protein + ", snf=" + snf + ", urea=" + urea + ", oneBanjhiCount=" + oneBanjhiCount + ", oneBudCount="
				+ oneBudCount + ", oneLeafBanjhi=" + oneLeafBanjhi + ", oneLeafBud=" + oneLeafBud + ", oneLeafCount="
				+ oneLeafCount + ", qualityScore=" + qualityScore + ", threeLeafBud=" + threeLeafBud
				+ ", threeLeafCount=" + threeLeafCount + ", totalCount=" + totalCount + ", totalWeight=" + totalWeight
				+ ", twoLeafBanjhi=" + twoLeafBanjhi + ", twoLeafBud=" + twoLeafBud + ", twoLeafCount=" + twoLeafCount
				+ ", admixture=" + admixture + ", aspectRatio=" + aspectRatio + ", black=" + black + ", broken="
				+ broken + ", brown=" + brown + ", chalky=" + chalky + ", clean=" + clean + ", countPerOz=" + countPerOz
				+ ", damaged=" + damaged + ", density=" + density + ", discolored=" + discolored + ", foreignMatters="
				+ foreignMatters + ", green=" + green + ", immature=" + immature + ", other=" + other + ", radius="
				+ radius + ", redRice=" + redRice + ", shell=" + shell + ", shrivelled=" + shrivelled + ", weevilled="
				+ weevilled + ", proteinDm=" + proteinDm + ", ashDm=" + ashDm + ", starch=" + starch + ", oilDm="
				+ oilDm + ", solProtein=" + solProtein + ", fibre=" + fibre + ", ash=" + ash + ", gluten=" + gluten
				+ ", amylose=" + amylose + ", oil_wm=" + oil_wm + ", anonymous=" + anonymous + ", curcumin=" + curcumin
				+ ", partnerCode=" + partnerCode + ", prodId=" + prodId + ", deviceTypeId=" + deviceTypeId
				+ ", deviceType=" + deviceType + ", data=" + data + ", quality=" + quality + ", approval=" + approval
				+ ", customerName=" + customerName + ", truckNumber=" + truckNumber + ", slipNo=" + slipNo
				+ ", gatePass=" + gatePass + ", cadNo=" + cadNo + ", bag=" + bag + ", truckGrossWeight="
				+ truckGrossWeight + ", truckTareWeight=" + truckTareWeight + ", truckNetWeight=" + truckNetWeight
				+ ", stackNumber=" + stackNumber + ", chamberNumber=" + chamberNumber + ", avgWeightPerBag="
				+ avgWeightPerBag + ", packingSize=" + packingSize + ", state=" + state + ", weighbridgeName="
				+ weighbridgeName + ", cropYear=" + cropYear + ", GRNNumber=" + GRNNumber + ", wHouse=" + wHouse
				+ ", operatorId=" + operatorId + ", clientId=" + clientId + ", variety=" + variety + ", societyName="
				+ societyName + ", quintalQuantity=" + quintalQuantity + ", remark=" + remark + ", warehouseName="
				+ warehouseName + ", numberOfBags=" + numberOfBags + ", acceptedBags=" + acceptedBags
				+ ", rejectedBags=" + rejectedBags + ", stateAdmin=" + stateAdmin + ", varietyName=" + varietyName
				+ ", isImageUrlValid=" + isImageUrlValid + ", deviceModelNo=" + deviceModelNo + ", modeOfScan="
				+ modeOfScan + "]";
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getMsgTimeStamp() {
		return msgTimeStamp;
	}

	public void setMsgTimeStamp(String msgTimeStamp) {
		this.msgTimeStamp = msgTimeStamp;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getApprovalDesc() {
		return approvalDesc;
	}

	public void setApprovalDesc(String approvalDesc) {
		this.approvalDesc = approvalDesc;
	}

	public String getSocietyName() {
		return societyName;
	}

	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}

	public BigDecimal getQuintalQuantity() {
		return quintalQuantity;
	}

	public void setQuintalQuantity(BigDecimal quintalQuantity) {
		this.quintalQuantity = quintalQuantity;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
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

	public Long getStateAdmin() {
		return stateAdmin;
	}

	public void setStateAdmin(Long stateAdmin) {
		this.stateAdmin = stateAdmin;
	}

	public String getVarietyName() {
		return varietyName;
	}

	public void setVarietyName(String varietyName) {
		this.varietyName = varietyName;
	}

	public Boolean getIsImageUrlValid() {
		return isImageUrlValid;
	}

	public void setIsImageUrlValid(Boolean isImageUrlValid) {
		this.isImageUrlValid = isImageUrlValid;
	}

	public String getDeviceModelNo() {
		return deviceModelNo;
	}

	public void setDeviceModelNo(String deviceModelNo) {
		this.deviceModelNo = deviceModelNo;
	}

	public String getModeOfScan() {
		return modeOfScan;
	}

	public void setModeOfScan(String modeOfScan) {
		this.modeOfScan = modeOfScan;
	}

}
