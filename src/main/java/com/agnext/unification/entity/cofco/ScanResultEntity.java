package com.agnext.unification.entity.cofco;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "scm_scan_results")
public class ScanResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "analysis_name")
    private String analysisName;

    @Column(name = "analysis_code")
    private String analysisCode;

    @Column(name = "result")
    private BigDecimal result;

    @ManyToOne
    @JoinColumn(name = "scan_id")
    private ScanEntity scanEntity;

    @Column(name = "lab_result")
    private Double labResult;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getAnalysisName() {
	return analysisName;
    }

    public void setAnalysisName(String analysisName) {
	this.analysisName = analysisName;
    }

    public String getAnalysisCode() {
	return analysisCode;
    }

    public void setAnalysisCode(String analysisCode) {
	this.analysisCode = analysisCode;
    }

    public BigDecimal getResult() {
	return result;
    }

    public void setResult(BigDecimal result) {
	this.result = result;
    }

    public ScanEntity getScan() {
	return scanEntity;
    }

    public void setScan(ScanEntity scanEntity) {
	this.scanEntity = scanEntity;
    }

    public ScanEntity getScanEntity() {
	return scanEntity;
    }

    public void setScanEntity(ScanEntity scanEntity) {
	this.scanEntity = scanEntity;
    }

    public Double getLabResult() {
	return labResult;
    }

    public void setLabResult(Double labResult) {
	this.labResult = labResult;
    }

}
