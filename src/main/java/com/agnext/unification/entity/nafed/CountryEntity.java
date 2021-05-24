package com.agnext.unification.entity.nafed;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@Table(name="countries")
public class CountryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="sortname")
	private String sortName;
	
	@Column(name="name")
	private String name;
	
	@Column(name="phonecode")
	private int phoneCode;

	 

	public CountryEntity() {
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}



	/**
	 * @return the sortName
	 */
	public String getSortName() {
		return sortName;
	}



	/**
	 * @param sortName the sortName to set
	 */
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the phoneCode
	 */
	public int getPhoneCode() {
		return phoneCode;
	}



	/**
	 * @param phoneCode the phoneCode to set
	 */
	public void setPhoneCode(int phoneCode) {
		this.phoneCode = phoneCode;
	}

	 
}