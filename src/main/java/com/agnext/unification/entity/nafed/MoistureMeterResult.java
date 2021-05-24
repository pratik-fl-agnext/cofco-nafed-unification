package com.agnext.unification.entity.nafed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "moisture_meter_result")
public class MoistureMeterResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sample_id")
    private String sampleId;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "commodity_name")
    private String commodityName;

    @Column(name = "moisture")
    private Double moisture;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "truck_number")
    private String truckNumber;

    @Column(name = "token")
    private String token;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "variety_id")
    private Long varietyId;

    @Column(name = "variety_name")
    private String varietyName;

    @Column(name = "created_on")
    private String createdOn;

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

    public String getVarietyName() {
	return varietyName;
    }

    public void setVarietyName(String varietyName) {
	this.varietyName = varietyName;
    }

    public String getCreatedOn() {
	return createdOn;
    }

    public void setCreatedOn(String createdOn) {
	this.createdOn = createdOn;
    }

}
