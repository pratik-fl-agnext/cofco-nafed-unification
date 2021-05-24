package com.agnext.unification.model;

import java.util.UUID;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class ProScanModel {

	@JsonProperty("pro_scan_id")
	private UUID proScanId;

	@JsonProperty("scan_batch_id")
	private UUID scanBatchId;

	@JsonProperty("device_serial_no")
	private String deviceSerialNo;

	@JsonProperty("created_on")
	private Long createdOn;

//	@Column(value = "crop_id")
//	private Long cropId;

	@JsonProperty("file_name")
	private String fileName;

	@JsonProperty("file_path")
	private String filePath;

	@JsonProperty("device_id")
	private String deviceId;

	@JsonProperty("scan_id")
	private String scanId;

	@JsonProperty("data")
	private JSONObject resultData;

	@JsonProperty("crop_name")
	private String cropName;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getScanId() {
		return scanId;
	}

	public void setScanId(String scanId) {
		this.scanId = scanId;
	}

	public JSONObject getResultData() {
		return resultData;
	}

	public void setResultData(JSONObject resultData) {
		this.resultData = resultData;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public UUID getProScanId() {
		return proScanId;
	}

	public void setProScanId(UUID proScanId) {
		this.proScanId = proScanId;
	}

	public UUID getScanBatchId() {
		return scanBatchId;
	}

	public void setScanBatchId(UUID scanBatchId) {
		this.scanBatchId = scanBatchId;
	}

	public String getDeviceSerialNo() {
		return deviceSerialNo;
	}

	public void setDeviceSerialNo(String deviceSerialNo) {
		this.deviceSerialNo = deviceSerialNo;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "ProScanModel [proScanId=" + proScanId + ", scanBatchId=" + scanBatchId + ", deviceSerialNo="
				+ deviceSerialNo + ", createdOn=" + createdOn + ", fileName=" + fileName + ", filePath=" + filePath
				+ ", deviceId=" + deviceId + ", scanId=" + scanId + ", resultData=" + resultData + ", cropName="
				+ cropName + "]";
	}
	
	

}
