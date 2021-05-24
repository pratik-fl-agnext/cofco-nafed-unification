package com.agnext.unification.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomAnalyticsModel {

	@JsonProperty("moisturecontent")
	public String moisturecontent;
	
	@JsonProperty("foreignmatter")
	public String foreignmatter;
	
	@JsonProperty("damaged")
	public String damaged;
	
	@JsonProperty("podsofothervariety")
	public String podsofothervariety;

	@JsonProperty("shrivelledAndImmature")
	public String shrivelledAndImmature;
	
	@JsonProperty("shelling")
	public String shelling;
	
	@JsonProperty("weevilled")
	public String weevilled;
	
	@JsonProperty("slightlydamaged")
	public String slightlydamaged;
	
	@JsonProperty("admixture")
	public String admixture;
	
	
	public String getMoisturecontent() {
		return moisturecontent;
	}
	public void setMoisturecontent(String moisturecontent) {
		this.moisturecontent = moisturecontent;
	}
	public String getForeignmatter() {
		return foreignmatter;
	}
	public void setForeignmatter(String foreignmatter) {
		this.foreignmatter = foreignmatter;
	}
	public String getDamaged() {
		return damaged;
	}
	public void setDamaged(String damaged) {
		this.damaged = damaged;
	}
	public String getPodsofothervariety() {
		return podsofothervariety;
	}
	public void setPodsofothervariety(String podsofothervariety) {
		this.podsofothervariety = podsofothervariety;
	}
	public String getShrivelledAndImmature() {
		return shrivelledAndImmature;
	}
	public void setShrivelledAndImmature(String shrivelledAndImmature) {
		this.shrivelledAndImmature = shrivelledAndImmature;
	}
	public String getShelling() {
		return shelling;
	}
	public void setShelling(String shelling) {
		this.shelling = shelling;
	}
	public String getWeevilled() {
		return weevilled;
	}
	public void setWeevilled(String weevilled) {
		this.weevilled = weevilled;
	}
	public String getSlightlydamaged() {
		return slightlydamaged;
	}
	public void setSlightlydamaged(String slightlydamaged) {
		this.slightlydamaged = slightlydamaged;
	}
	public String getAdmixture() {
		return admixture;
	}
	public void setAdmixture(String admixture) {
		this.admixture = admixture;
	}
	@Override
	public String toString() {
		return "CustomAnalyticsModel [moisturecontent=" + moisturecontent + ", foreignmatter=" + foreignmatter
				+ ", damaged=" + damaged + ", podsofothervariety=" + podsofothervariety + ", shrivelledAndImmature="
				+ shrivelledAndImmature + ", shelling=" + shelling + ", weevilled=" + weevilled + ", slightlydamaged="
				+ slightlydamaged + ", admixture=" + admixture + "]";
	}
	
	

}
