package com.agnext.unification.entity.cofco;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the device-commodity association database table.
 * 
 * @author Piyush
 * 
 */
@Entity
@Table(name = "device_commodity")
@NamedQuery(name = "DeviceCommodityEntity.findAll", query = "SELECT d FROM DeviceCommodityEntity d")
public class DeviceCommodityEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "device_id")
	private DcmDevice device;

	@ManyToOne
	@JoinColumn(name = "commodity_id")
	private CofcoCommodityEntity commodity;

	@Column(name = "status_id")
	private Integer statusId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DcmDevice getDevice() {
		return device;
	}

	public void setDevice(DcmDevice device) {
		this.device = device;
	}

	public CofcoCommodityEntity getCommodity() {
		return commodity;
	}

	public void setCommodity(CofcoCommodityEntity commodity) {
		this.commodity = commodity;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		return "DeviceCommodityEntity [id=" + id + ", device=" + device + ", commodity=" + commodity + ", statusId="
				+ statusId + "]";
	}
	
	

}
