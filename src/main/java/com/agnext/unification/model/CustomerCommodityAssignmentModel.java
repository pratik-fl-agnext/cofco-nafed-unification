package com.agnext.unification.model;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class CustomerCommodityAssignmentModel {

	@JsonProperty("commodity_category_id")
	private Long[] commodityCategoryId;

	@JsonProperty("customer_id")
	private Long customerId;

	@JsonProperty("product_id")
	private Long productId;

	@JsonProperty("start_of_subscription")
	private Long startOfSubscription;

	@JsonProperty("end_of_subscription")
	private Long endOfSubscription;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long[] getCommodityCategoryId() {
		return commodityCategoryId;
	}

	public void setCommodityCategoryId(Long[] commodityCategoryId) {
		this.commodityCategoryId = commodityCategoryId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getStartOfSubscription() {
		return startOfSubscription;
	}

	public void setStartOfSubscription(Long startOfSubscription) {
		this.startOfSubscription = startOfSubscription;
	}

	public Long getEndOfSubscription() {
		return endOfSubscription;
	}

	public void setEndOfSubscription(Long endOfSubscription) {
		this.endOfSubscription = endOfSubscription;
	}

	@Override
	public String toString() {
		return "CustomerCommodityAssignmentModel [commodityCategoryId=" + Arrays.toString(commodityCategoryId)
				+ ", customerId=" + customerId + ", productId=" + productId + ", startOfSubscription="
				+ startOfSubscription + ", endOfSubscription=" + endOfSubscription + "]";
	}

}
