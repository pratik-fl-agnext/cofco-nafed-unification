package com.agnext.unification.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(Include.NON_NULL)
public class QualityRulesModel {
	@JsonProperty("column_name")
	Set<String> column;
	
	private String analysis_code;
	private List<QualityRules> rules;
	public String getAnalysis_code() {
		return analysis_code;
	}
	public void setAnalysis_code(String analysis_code) {
		this.analysis_code = analysis_code;
	}
	public List<QualityRules> getRules() {
		return rules;
	}
	public void setRules(List<QualityRules> rules) {
		this.rules = rules;
	}
	public Set<String> getColumn() {
		return column;
	}
	public void setColumn(Set<String> column) {
		this.column = column;
	}
	
}
