package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
//@JsonInclude(Include.NON_NULL)
public class HistoryMoistureWrapperModel {

    @JsonProperty("history")
    private List<HistoryModel> history;
    
    @JsonProperty("total_count")
    private Long totalCount;

    public List<HistoryModel> getHistory() {
	return history;
    }

    public void setHistory(List<HistoryModel> history) {
	this.history = history;
    }

    @Override
    public String toString() {
	return "HistoryMoistureWrapperModel [history=" + history + "]";
    }

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

}
