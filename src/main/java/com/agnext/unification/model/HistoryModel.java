package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
//@JsonInclude(Include.NON_EMPTY)
public class HistoryModel {

    @JsonProperty("sample_id")
    private String sampleId;

    @JsonProperty("scan_history")
    private ScanHistoryModel scanHistoryModel;

    @JsonProperty("moisture_history")
    private MoistureHistoryModel moistureHistory;

    public String getSampleId() {
	return sampleId;
    }

    public void setSampleId(String sampleId) {
	this.sampleId = sampleId;
    }

    public MoistureHistoryModel getMoistureHistory() {
	return moistureHistory;
    }

    public void setMoistureHistory(MoistureHistoryModel moistureHistory) {
	this.moistureHistory = moistureHistory;
    }

    public ScanHistoryModel getScanHistoryModel() {
	return scanHistoryModel;
    }

    public void setScanHistoryModel(ScanHistoryModel scanHistoryModel) {
	this.scanHistoryModel = scanHistoryModel;
    }

    @Override
    public String toString() {
	return "HistoryModel [sampleId=" + sampleId + ", moistureHistory=" + moistureHistory + ", scanHistoryModel="
		+ scanHistoryModel + "]";
    }

}
