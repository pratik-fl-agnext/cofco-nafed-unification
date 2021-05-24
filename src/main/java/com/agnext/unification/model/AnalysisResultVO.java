package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonIgnoreProperties
//@JsonInclude(Include.NON_EMPTY)
public class AnalysisResultVO {
	@JsonProperty("analysis_type")
	private String analysisType;

	@JsonProperty("analysis_value")
	private String analysisValue;

	@JsonProperty("analysis_unit")
	private String analysisUnit;

	@JsonProperty("analysis_result")
	private String result;

	@JsonProperty("density_value")
	private String densityValue;

	public String getAnalysisType() {
		return analysisType;
	}

	public void setAnalysisType(String analysisType) {
		this.analysisType = analysisType;
	}

	public String getAnalysisValue() {
		return analysisValue;
	}

	public void setAnalysisValue(String analysisValue) {
		this.analysisValue = analysisValue;
	}

	public String getAnalysisUnit() {
		return analysisUnit;
	}

	public void setAnalysisUnit(String analysisUnit) {
		this.analysisUnit = analysisUnit;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDensityValue() {
		return densityValue;
	}

	public void setDensityValue(String densityValue) {
		this.densityValue = densityValue;
	}

	@Override
	public String toString() {
		return "AnalysisResultVO [analysisType=" + analysisType + ", analysisValue=" + analysisValue + ", analysisUnit="
				+ analysisUnit + ", result=" + result + ", densityValue=" + densityValue + "]";
	}
	
	
	
}
