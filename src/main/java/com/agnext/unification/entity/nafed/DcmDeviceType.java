package com.agnext.unification.entity.nafed;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the dcm_device_type database table.
 * 
 */
@Entity
@Table(name="dcm_device_type")
@NamedQuery(name="DcmDeviceType.findAll", query="SELECT d FROM DcmDeviceType d")
public class DcmDeviceType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="commodity_required")
	private Boolean commodityRequired;

	@Column(name="created_by")
	private Long createdBy;

	@Column(name="created_on")
	private Long createdOn;

	@Column(name="device_type_desc")
	private String deviceTypeDesc;

	@Column(name="license_required")
	private Boolean licenseRequired;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Column(name="modified_on")
	private Long modifiedOn;

	@Column(name="payment_required")
	private Boolean paymentRequired;

	//bi-directional many-to-one association to DcmDevice
	@OneToMany(mappedBy="dcmDeviceType")
	private List<DcmDevice> dcmDevices;

	

	public DcmDeviceType() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	 

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public String getDeviceTypeDesc() {
		return this.deviceTypeDesc;
	}

	public void setDeviceTypeDesc(String deviceTypeDesc) {
		this.deviceTypeDesc = deviceTypeDesc;
	}

	 

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Long getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Long modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	 

	public List<DcmDevice> getDcmDevices() {
		return this.dcmDevices;
	}

	public void setDcmDevices(List<DcmDevice> dcmDevices) {
		this.dcmDevices = dcmDevices;
	}

	public DcmDevice addDcmDevice(DcmDevice dcmDevice) {
		getDcmDevices().add(dcmDevice);
		dcmDevice.setDcmDeviceType(this);

		return dcmDevice;
	}

	public DcmDevice removeDcmDevice(DcmDevice dcmDevice) {
		getDcmDevices().remove(dcmDevice);
		dcmDevice.setDcmDeviceType(null);

		return dcmDevice;
	}

	/**
	 * @return the commodityRequired
	 */
	public Boolean getCommodityRequired() {
		return commodityRequired;
	}

	/**
	 * @param commodityRequired the commodityRequired to set
	 */
	public void setCommodityRequired(Boolean commodityRequired) {
		this.commodityRequired = commodityRequired;
	}

	/**
	 * @return the licenseRequired
	 */
	public Boolean getLicenseRequired() {
		return licenseRequired;
	}

	/**
	 * @param licenseRequired the licenseRequired to set
	 */
	public void setLicenseRequired(Boolean licenseRequired) {
		this.licenseRequired = licenseRequired;
	}

	/**
	 * @return the paymentRequired
	 */
	public Boolean getPaymentRequired() {
		return paymentRequired;
	}

	/**
	 * @param paymentRequired the paymentRequired to set
	 */
	public void setPaymentRequired(Boolean paymentRequired) {
		this.paymentRequired = paymentRequired;
	}

	
}