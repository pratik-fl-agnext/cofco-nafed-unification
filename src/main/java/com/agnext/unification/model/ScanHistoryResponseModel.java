package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class ScanHistoryResponseModel {


    @JsonProperty("total_count")
    private int totalCount;

    @JsonProperty("scan_history_data")
    private List<ScanResultModel> scanModelList;

    public ScanHistoryResponseModel() {

    }

    public int getTotalCount() {
	return totalCount;
    }

    public void setTotalCount(int totalCount) {
	this.totalCount = totalCount;
    }

    public List<ScanResultModel> getScanModelList() {
	return scanModelList;
    }

    public void setScanModelList(List<ScanResultModel> scanModelList) {
	this.scanModelList = scanModelList;
    }

}
