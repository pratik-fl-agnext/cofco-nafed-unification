package com.agnext.unification.entity.nafed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="scan_result")
public class ScanResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="analysis_name")
	private String analysisName;
	
	@Column(name="analysis_code")
	private String analysisCode;
	
	@Column(name="result")
	private Double result;
	
	@ManyToOne
	@JoinColumn(name="scan_id")
	private ScanEntity scanEntity;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the analysisName
	 */
	public String getAnalysisName() {
		return analysisName;
	}

	/**
	 * @param analysisName the analysisName to set
	 */
	public void setAnalysisName(String analysisName) {
		this.analysisName = analysisName;
	}

	/**
	 * @return the analysisCode
	 */
	public String getAnalysisCode() {
		return analysisCode;
	}

	/**
	 * @param analysisCode the analysisCode to set
	 */
	public void setAnalysisCode(String analysisCode) {
		this.analysisCode = analysisCode;
	}

	/**
	 * @return the result
	 */
	public Double getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(Double result) {
		this.result = result;
	}

	/**
	 * @return the scanEntity
	 */
	public ScanEntity getScanEntity() {
		return scanEntity;
	}

	/**
	 * @param scanEntity the scanEntity to set
	 */
	public void setScanEntity(ScanEntity scanEntity) {
		this.scanEntity = scanEntity;
	}

	
}
