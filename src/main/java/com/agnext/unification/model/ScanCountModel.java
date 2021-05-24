package com.agnext.unification.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class ScanCountModel {

	@JsonProperty("scan_count")
	private Long scanCount;

	@JsonProperty("commodity_id")
	private Long commodityId;

	@JsonProperty("commodity_name")
	private String commodityName;

	@JsonProperty("scan_date")
	private String scanDate;

	@JsonProperty("quality_avg")
	private BigDecimal qualityAvg;

	@JsonProperty("total_collection")
	private BigDecimal totalCollection;
	
	@JsonProperty("unit")
	private String unit;

	@JsonProperty("center_collection")
	private BigDecimal centerCollection;

	@JsonProperty("region_collection")
	private BigDecimal regionCollection;

	@JsonProperty("date_done")
	private Long dateDone;

	@JsonProperty("difference")
	private BigDecimal difference;

	@JsonProperty("difference_percentage")
	private BigDecimal differencePercentage;

	@JsonProperty("total_payment")
	private BigDecimal totalPaymemnt;
	


	@JsonProperty("graph_data")
	private List<GraphDataModel> graphData;

	@JsonProperty("payment_count")
	private BigDecimal paymentCount;

	@JsonProperty("inst_center_id")
	private Long instCenterId;

	@JsonProperty("avg_acceptance_rate")
	private Long avgAcceptance;
	
	@JsonProperty("avg_variance_rate")
	private Long avgVariance;
	
	@JsonProperty("total_avg_variance_rate")
	private Long totalAvgVariance;


	
	
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Long getTotalAvgVariance() {
		return totalAvgVariance;
	}

	public void setTotalAvgVariance(Long totalAvgVariance) {
		this.totalAvgVariance = totalAvgVariance;
	}

	private List<ScanCountModel> data;
	
	
	
	public List<ScanCountModel> getData() {
		return data;
	}

	public void setData(List<ScanCountModel> data) {
		this.data = data;
	}

	public Long getAvgVariance() {
		return avgVariance;
	}

	public void setAvgVariance(Long avgVariance) {
		this.avgVariance = avgVariance;
	}

	public Long getAvgAcceptance() {
		return avgAcceptance;
	}

	public void setAvgAcceptance(Long avgAcceptance) {
		this.avgAcceptance = avgAcceptance;
	}

	public String getScanDate() {
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	public BigDecimal getQualityAvg() {
		return qualityAvg;
	}

	public void setQualityAvg(BigDecimal qualityAvg) {
		this.qualityAvg = qualityAvg;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public Long getScanCount() {
		return scanCount;
	}

	public void setScanCount(Long scanCount) {
		this.scanCount = scanCount;
	}

	public Long getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}

	public BigDecimal getTotalCollection() {
		return totalCollection;
	}

	public void setTotalCollection(BigDecimal totalCollection) {
		this.totalCollection = totalCollection;
	}

	public BigDecimal getCenterCollection() {
		return centerCollection;
	}

	public void setCenterCollection(BigDecimal centerCollection) {
		this.centerCollection = centerCollection;
	}

	public BigDecimal getRegionCollection() {
		return regionCollection;
	}

	public void setRegionCollection(BigDecimal regionCollection) {
		this.regionCollection = regionCollection;
	}

	@Override
	public String toString() {
		return "ScanCountModel [scanCount=" + scanCount + ", commodityId=" + commodityId + ", commodityName="
				+ commodityName + ", scanDate=" + scanDate + ", qualityAvg=" + qualityAvg + ", totalCollection="
				+ totalCollection + ", centerCollection=" + centerCollection + ", regionCollection=" + regionCollection
				+ ", dateDone=" + dateDone + ", difference=" + difference + ", differencePercentage="
				+ differencePercentage + ", totalPaymemnt=" + totalPaymemnt + ", graphData=" + graphData
				+ ", paymentCount=" + paymentCount + ", instCenterId=" + instCenterId + "]";
	}

	public Long getDateDone() {
		return dateDone;
	}

	public void setDateDone(Long dateDate) {
		this.dateDone = dateDate;
	}

	public BigDecimal getDifference() {
		return difference;
	}

	public void setDifference(BigDecimal difference) {
		this.difference = difference;
	}

	public BigDecimal getDifferencePercentage() {
		return differencePercentage;
	}

	public void setDifferencePercentage(BigDecimal differencePercentage) {
		this.differencePercentage = differencePercentage;
	}

	public List<GraphDataModel> getGraphData() {
		return graphData;
	}

	public void setGraphData(List<GraphDataModel> graphData) {
		this.graphData = graphData;
	}

	public BigDecimal getTotalPaymemnt() {
		return totalPaymemnt;
	}

	public void setTotalPaymemnt(BigDecimal totalPaymemnt) {
		this.totalPaymemnt = totalPaymemnt;
	}

	public BigDecimal getPaymentCount() {
		return paymentCount;
	}

	public void setPaymentCount(BigDecimal paymentCount) {
		this.paymentCount = paymentCount;
	}

	public Long getInstCenterId() {
		return instCenterId;
	}

	public void setInstCenterId(Long instCenterId) {
		this.instCenterId = instCenterId;
	}

//	public GraphDataModel getGraphData() {
//		return graphData;
//	}
//
//	public void setGraphData(GraphDataModel graphData) {
//		this.graphData = graphData;
//	}

}
