package com.agnext.unification.entity.nafed;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the state database table.
 * 
 */
@Entity
@Table(name="plot")
public class PlotEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="created_on")
	private Long createdOn;
	
	@Column(name="lang")
	private String lang;
	
	@Column(name="lat")
	private String lat;
	

	 

	public PlotEntity() {
	}




	public Long getId() {
	    return id;
	}




	public void setId(Long id) {
	    this.id = id;
	}




	public Long getCreatedOn() {
	    return createdOn;
	}




	public void setCreatedOn(Long createdOn) {
	    this.createdOn = createdOn;
	}




	public String getLang() {
	    return lang;
	}




	public void setLang(String lang) {
	    this.lang = lang;
	}




	public String getLat() {
	    return lat;
	}




	public void setLat(String lat) {
	    this.lat = lat;
	}

	

}