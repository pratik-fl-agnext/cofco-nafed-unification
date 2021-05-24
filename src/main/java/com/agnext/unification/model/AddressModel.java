package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_EMPTY)
public class AddressModel {

	@JsonProperty("city_id")
	private Long cityId;

	@JsonProperty("state_id")
	private Long stateId;

	@JsonProperty("country_id")
	private Long countryId;
	
	@JsonProperty("custom_city_id")
	private Long customCityId;

	@JsonProperty("custom_state_id")
	private Long customStateId;

	@JsonProperty("custom_country_id")
	private Long customCountryId;

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	@Override
	public String toString() {
		return "AddressModel [cityId=" + cityId + ", stateId=" + stateId + ", countryId=" + countryId
				+ ", customCityId=" + customCityId + ", customStateId=" + customStateId + ", customCountryId="
				+ customCountryId + "]";
	}

	public Long getCustomCityId() {
		return customCityId;
	}

	public void setCustomCityId(Long customCityId) {
		this.customCityId = customCityId;
	}

	public Long getCustomStateId() {
		return customStateId;
	}

	public void setCustomStateId(Long customStateId) {
		this.customStateId = customStateId;
	}

	public Long getCustomCountryId() {
		return customCountryId;
	}

	public void setCustomCountryId(Long customCountryId) {
		this.customCountryId = customCountryId;
	}

	
}
