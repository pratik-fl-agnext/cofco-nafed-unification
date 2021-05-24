package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class AllAnalysisModel {
	
	@JsonProperty("grain_count")
	private Integer grainCount;
	
	@JsonProperty("detergent")
	private Boolean detergent;

	@JsonProperty("fat")
	private Double fat;

	@JsonProperty("glabridin")
	private Double glabridin;

	@JsonProperty("moisture")
	private Double moisture;

	@JsonProperty("oil")
	private Double oil;

	@JsonProperty("palm_oil")
	private Double palmOil;

	@JsonProperty("protein")
	private Double protein;

	@JsonProperty("snf")
	private Double snf;

	@JsonProperty("urea")
	private Double urea;

	@JsonProperty("one_banjhi_count")
	private Integer oneBanjhiCount;

	@JsonProperty("one_bud_count")
	private Integer oneBudCount;

	@JsonProperty("one_leaf_banjhi")
	private Integer oneLeafBanjhi;

	@JsonProperty("one_leaf_bud")
	private Integer oneLeafBud;

	@JsonProperty("one_leaf_count")
	private Integer oneLeafCount;

	@JsonProperty("quality_score")
	private Double qualityScore;

	@JsonProperty("three_leaf_bud")
	private Integer threeLeafBud;

	@JsonProperty("three_leaf_count")
	private Integer threeLeafCount;

	@JsonProperty("total_count")
	private Double totalCount;

	@JsonProperty("total_weight")
	private Double totalWeight;

	@JsonProperty("two_leaf_banjhi")
	private Integer twoLeafBanjhi;

	@JsonProperty("two_leaf_bud")
	private Integer twoLeafBud;

	@JsonProperty("two_leaf_count")
	private Integer twoLeafCount;

	@JsonProperty("admixture")
	private Double admixture;

	@JsonProperty("aspect_ratio")
	private Double aspectRatio;

	@JsonProperty("black")
	private Double black;

	@JsonProperty("broken")
	private Double broken;

	@JsonProperty("brown")
	private Double brown;

	@JsonProperty("chalky")
	private Double chalky;

	@JsonProperty("clean")
	private Double clean;

	@JsonProperty("count_per_oz")
	private String countPerOz;

	@JsonProperty("damaged")
	private Double damaged;

	@JsonProperty("density")
	private String density;

	@JsonProperty("discolored")
	private Double discolored;

	@JsonProperty("foreign_matters")
	private Double foreignMatters;

	@JsonProperty("green")
	private Double green;

	@JsonProperty("immature")
	private Double immature;

	@JsonProperty("other")
	private Double other;

	@JsonProperty("radius")
	private Double radius;

	@JsonProperty("red_rice")
	private Double redRice;

	@JsonProperty("shell")
	private Double shell;

	@JsonProperty("shrivelled")
	private Double shrivelled;

	@JsonProperty("weevilled")
	private Double weevilled;

	@JsonProperty("protein_dm")
	private Double proteinDm;

	@JsonProperty("ash_dm")
	private Double ashDm;

	@JsonProperty("starch")
	private Double starch;

	@JsonProperty("oil_dm")
	private Double oilDm;

	@JsonProperty("sol_protein")
	private Double solProtein;

	@JsonProperty("fibre")
	private Double fibre;

	@JsonProperty("ash")
	private Double ash;

	@JsonProperty("gluten")
	private Double gluten;

	@JsonProperty("amylose")
	private Double amylose;

	@JsonProperty("oil_wm")
	private Double oil_wm;

	@JsonProperty("anonymous")
	private String anonymous;

	@JsonProperty("curcumin")
	private Double curcumin;
	
	@JsonProperty("scan_id")
	private Long scanId;

	public Boolean getDetergent() {
		return detergent;
	}

	public void setDetergent(Boolean detergent) {
		this.detergent = detergent;
	}

	public Double getFat() {
		return fat;
	}

	public void setFat(Double fat) {
		this.fat = fat;
	}

	public Double getGlabridin() {
		return glabridin;
	}

	public void setGlabridin(Double glabridin) {
		this.glabridin = glabridin;
	}

	public Double getMoisture() {
		return moisture;
	}

	public void setMoisture(Double moisture) {
		this.moisture = moisture;
	}

	public Double getOil() {
		return oil;
	}

	public void setOil(Double oil) {
		this.oil = oil;
	}

	public Double getPalmOil() {
		return palmOil;
	}

	public void setPalmOil(Double palmOil) {
		this.palmOil = palmOil;
	}

	public Double getProtein() {
		return protein;
	}

	public void setProtein(Double protein) {
		this.protein = protein;
	}

	public Double getSnf() {
		return snf;
	}

	public void setSnf(Double snf) {
		this.snf = snf;
	}

	public Double getUrea() {
		return urea;
	}

	public void setUrea(Double urea) {
		this.urea = urea;
	}

	public Integer getOneBanjhiCount() {
		return oneBanjhiCount;
	}

	public void setOneBanjhiCount(Integer oneBanjhiCount) {
		this.oneBanjhiCount = oneBanjhiCount;
	}

	public Integer getOneBudCount() {
		return oneBudCount;
	}

	public void setOneBudCount(Integer oneBudCount) {
		this.oneBudCount = oneBudCount;
	}

	public Integer getOneLeafBanjhi() {
		return oneLeafBanjhi;
	}

	public void setOneLeafBanjhi(Integer oneLeafBanjhi) {
		this.oneLeafBanjhi = oneLeafBanjhi;
	}

	public Integer getOneLeafBud() {
		return oneLeafBud;
	}

	public void setOneLeafBud(Integer oneLeafBud) {
		this.oneLeafBud = oneLeafBud;
	}

	public Integer getOneLeafCount() {
		return oneLeafCount;
	}

	public void setOneLeafCount(Integer oneLeafCount) {
		this.oneLeafCount = oneLeafCount;
	}

	public Double getQualityScore() {
		return qualityScore;
	}

	public void setQualityScore(Double qualityScore) {
		this.qualityScore = qualityScore;
	}

	public Integer getThreeLeafBud() {
		return threeLeafBud;
	}

	public void setThreeLeafBud(Integer threeLeafBud) {
		this.threeLeafBud = threeLeafBud;
	}

	public Integer getThreeLeafCount() {
		return threeLeafCount;
	}

	public void setThreeLeafCount(Integer threeLeafCount) {
		this.threeLeafCount = threeLeafCount;
	}

	public Double getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Double totalCount) {
		this.totalCount = totalCount;
	}

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Integer getTwoLeafBanjhi() {
		return twoLeafBanjhi;
	}

	public void setTwoLeafBanjhi(Integer twoLeafBanjhi) {
		this.twoLeafBanjhi = twoLeafBanjhi;
	}

	public Integer getTwoLeafBud() {
		return twoLeafBud;
	}

	public void setTwoLeafBud(Integer twoLeafBud) {
		this.twoLeafBud = twoLeafBud;
	}

	public Integer getTwoLeafCount() {
		return twoLeafCount;
	}

	public void setTwoLeafCount(Integer twoLeafCount) {
		this.twoLeafCount = twoLeafCount;
	}

	public Double getAdmixture() {
		return admixture;
	}

	public void setAdmixture(Double admixture) {
		this.admixture = admixture;
	}

	public Double getAspectRatio() {
		return aspectRatio;
	}

	public void setAspectRatio(Double aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public Double getBlack() {
		return black;
	}

	public void setBlack(Double black) {
		this.black = black;
	}

	public Double getBroken() {
		return broken;
	}

	public void setBroken(Double broken) {
		this.broken = broken;
	}

	public Double getBrown() {
		return brown;
	}

	public void setBrown(Double brown) {
		this.brown = brown;
	}

	public Double getChalky() {
		return chalky;
	}

	public void setChalky(Double chalky) {
		this.chalky = chalky;
	}

	public Double getClean() {
		return clean;
	}

	public void setClean(Double clean) {
		this.clean = clean;
	}

	public String getCountPerOz() {
		return countPerOz;
	}

	public void setCountPerOz(String countPerOz) {
		this.countPerOz = countPerOz;
	}

	public Double getDamaged() {
		return damaged;
	}

	public void setDamaged(Double damaged) {
		this.damaged = damaged;
	}

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	public Double getDiscolored() {
		return discolored;
	}

	public void setDiscolored(Double discolored) {
		this.discolored = discolored;
	}

	public Double getForeignMatters() {
		return foreignMatters;
	}

	public void setForeignMatters(Double foreignMatters) {
		this.foreignMatters = foreignMatters;
	}

	public Integer getGrainCount() {
		return grainCount;
	}

	public void setGrainCount(Integer grainCount) {
		this.grainCount = grainCount;
	}

	public Double getGreen() {
		return green;
	}

	public void setGreen(Double green) {
		this.green = green;
	}

	public Double getImmature() {
		return immature;
	}

	public void setImmature(Double immature) {
		this.immature = immature;
	}

	public Double getOther() {
		return other;
	}

	public void setOther(Double other) {
		this.other = other;
	}

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}

	public Double getRedRice() {
		return redRice;
	}

	public void setRedRice(Double redRice) {
		this.redRice = redRice;
	}

	public Double getShell() {
		return shell;
	}

	public void setShell(Double shell) {
		this.shell = shell;
	}

	public Double getShrivelled() {
		return shrivelled;
	}

	public void setShrivelled(Double shrivelled) {
		this.shrivelled = shrivelled;
	}

	public Double getWeevilled() {
		return weevilled;
	}

	public void setWeevilled(Double weevilled) {
		this.weevilled = weevilled;
	}

	public Double getProteinDm() {
		return proteinDm;
	}

	public void setProteinDm(Double proteinDm) {
		this.proteinDm = proteinDm;
	}

	public Double getAshDm() {
		return ashDm;
	}

	public void setAshDm(Double ashDm) {
		this.ashDm = ashDm;
	}

	public Double getStarch() {
		return starch;
	}

	public void setStarch(Double starch) {
		this.starch = starch;
	}

	public Double getOilDm() {
		return oilDm;
	}

	public void setOilDm(Double oilDm) {
		this.oilDm = oilDm;
	}

	public Double getSolProtein() {
		return solProtein;
	}

	public void setSolProtein(Double solProtein) {
		this.solProtein = solProtein;
	}

	public Double getFibre() {
		return fibre;
	}

	public void setFibre(Double fibre) {
		this.fibre = fibre;
	}

	public Double getAsh() {
		return ash;
	}

	public void setAsh(Double ash) {
		this.ash = ash;
	}

	public Double getGluten() {
		return gluten;
	}

	public void setGluten(Double gluten) {
		this.gluten = gluten;
	}

	public Double getAmylose() {
		return amylose;
	}

	public void setAmylose(Double amylose) {
		this.amylose = amylose;
	}

	public Double getOil_wm() {
		return oil_wm;
	}

	public void setOil_wm(Double oil_wm) {
		this.oil_wm = oil_wm;
	}

	public String getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(String anonymous) {
		this.anonymous = anonymous;
	}

	public Double getCurcumin() {
		return curcumin;
	}

	public void setCurcumin(Double curcumin) {
		this.curcumin = curcumin;
	}

	public Long getScanId() {
		return scanId;
	}

	public void setScanId(Long scanId) {
		this.scanId = scanId;
	}
	
}
