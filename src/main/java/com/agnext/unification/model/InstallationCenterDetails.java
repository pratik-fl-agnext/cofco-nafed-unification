package com.agnext.unification.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class InstallationCenterDetails {
    @JsonProperty("inst_center_id")
    private Long instCenterId;

    @JsonProperty("inst_center_name")
    private String instCenterName;

    @JsonProperty("warehouse_name")
    private String warehouseName;

    @JsonProperty("code")
    private String code;

    @JsonProperty("inst_center_type_id")
    private Long instCenterTypeId;

    @JsonProperty("inst_center_type_name")
    private String instCenterTypeName;

    @JsonProperty("total_quantity")
    private BigDecimal totalQuantity;

    @JsonProperty("quantity_unit")
    private String quantityUnit;

    @JsonProperty("difference")
    private BigDecimal difference;

    @JsonProperty("difference_percentage")
    private BigDecimal differencePercentage;

    @JsonProperty("device_type")
    private List<String> deviceTypes;

    @JsonProperty("device_type_data")
    private List<DeviceTypeDataModel> deviceTypeDataModel;

    public Long getInstCenterId() {
	return instCenterId;
    }

    public void setInstCenterId(Long instCenterId) {
	this.instCenterId = instCenterId;
    }

    public String getInstCenterName() {
	return instCenterName;
    }

    public void setInstCenterName(String instCenterName) {
	this.instCenterName = instCenterName;
    }

    public Long getInstCenterTypeId() {
	return instCenterTypeId;
    }

    public void setInstCenterTypeId(Long instCenterTypeId) {
	this.instCenterTypeId = instCenterTypeId;
    }

    public String getInstCenterTypeName() {
	return instCenterTypeName;
    }

    public void setInstCenterTypeName(String instCenterTypeName) {
	this.instCenterTypeName = instCenterTypeName;
    }

    public BigDecimal getTotalQuantity() {
	return totalQuantity;
    }

    public void setTotalQuantity(BigDecimal totalQuantity) {
	this.totalQuantity = totalQuantity;
    }

    public String getQuantityUnit() {
	return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
	this.quantityUnit = quantityUnit;
    }

    public BigDecimal getDifference() {
	return difference;
    }

    public void setDifference(BigDecimal difference) {
	this.difference = difference;
    }

    public BigDecimal getDifferencePercentage() {
	return differencePercentage;
    }

    public void setDifferencePercentage(BigDecimal differencePercentage) {
	this.differencePercentage = differencePercentage;
    }

    public List<String> getDeviceTypes() {
	return deviceTypes;
    }

    public void setDeviceTypes(List<String> deviceTypes) {
	this.deviceTypes = deviceTypes;
    }

    @Override
    public String toString() {
	return "InstallationCenterDetails [instCenterId=" + instCenterId + ", instCenterName=" + instCenterName
		+ ", instCenterTypeId=" + instCenterTypeId + ", instCenterTypeName=" + instCenterTypeName
		+ ", totalQuantity=" + totalQuantity + ", quantityUnit=" + quantityUnit + ", difference=" + difference
		+ ", differencePercentage=" + differencePercentage + ", deviceTypes=" + deviceTypes + "]";
    }

    public List<DeviceTypeDataModel> getDeviceTypeDataModel() {
	return deviceTypeDataModel;
    }

    public void setDeviceTypeDataModel(List<DeviceTypeDataModel> deviceTypeDataModel) {
	this.deviceTypeDataModel = deviceTypeDataModel;
    }

    public String getWarehouseName() {
	return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
	this.warehouseName = warehouseName;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

}
