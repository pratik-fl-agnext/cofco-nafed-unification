package com.agnext.unification.model;

import java.io.Serializable;
import java.util.List;

public class ScansModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String result;
	private String lotId;
	private String sampleId;

	private String commodityId;
	private Long commodity;
	private String clientCode;
	private String partnerCode;
	private Long prodId;
	private String analysis;
	List<Analytics> analyticList;

	private String deviceSerialNum;
	private String deviceType;

	private String scanId;

	private Long userId;

	private String commodityCode;
	private String farmerCode;
	private String coordinates;
	private String userName;
	private String token;
	
	private Long clientId;
	private Long adminId;
	private Long operatorId;
	
	private Integer scanStatusId;
	private String scanStatusDesc;
	
	private String oldScanStatusDesc; 
	private String newScanStatusDesc;
	private String message;

	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public String getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}

	public String getFarmerCode() {
		return farmerCode;
	}

	public void setFarmerCode(String farmerCode) {
		this.farmerCode = farmerCode;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDeviceSerialNum() {
		return deviceSerialNum;
	}

	public void setDeviceSerialNum(String deviceSerialNum) {
		this.deviceSerialNum = deviceSerialNum;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getScanId() {
		return scanId;
	}

	public void setScanId(String string) {
		this.scanId = string;
	}

	/**
	 * @return the analyticList
	 */
	public List<Analytics> getAnalyticList() {
		return analyticList;
	}

	/**
	 * @param analyticList the analyticList to set
	 */
	public void setAnalyticList(List<Analytics> analyticList) {
		this.analyticList = analyticList;
	}

	/**
	 * @return the commodity
	 */
	public Long getCommodity() {
		return commodity;
	}

	/**
	 * @param commodity the commodity to set
	 */
	public void setCommodity(Long commodity) {
		this.commodity = commodity;
	}

	/**
	 * @return the analysis
	 */
	public String getAnalysis() {
		return analysis;
	}

	/**
	 * @param analysis the analysis to set
	 */
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	/**
	 * @return the partnerCode
	 */
	public String getPartnerCode() {
		return partnerCode;
	}

	/**
	 * @param partnerCode the partnerCode to set
	 */
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	/**
	 * @return the clientCode
	 */
	public String getClientCode() {
		return clientCode;
	}

	/**
	 * @param clientCode the clientCode to set
	 */
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	/**
	 * @return the commodityId
	 */
	public String getCommodityId() {
		return commodityId;
	}

	/**
	 * @param commodityId the commodityId to set
	 */
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the lotId
	 */
	public String getLotId() {
		return lotId;
	}

	/**
	 * @param lotId the lotId to set
	 */
	public void setLotId(String lotId) {
		this.lotId = lotId;
	}

	/**
	 * @return the sampleId
	 */
	public String getSampleId() {
		return sampleId;
	}

	/**
	 * @param sampleId the sampleId to set
	 */
	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	/**
	 * @return the prodId
	 */
	public Long getProdId() {
		return prodId;
	}

	/**
	 * @param prodId the prodId to set
	 */
	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public String getToken() {
	    return token;
	}

	public void setToken(String token) {
	    this.token = token;
	}

	@Override
	public String toString() {
	    return "ScansModel [result=" + result + ", lotId=" + lotId + ", sampleId=" + sampleId + ", commodityId="
		    + commodityId + ", commodity=" + commodity + ", clientCode=" + clientCode + ", partnerCode="
		    + partnerCode + ", prodId=" + prodId + ", analysis=" + analysis + ", analyticList=" + analyticList
		    + ", deviceSerialNum=" + deviceSerialNum + ", deviceType=" + deviceType + ", scanId=" + scanId
		    + ", userId=" + userId + ", commodityCode=" + commodityCode + ", farmerCode=" + farmerCode
		    + ", coordinates=" + coordinates + ", userName=" + userName + ", token=" + token + "]";
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public Integer getScanStatusId() {
		return scanStatusId;
	}

	public void setScanStatusId(Integer scanStatusId) {
		this.scanStatusId = scanStatusId;
	}

	public String getScanStatusDesc() {
		return scanStatusDesc;
	}

	public void setScanStatusDesc(String scanStatusDesc) {
		this.scanStatusDesc = scanStatusDesc;
	}

	public String getOldScanStatusDesc() {
		return oldScanStatusDesc;
	}

	public void setOldScanStatusDesc(String oldScanStatusDesc) {
		this.oldScanStatusDesc = oldScanStatusDesc;
	}

	public String getNewScanStatusDesc() {
		return newScanStatusDesc;
	}

	public void setNewScanStatusDesc(String newScanStatusDesc) {
		this.newScanStatusDesc = newScanStatusDesc;
	}
	
	
}
