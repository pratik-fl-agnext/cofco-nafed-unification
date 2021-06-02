package com.agnext.unification.entity.cofco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dcm_device_sub_type")
public class DcmDeviceSubType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "device_sub_type_desc")
	private String deviceSubTypeDesc;

	@Column(name = "device_sub_type_code")
	private String deviceSubTypeCode;

	@Column(name = "status_id")
	private Integer dcmStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceSubTypeDesc() {
		return deviceSubTypeDesc;
	}

	public void setDeviceSubTypeDesc(String deviceSubTypeDesc) {
		this.deviceSubTypeDesc = deviceSubTypeDesc;
	}

	public String getDeviceSubTypeCode() {
		return deviceSubTypeCode;
	}

	public void setDeviceSubTypeCode(String deviceSubTypeCode) {
		this.deviceSubTypeCode = deviceSubTypeCode;
	}

	/**
	 * @return the dcmStatus
	 */
	public Integer getDcmStatus() {
		return dcmStatus;
	}

	/**
	 * @param dcmStatus the dcmStatus to set
	 */
	public void setDcmStatus(Integer dcmStatus) {
		this.dcmStatus = dcmStatus;
	}

}
