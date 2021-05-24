package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AndroidVersionModel {

	@JsonProperty("version_name")
	private String versionName;

	@JsonProperty("link")
	private String link;

	@JsonProperty("status")
	private String status;

	@JsonProperty("created_on")
	private Long createdOn;

	@JsonProperty("valid_till")
	private Long validTill;

	@JsonProperty("force_upload")
	private boolean forceUpload;

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public Long getValidTill() {
		return validTill;
	}

	public void setValidTill(Long validTill) {
		this.validTill = validTill;
	}

	public boolean isForceUpload() {
		return forceUpload;
	}

	public void setForceUpload(boolean forceUpload) {
		this.forceUpload = forceUpload;
	}



}
