package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhysicalScanResultVO {
	@JsonProperty("grain_count")
	private Integer grainCount;

	@JsonProperty("count_per_oz")
	private String countPerOz;

	@JsonProperty("aspect_ratio")
	private Double aspectRatio;

	private Double radius;

	private Double clean;

	private Double weevilled;

	private Double immature;

	private Double shrivelled;

	private Double broken;

	private Double damaged;

	private Double discolored;

	private Double admixture;

	@JsonProperty("foreign_matters")
	private Double foreignMatters;

	@JsonProperty("red_rice")
	private Double redRice;

	private Double chalky;

	private Double black;

	private Double brown;

	private Double green;

	private Double other;

	private Double shell;

	private String density;

	@JsonProperty("commodity_id")
	private Long commodityId;

	@JsonProperty("commodity_name")
	private String commodityName;

	@JsonProperty("reference_id")
	private String referenceId;

	@JsonProperty("bar_code")
	private String barCode;

	private Long createdOn;

	public Integer getGrainCount() {
		return grainCount;
	}

	public void setGrainCount(Integer grainCount) {
		this.grainCount = grainCount;
	}

	public String getCountPerOz() {
		return countPerOz;
	}

	public void setCountPerOz(String countPerOz) {
		this.countPerOz = countPerOz;
	}

	public Double getAspectRatio() {
		return aspectRatio;
	}

	public void setAspectRatio(Double aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}

	public Double getClean() {
		return clean;
	}

	public void setClean(Double clean) {
		this.clean = clean;
	}

	public Double getWeevilled() {
		return weevilled;
	}

	public void setWeevilled(Double weevilled) {
		this.weevilled = weevilled;
	}

	public Double getImmature() {
		return immature;
	}

	public void setImmature(Double immature) {
		this.immature = immature;
	}

	public Double getShrivelled() {
		return shrivelled;
	}

	public void setShrivelled(Double shrivelled) {
		this.shrivelled = shrivelled;
	}

	public Double getBroken() {
		return broken;
	}

	public void setBroken(Double broken) {
		this.broken = broken;
	}

	public Double getDamaged() {
		return damaged;
	}

	public void setDamaged(Double damaged) {
		this.damaged = damaged;
	}

	public Double getDiscolored() {
		return discolored;
	}

	public void setDiscolored(Double discolored) {
		this.discolored = discolored;
	}

	public Double getAdmixture() {
		return admixture;
	}

	public void setAdmixture(Double admixture) {
		this.admixture = admixture;
	}

	public Double getForeignMatters() {
		return foreignMatters;
	}

	public void setForeignMatters(Double foreignMatters) {
		this.foreignMatters = foreignMatters;
	}

	public Double getRedRice() {
		return redRice;
	}

	public void setRedRice(Double redRice) {
		this.redRice = redRice;
	}

	public Double getChalky() {
		return chalky;
	}

	public void setChalky(Double chalky) {
		this.chalky = chalky;
	}

	public Double getBlack() {
		return black;
	}

	public void setBlack(Double black) {
		this.black = black;
	}

	public Double getBrown() {
		return brown;
	}

	public void setBrown(Double brown) {
		this.brown = brown;
	}

	public Double getGreen() {
		return green;
	}

	public void setGreen(Double green) {
		this.green = green;
	}

	public Double getOther() {
		return other;
	}

	public void setOther(Double other) {
		this.other = other;
	}

	public Double getShell() {
		return shell;
	}

	public void setShell(Double shell) {
		this.shell = shell;
	}

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	public Long getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

}
