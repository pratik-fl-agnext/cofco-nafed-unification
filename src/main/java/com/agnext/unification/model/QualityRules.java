package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class QualityRules {

	private Long id;

	private String analysis_code;

	private Double max_val;

	private Double min_val;

	private Long commodity_id;

	private String grade;
	
	private Long ScanCount;

	public Long getScanCount() {
		return ScanCount;
	}

	public void setScanCount(Long scanCount) {
		ScanCount = scanCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnalysis_code() {
		return analysis_code;
	}

	public void setAnalysis_code(String analysis_code) {
		this.analysis_code = analysis_code;
	}

	public Double getMax_val() {
		return max_val;
	}

	public void setMax_val(Double max_val) {
		this.max_val = max_val;
	}

	public Double getMin_val() {
		return min_val;
	}

	public void setMin_val(Double min_val) {
		this.min_val = min_val;
	}

	public Long getCommodity_id() {
		return commodity_id;
	}

	public void setCommodity_id(Long commodity_id) {
		this.commodity_id = commodity_id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
