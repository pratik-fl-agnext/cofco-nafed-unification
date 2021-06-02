package com.agnext.unification.entity.cofco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dcm_sensor_profile")
public class DcmSensorProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "sensor_profile_desc")
	private String sensorProfileDesc;
	
	@Column(name = "sensor_profile_code")
	private String sensorProfileCode;
	
	@Column(name = "status_id")
	private Long statusId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSensorProfileDesc() {
		return sensorProfileDesc;
	}

	public void setSensorProfileDesc(String sensorProfileDesc) {
		this.sensorProfileDesc = sensorProfileDesc;
	}

	public String getSensorProfileCode() {
		return sensorProfileCode;
	}

	public void setSensorProfileCode(String sensorProfileCode) {
		this.sensorProfileCode = sensorProfileCode;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	
}
