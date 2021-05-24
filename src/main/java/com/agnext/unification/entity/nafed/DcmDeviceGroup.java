package com.agnext.unification.entity.nafed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dcm_device_group")
public class DcmDeviceGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "device_group_desc")
	private String deviceGroupDesc;

	@Column(name = "device_group_code")
	private String deviceGroupCode;


	@Column(name = "status_id")
	private Integer dcmStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceGroupDesc() {
		return deviceGroupDesc;
	}

	public void setDeviceGroupDesc(String deviceGroupDesc) {
		this.deviceGroupDesc = deviceGroupDesc;
	}

	public String getDeviceGroupCode() {
		return deviceGroupCode;
	}

	public void setDeviceGroupCode(String deviceGroupCode) {
		this.deviceGroupCode = deviceGroupCode;
	}

	

}
