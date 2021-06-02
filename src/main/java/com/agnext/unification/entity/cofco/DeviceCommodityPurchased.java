package com.agnext.unification.entity.cofco;


import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "device_commodity_purchased")
public class DeviceCommodityPurchased {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "package_id")
    private Long packageId;

    @ManyToOne
    @JoinColumn(name = "commodity_id")
    private CofcoCommodityEntity commodity;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private DcmDevice device;

    @Column(name = "licence_no")
    private String licenceNo;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "status")
    private Integer status;

    @Column(name = "device_type_id")
    private Long deviceTypeId;

    @Column(name = "expired_on")
    private LocalDateTime expiredOn;

    @Column(name = "total_scan")
    private Long totalScans;

    @Column(name = "consumed_scan")
    private Long consumedScan;

    @Column(name = "renew_on")
    private LocalDateTime renewedOn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_reference_number")
    private Payment paymentReference;

    /**
     * @return the renewedOn
     */
    public LocalDateTime getRenewedOn() {
	return renewedOn;
    }

    /**
     * @param renewedOn
     *            the renewedOn to set
     */
    public void setRenewedOn(LocalDateTime renewedOn) {
	this.renewedOn = renewedOn;
    }

    /**
     * @return the expiredOn
     */
    public LocalDateTime getExpiredOn() {
	return expiredOn;
    }

    /**
     * @param expiredOn
     *            the expiredOn to set
     */
    public void setExpiredOn(LocalDateTime expiredOn) {
	this.expiredOn = expiredOn;
    }

    /**
     * @return the totalScans
     */
    public Long getTotalScans() {
	return totalScans;
    }

    /**
     * @param totalScans
     *            the totalScans to set
     */
    public void setTotalScans(Long totalScans) {
	this.totalScans = totalScans;
    }

    /**
     * @return the consumedScan
     */
    public Long getConsumedScan() {
	return consumedScan;
    }

    /**
     * @param consumedScan
     *            the consumedScan to set
     */
    public void setConsumedScan(Long consumedScan) {
	this.consumedScan = consumedScan;
    }

    /**
     * @return the deviceTypeId
     */
    public Long getDeviceTypeId() {
	return deviceTypeId;
    }

    /**
     * @param deviceTypeId
     *            the deviceTypeId to set
     */
    public void setDeviceTypeId(Long deviceTypeId) {
	this.deviceTypeId = deviceTypeId;
    }

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
     * @return the clientId
     */
    public Long getClientId() {
	return clientId;
    }

    /**
     * @param clientId
     *            the clientId to set
     */
    public void setClientId(Long clientId) {
	this.clientId = clientId;
    }

    /**
     * @return the licenceNo
     */
    public String getLicenceNo() {
	return licenceNo;
    }

    /**
     * @param licenceNo
     *            the licenceNo to set
     */
    public void setLicenceNo(String licenceNo) {
	this.licenceNo = licenceNo;
    }

    /**
     * @return the createdOn
     */
    public LocalDateTime getCreatedOn() {
	return createdOn;
    }

    /**
     * @param createdOn
     *            the createdOn to set
     */
    public void setCreatedOn(LocalDateTime createdOn) {
	this.createdOn = createdOn;
    }

    /**
     * @return the createdBy
     */
    public Long getCreatedBy() {
	return createdBy;
    }

    /**
     * @param createdBy
     *            the createdBy to set
     */
    public void setCreatedBy(Long createdBy) {
	this.createdBy = createdBy;
    }

    /**
     * @return the updatedOn
     */
    public LocalDateTime getUpdatedOn() {
	return updatedOn;
    }

    /**
     * @param updatedOn
     *            the updatedOn to set
     */
    public void setUpdatedOn(LocalDateTime updatedOn) {
	this.updatedOn = updatedOn;
    }

    /**
     * @return the updatedBy
     */
    public Long getUpdatedBy() {
	return updatedBy;
    }

    /**
     * @param updatedBy
     *            the updatedBy to set
     */
    public void setUpdatedBy(Long updatedBy) {
	this.updatedBy = updatedBy;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
	return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Integer status) {
	this.status = status;
    }

    /**
     * @return the packageId
     */
    public Long getPackageId() {
	return packageId;
    }

    /**
     * @param packageId
     *            the packageId to set
     */
    public void setPackageId(Long packageId) {
	this.packageId = packageId;
    }

    /**
     * @return the commodity
     */
    public CofcoCommodityEntity getCommodity() {
	return commodity;
    }

    /**
     * @param commodity
     *            the commodity to set
     */
    public void setCommodity(CofcoCommodityEntity commodity) {
	this.commodity = commodity;
    }

    /**
     * @return the device
     */
    public DcmDevice getDevice() {
	return device;
    }

    /**
     * @param device
     *            the device to set
     */
    public void setDevice(DcmDevice device) {
	this.device = device;
    }

    public Payment getPaymentReference() {
	return paymentReference;
    }

    public void setPaymentReference(Payment paymentReference) {
	this.paymentReference = paymentReference;
    }

}
