package com.agnext.unification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class AnalyticsRangeModel {

	@JsonProperty("total_count")
	private Long count;

	@JsonProperty("range_model")
	private List<RangeModel> rangeList;

	public Long getCount() {
		return count;
	}

	public List<RangeModel> getRangeList() {
		return rangeList;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public void setRangeList(List<RangeModel> rangeList) {
		this.rangeList = rangeList;
	}

	

}
