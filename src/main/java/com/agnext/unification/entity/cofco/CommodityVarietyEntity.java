package com.agnext.unification.entity.cofco;

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
 * The persistent class for the commodity-variety database table.
 * 
 */
@Entity
@Table(name="commodity_variety")
public class CommodityVarietyEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7017135730566228742L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	
	@Column(name="variety_name")
	private String varietyName;
	
	@ManyToOne
	@JoinColumn(name="commodity_id")
	private CofcoCommodityEntity commodity;
	
	@Column(name="status_id")
	private Integer statusId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVarietyName() {
		return varietyName;
	}

	public void setVarietyName(String varietyName) {
		this.varietyName = varietyName;
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
	
	
	
}
