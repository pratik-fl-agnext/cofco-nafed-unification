package com.agnext.unification.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class MetaDataModel implements Serializable {

    @JsonProperty("Batch ID")
    private String batchId;
    @JsonProperty("LOT ID")
    private String lotId;
    @JsonProperty("Sample ID")
    private String sampleId;
    @JsonProperty("Quantity")
    private String quantity;
    @JsonProperty("Weight")
    private String weight;
    @JsonProperty("Client")
    private String customerName;
    @JsonProperty("Truck Number")
    private String truckNumber;
    @JsonProperty("Slip Number")
    private String slipNumber;
    @JsonProperty("Gate Pass")
    private String gatePass;
    @JsonProperty("CAD Number")
    private String cadNumber;
    @JsonProperty("Number of Bags")
    private String bag;
    @JsonProperty("Truck Gross Weight")
    private String truckGrossWeight;
    @JsonProperty("Truck Tare Weight")
    private String truckTareWeight;
    @JsonProperty("Stack Number")
    private String stackNumber;
    @JsonProperty("Chamber Number")
    private String chamberNumber;
    @JsonProperty("Average Weight Per Bag")
    private String avgWeightPerBag;
    @JsonProperty("Packing Size")
    private String packingSize;
    @JsonProperty("GRN Number")
    private String grnNumber;
    @JsonProperty("Weight Bridge Name")
    private String weightBridgeName;

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

    public String getQuantity() {
	return quantity;
    }

    public void setQuantity(String quantity) {
	this.quantity = quantity;
    }

    public String getWeight() {
	return weight;
    }

    public void setWeight(String weight) {
	this.weight = weight;
    }

    public String getCustomerName() {
	return customerName;
    }

    public void setCustomerName(String customerName) {
	this.customerName = customerName;
    }

    public String getTruckNumber() {
	return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
	this.truckNumber = truckNumber;
    }

    public String getSlipNumber() {
	return slipNumber;
    }

    public void setSlipNumber(String slipNumber) {
	this.slipNumber = slipNumber;
    }

    public String getGatePass() {
	return gatePass;
    }

    public void setGatePass(String gatePass) {
	this.gatePass = gatePass;
    }

    public String getCadNumber() {
	return cadNumber;
    }

    public void setCadNumber(String cadNumber) {
	this.cadNumber = cadNumber;
    }

    public String getBag() {
	return bag;
    }

    public void setBag(String bag) {
	this.bag = bag;
    }

    public String getTruckGrossWeight() {
	return truckGrossWeight;
    }

    public void setTruckGrossWeight(String truckGrossWeight) {
	this.truckGrossWeight = truckGrossWeight;
    }

    public String getTruckTareWeight() {
	return truckTareWeight;
    }

    public void setTruckTareWeight(String truckTareWeight) {
	this.truckTareWeight = truckTareWeight;
    }

    public String getStackNumber() {
	return stackNumber;
    }

    public void setStackNumber(String stackNumber) {
	this.stackNumber = stackNumber;
    }

    public String getChamberNumber() {
	return chamberNumber;
    }

    public void setChamberNumber(String chamberNumber) {
	this.chamberNumber = chamberNumber;
    }

    public String getAvgWeightPerBag() {
	return avgWeightPerBag;
    }

    public void setAvgWeightPerBag(String avgWeightPerBag) {
	this.avgWeightPerBag = avgWeightPerBag;
    }

    public String getPackingSize() {
	return packingSize;
    }

    public void setPackingSize(String packingSize) {
	this.packingSize = packingSize;
    }

    public String getGrnNumber() {
	return grnNumber;
    }

    public void setGrnNumber(String grnNumber) {
	this.grnNumber = grnNumber;
    }

    public String getWeightBridgeName() {
	return weightBridgeName;
    }

    public void setWeightBridgeName(String weightBridgeName) {
	this.weightBridgeName = weightBridgeName;
    }

    @Override
    public String toString() {
	return "MetaDataModel{" + "batchId='" + batchId + '\'' + ", lotId='" + lotId + '\'' + ", sampleId='" + sampleId
		+ '\'' + ", quantity='" + quantity + '\'' + ", weight='" + weight + '\'' + ", customerName='"
		+ customerName + '\'' + ", truckNumber='" + truckNumber + '\'' + ", slipNumber='" + slipNumber + '\''
		+ ", gatePass='" + gatePass + '\'' + ", cadNumber='" + cadNumber + '\'' + ", bag='" + bag + '\''
		+ ", truckGrossWeight='" + truckGrossWeight + '\'' + ", truckTareWeight='" + truckTareWeight + '\''
		+ ", stackNumber='" + stackNumber + '\'' + ", chamberNumber='" + chamberNumber + '\''
		+ ", avgWeightPerBag='" + avgWeightPerBag + '\'' + ", packingSize='" + packingSize + '\''
		+ ", grnNumber='" + grnNumber + '\'' + ", weightBridgeName='" + weightBridgeName + '\'' + '}';
    }
}
