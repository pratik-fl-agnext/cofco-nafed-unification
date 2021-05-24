package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlotModel {
	
	@JsonProperty("plot_id")
	private Long plotId ;
	
	@JsonProperty("latitude")
	private String latitude ;
	
	@JsonProperty("longitude")
	private String longitude;

	 

	/**
	 * @return the plotId
	 */
	public Long getPlotId() {
		return plotId;
	}

	/**
	 * @param plotId the plotId to set
	 */
	public void setPlotId(Long plotId) {
		this.plotId = plotId;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	

}