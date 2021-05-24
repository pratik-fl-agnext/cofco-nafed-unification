package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CollectionDetailsModel {

    @JsonProperty("inst_center_id")
    private Long instCenterId;

    @JsonProperty("inst_center_name")
    private String instCenterName;

    @JsonProperty("inst_center_type_id")
    private Long instCenterTypeId;

    @JsonProperty("inst_center_type_name")
    private String instCenterTypeName;

    @JsonProperty("total_quantity")
    private String totalQuantity;

    @JsonProperty("quantity_unit")
    private String quantityUnit;

    @JsonProperty("difference")
    private Double difference;

    @JsonProperty("difference_percentage")
    private Double differencePercentage;

    @JsonProperty("device_type")
    private List<String> deviceTypes;

    @JsonProperty("inst_center_details")
    private List<InstallationCenterDetails> installationCenterDetails;

    @JsonProperty("collection")
    private CollectionModel collection;

    @JsonProperty("response_msg")
    private String responseMsg;

    public String getResponseMsg() {
	return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
	this.responseMsg = responseMsg;
    }

    public void setInstCenterTypeName(String instCenterTypeName) {
	this.instCenterTypeName = instCenterTypeName;
    }

    public String getTotalQuantity() {
	return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
	this.totalQuantity = totalQuantity;
    }

    public String getQuantityUnit() {
	return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
	this.quantityUnit = quantityUnit;
    }

    public Double getDifference() {
	return difference;
    }

    public void setDifference(Double difference) {
	this.difference = difference;
    }

    public Double getDifferencePercentage() {
	return differencePercentage;
    }

    public void setDifferencePercentage(Double differencePercentage) {
	this.differencePercentage = differencePercentage;
    }

    //	public String getInstCenterName() {
    //		return instCenterName;
    //	}
    //
    //	public void setInstCenterName(String instCenterName) {
    //		this.instCenterName = instCenterName;
    //	}

    //	public String getInstCenterTypeName() {
    //		return instCenterTypeName;
    //	}
    //
    //	public void setInstCenterTypeName(String instCenterTypeName) {
    //		this.instCenterTypeName = instCenterTypeName;
    //	}
    //
    //	public Double getTotalQuantity() {
    //		return totalQuantity;
    //	}
    //
    //	public void setTotalQuantity(Double totalQuantity) {
    //		this.totalQuantity = totalQuantity;
    //	}
    //
    //	public String getQuantityUnit() {
    //		return quantityUnit;
    //	}
    //
    //	public void setQuantityUnit(String quantityUnit) {
    //		this.quantityUnit = quantityUnit;
    //	}
    //
    //	public Double getDifference() {
    //		return difference;
    //	}
    //
    //	public void setDifference(Double difference) {
    //		this.difference = difference;
    //	}
    //
    //	public Double getDifferencePercentage() {
    //		return differencePercentage;
    //	}
    //
    //	public void setDifferencePercentage(Double differencePercentage) {
    //		this.differencePercentage = differencePercentage;
    //	}
    //
    //	public List<String> getDeviceTypes() {
    //		return deviceTypes;
    //	}
    //
    //	public void setDeviceTypes(List<String> deviceTypes) {
    //		this.deviceTypes = deviceTypes;
    //	}

    public List<String> getDeviceTypes() {
	return deviceTypes;
    }

    public void setDeviceTypes(List<String> deviceTypes) {
	this.deviceTypes = deviceTypes;
    }

    public CollectionModel getCollection() {
	return collection;
    }

    public void setCollection(CollectionModel collection) {
	this.collection = collection;
    }

    public Long getInstCenterId() {
	return instCenterId;
    }

    public void setInstCenterId(Long instCenterId) {
	this.instCenterId = instCenterId;
    }

    public Long getInstCenterTypeId() {
	return instCenterTypeId;
    }

    public void setInstCenterTypeId(Long instCenterTypeId) {
	this.instCenterTypeId = instCenterTypeId;
    }

    public List<InstallationCenterDetails> getInstallationCenterDetails() {
	return installationCenterDetails;
    }

    public void setInstallationCenterDetails(List<InstallationCenterDetails> installationCenterDetails) {
	this.installationCenterDetails = installationCenterDetails;
    }

    @Override
    public String toString() {
	return "CollectionDetailsModel [collection=" + collection + ", installationCenterDetails="
		+ installationCenterDetails + "]";
    }

    public String getInstCenterName() {
	return instCenterName;
    }

    public void setInstCenterName(String instCenterName) {
	this.instCenterName = instCenterName;
    }

    public String getInstCenterTypeName() {
	return instCenterTypeName;
    }

    //	public Set<String> getDeviceTypes() {
    //		return deviceTypes;
    //	}
    //
    //	public void setDeviceTypes(Set<String> deviceTypes) {
    //		this.deviceTypes = deviceTypes;
    //	}

}
