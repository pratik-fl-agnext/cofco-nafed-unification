package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class MoistureMeterResultModel {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("sample_id")
    private String sampleId;

    @JsonProperty("client_id")
    private Long clientId;

    @JsonProperty("commodity_name")
    private String commodityName;

    @JsonProperty("moisture")
    private Double moisture;

    @JsonProperty("temperature")
    private Double temperature;

    @JsonProperty("truck_number")
    private String truckNumber;

    @JsonProperty("token")
    private String token;

    @JsonProperty("weight")
    private Double weight;

    @JsonProperty("variety_id")
    private Long varietyId;

    @JsonProperty("created_on")
    private String createdOn;
    
    @JsonProperty("variety_name")
    private String varietyName;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getSampleId() {
	return sampleId;
    }

    public void setSampleId(String sampleId) {
	this.sampleId = sampleId;
    }

    public Long getClientId() {
	return clientId;
    }

    public void setClientId(Long clientId) {
	this.clientId = clientId;
    }

    public String getCommodityName() {
	return commodityName;
    }

    public void setCommodityName(String commodityName) {
	this.commodityName = commodityName;
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

    public String getTruckNumber() {
	return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
	this.truckNumber = truckNumber;
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public Double getWeight() {
	return weight;
    }

    public void setWeight(Double weight) {
	this.weight = weight;
    }

    public Long getVarietyId() {
	return varietyId;
    }

    public void setVarietyId(Long varietyId) {
	this.varietyId = varietyId;
    }

    public String getCreatedOn() {
	return createdOn;
    }

    public void setCreatedOn(String createdOn) {
	this.createdOn = createdOn;
    }

	@Override
	public String toString() {
		return "MoistureMeterResultModel [id=" + id + ", sampleId=" + sampleId + ", clientId=" + clientId
				+ ", commodityName=" + commodityName + ", moisture=" + moisture + ", temperature=" + temperature
				+ ", truckNumber=" + truckNumber + ", token=" + token + ", weight=" + weight + ", varietyId="
				+ varietyId + ", createdOn=" + createdOn + "]";
	}

	public String getVarietyName() {
	    return varietyName;
	}

	public void setVarietyName(String varietyName) {
	    this.varietyName = varietyName;
	}

	
	
    
}
