package com.agnext.unification.entity.nafed;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the Client's Analytics Range by warehouse.
 * 
 */
@Entity
@Table(name="client_analytics_range")
public class ClientsAnalyticsRange implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "commodity_id")
	private DcmCommodity commodityId;
	
	@ManyToOne
	@JoinColumn(name = "analytics_id")
	private Analytics analytics;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private CustomerEntity client;
	
	@ManyToOne
	@JoinColumn(name="location_id")
	private ScanLocation location;
	
	@Column(name="warehouse_name")
	private String warehouseName;
	
	@Column(name="max_range")
	private String maxRange;
	
	@Column(name="min_range")
	private String minRange;
	
	@ManyToOne
	@JoinColumn(name="status_id")
	private StatusEntity status;
	
	@Column(name="created_on")
	private Long createdOn;

	public Long getId() {
		return id;
	}

	public DcmCommodity getCommodityId() {
		return commodityId;
	}

	public Analytics getAnalytics() {
		return analytics;
	}

	public CustomerEntity getClient() {
		return client;
	}

	public ScanLocation getLocation() {
		return location;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public String getMaxRange() {
		return maxRange;
	}

	public String getMinRange() {
		return minRange;
	}

	public StatusEntity getStatus() {
		return status;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCommodityId(DcmCommodity commodityId) {
		this.commodityId = commodityId;
	}

	public void setAnalytics(Analytics analytics) {
		this.analytics = analytics;
	}

	public void setClient(CustomerEntity client) {
		this.client = client;
	}

	public void setLocation(ScanLocation location) {
		this.location = location;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public void setMaxRange(String maxRange) {
		this.maxRange = maxRange;
	}

	public void setMinRange(String minRange) {
		this.minRange = minRange;
	}

	public void setStatus(StatusEntity status) {
		this.status = status;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "ClientsAnalyticsRange [id=" + id + ", commodityId=" + commodityId + ", analytics=" + analytics
				+ ", client=" + client + ", location=" + location + ", warehouseName=" + warehouseName + ", maxRange="
				+ maxRange + ", minRange=" + minRange + ", status=" + status + ", createdOn=" + createdOn + "]";
	}
	

}
