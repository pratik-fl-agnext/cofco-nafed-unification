package com.agnext.unification.entity.cofco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "scan_location")
public class ScanLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_on")
    private Long createdOn;

    @Column(name = "location_name")
    private String locationName;

    @ManyToOne
    @JoinColumn(name = "status")
    private StatusEntity status;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private StateEntity state;

    @Column(name = "warehouse_name")
    private String warehouseName;

    @Column(name = "code")
    private String code;

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * @return the createdOn
     */
    public Long getCreatedOn() {
	return createdOn;
    }

    /**
     * @param createdOn
     *            the createdOn to set
     */
    public void setCreatedOn(Long createdOn) {
	this.createdOn = createdOn;
    }

    public String getLocationName() {
	return locationName;
    }

    public void setLocationName(String locationName) {
	this.locationName = locationName;
    }

    public StatusEntity getStatus() {
	return status;
    }

    public void setStatus(StatusEntity status) {
	this.status = status;
    }

    public StateEntity getState() {
	return state;
    }

    public void setState(StateEntity state) {
	this.state = state;
    }

    public String getWarehouseName() {
	return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
	this.warehouseName = warehouseName;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

}
