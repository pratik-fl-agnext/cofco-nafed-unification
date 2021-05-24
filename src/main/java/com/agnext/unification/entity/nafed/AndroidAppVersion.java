package com.agnext.unification.entity.nafed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the AndroidAppVersion database table.
 * 
 */
@Entity
@Table(name="android_app_version")
@NamedQuery(name="AndroidAppVersion.findAll", query="SELECT a FROM AndroidAppVersion a")
public class AndroidAppVersion {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="version_name")
	private String versionName;

	@Column(name="created_on")
	private Long createdOn;
	
	@Column(name="valid_till")
	private Long validTill;
	
	@Column(name="link")
	private String link;
	
	@Column(name="status")
	private Long status;
	
	@Column(name="force_update_flag")
	private boolean forceUpdateFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	


	@Override
	public String toString() {
		return "AndroidAppVersion [id=" + id + ", versionName=" + versionName + ", createdOn=" + createdOn
				+ ", validTill=" + validTill + ", link=" + link + ", status=" + status + ", forceUpdateFlag="
				+ forceUpdateFlag + "]";
	}

	public boolean isForceUpdateFlag() {
		return forceUpdateFlag;
	}

	public void setForceUpdateFlag(boolean forceUpdateFlag) {
		this.forceUpdateFlag = forceUpdateFlag;
	}
	
	
	
	
}
