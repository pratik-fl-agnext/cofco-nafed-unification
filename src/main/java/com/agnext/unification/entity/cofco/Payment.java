package com.agnext.unification.entity.cofco;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "reference_id")
    private String referenceId;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "created_on")
    private Long createdOn;

    @Column(name = "status")
    private boolean status;

    @Column(name = "modified_on")
    private Long modifiedOn;

    @Column(name = "mode")
    private String mode;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "tracking_id")
    private String trackingId;

    @Column(name = "bankref_id")
    private String bankRefId;

    @Column(name = "licence_id")
    private String licenceId;

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Long modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getBankRefId() {
        return bankRefId;
    }

    public void setBankRefId(String bankRefId) {
        this.bankRefId = bankRefId;
    }

    public String getLicenceId() {
        return licenceId;
    }

    public void setLicenceId(String licenceId) {
        this.licenceId = licenceId;
    }

	@Override
	public String toString() {
		return "Payment [referenceId=" + referenceId + ", createdBy=" + createdBy + ", customerId=" + customerId
				+ ", userId=" + userId + ", createdOn=" + createdOn + ", status=" + status + ", modifiedOn="
				+ modifiedOn + ", mode=" + mode + ", paymentStatus=" + paymentStatus + ", orderId=" + orderId
				+ ", trackingId=" + trackingId + ", bankRefId=" + bankRefId + ", licenceId=" + licenceId + "]";
	}

   
}
