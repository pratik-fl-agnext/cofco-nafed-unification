package com.agnext.unification.model;

public class PaymentResponseModel {

	 private String referenceId;
	  private String orderId;
	  private String aggregatorStatus;
	  private Long clientId;
	  private Long createdOn;
	  private boolean status;
	  private Long modifiedOn;
	  private String bearer;
	  private Float amount;
	  private Boolean isVerified;
	  private String ccTrackingId;
	  private String ccBankRefNo;
	  private String ccPaymentInstrument;
	  private String ccPaymentMethod;

	  public String getReferenceId() {
	    return referenceId;
	  }

	  public void setReferenceId(String referenceId) {
	    this.referenceId = referenceId;
	  }

	  public String getOrderId() {
	    return orderId;
	  }

	  public void setOrderId(String orderId) {
	    this.orderId = orderId;
	  }

	  public String getAggregatorStatus() {
	    return aggregatorStatus;
	  }

	  public void setAggregatorStatus(String aggregatorStatus) {
	    this.aggregatorStatus = aggregatorStatus;
	  }

	  public Long getClientId() {
	    return clientId;
	  }

	  public void setClientId(Long clientId) {
	    this.clientId = clientId;
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

	  public String getBearer() {
	    return bearer;
	  }

	  public void setBearer(String bearer) {
	    this.bearer = bearer;
	  }

	  public Float getAmount() {
	    return amount;
	  }

	  public void setAmount(Float amount) {
	    this.amount = amount;
	  }

	  public Boolean getVerified() {
	    return isVerified;
	  }

	  public void setVerified(Boolean verified) {
	    isVerified = verified;
	  }

	  public String getCcTrackingId() {
	    return ccTrackingId;
	  }

	  public void setCcTrackingId(String ccTrackingId) {
	    this.ccTrackingId = ccTrackingId;
	  }

	  public String getCcBankRefNo() {
	    return ccBankRefNo;
	  }

	  public void setCcBankRefNo(String ccBankRefNo) {
	    this.ccBankRefNo = ccBankRefNo;
	  }

	  public String getCcPaymentInstrument() {
	    return ccPaymentInstrument;
	  }

	  public void setCcPaymentInstrument(String ccPaymentInstrument) {
	    this.ccPaymentInstrument = ccPaymentInstrument;
	  }

	  public String getCcPaymentMethod() {
	    return ccPaymentMethod;
	  }

	  public void setCcPaymentMethod(String ccPaymentMethod) {
	    this.ccPaymentMethod = ccPaymentMethod;
	  }

	  @Override
	  public String toString() {
	    return "PaymentResponseModel{" +
	        "referenceId='" + referenceId + '\'' +
	        ", orderId='" + orderId + '\'' +
	        ", aggregatorStatus='" + aggregatorStatus + '\'' +
	        ", clientId=" + clientId +
	        ", createdOn=" + createdOn +
	        ", status=" + status +
	        ", modifiedOn=" + modifiedOn +
	        ", bearer='" + bearer + '\'' +
	        ", amount=" + amount +
	        ", isVerified=" + isVerified +
	        ", ccTrackingId='" + ccTrackingId + '\'' +
	        ", ccBankRefNo='" + ccBankRefNo + '\'' +
	        ", ccPaymentInstrument='" + ccPaymentInstrument + '\'' +
	        ", ccPaymentMethod='" + ccPaymentMethod + '\'' +
	        '}';
	  }
}
