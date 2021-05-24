package com.agnext.unification.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Model class for Analysis Data Service
 *
 */
public class StateAnalysisDataModel implements Serializable {

    Long stateId;
    String state;
    List<CSVModel> csvList = new ArrayList<>();
    List<BigDecimal> totalWeightList = new ArrayList<>();
    List<BigDecimal> acceptedWeightList = new ArrayList<>();
    List<BigDecimal> rejectedWeightList = new ArrayList<>();
    Map<String, Integer> truckCount = new HashMap<>();
    Map<String, Integer> acceptedTruckCount = new HashMap<>();
    Map<String, Integer> rejectedTruckCount = new HashMap<>();
    List<BigDecimal> moistureList = new ArrayList<>();
    List<BigDecimal> fmList = new ArrayList<>();
    List<BigDecimal> adMixtureList = new ArrayList();
    List<BigDecimal> damagedList = new ArrayList();
    List<BigDecimal> wevilledList = new ArrayList();
    List<BigDecimal> shrivelledList = new ArrayList();
    List<BigDecimal> slightlyDamagedList = new ArrayList();
    List<BigDecimal> podsOfOtherVarietyList = new ArrayList<>();
    List<BigDecimal> shellingList = new ArrayList<>();
    List<BigDecimal> damagedAndWevilledList = new ArrayList<>();
    List<BigDecimal> immatureList = new ArrayList<>();
    List<BigDecimal> otherFoodGrainsList = new ArrayList<>();
    List<BigDecimal> smallAtrophoeSeeds = new ArrayList<>();

    public Map<String, Integer> getTruckCount() {
        return truckCount;
    }

    public void setTruckCount(Map<String, Integer> truckCount) {
        this.truckCount = truckCount;
    }

    public Map<String, Integer> getAcceptedTruckCount() {
        return acceptedTruckCount;
    }

    public void setAcceptedTruckCount(Map<String, Integer> acceptedTruckCount) {
        this.acceptedTruckCount = acceptedTruckCount;
    }

    public Map<String, Integer> getRejectedTruckCount() {
        return rejectedTruckCount;
    }

    public void setRejectedTruckCount(Map<String, Integer> rejectedTruckCount) {
        this.rejectedTruckCount = rejectedTruckCount;
    }


    public List<CSVModel> getCsvList() {
        return csvList;
    }

    public void setCsvList(List<CSVModel> csvList) {
        this.csvList = csvList;
    }


    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<BigDecimal> getTotalWeightList() {
        return totalWeightList;
    }

    public void setTotalWeightList(List<BigDecimal> totalWeightList) {
        this.totalWeightList = totalWeightList;
    }

    public List<BigDecimal> getAcceptedWeightList() {
        return acceptedWeightList;
    }

    public void setAcceptedWeightList(List<BigDecimal> acceptedWeightList) {
        this.acceptedWeightList = acceptedWeightList;
    }

    public List<BigDecimal> getRejectedWeightList() {
        return rejectedWeightList;
    }

    public void setRejectedWeightList(List<BigDecimal> rejectedWeightList) {
        this.rejectedWeightList = rejectedWeightList;
    }

    public List<BigDecimal> getMoistureList() {
        return moistureList;
    }

    public void setMoistureList(List<BigDecimal> moistureList) {
        this.moistureList = moistureList;
    }

    public List<BigDecimal> getFmList() {
        return fmList;
    }

    public void setFmList(List<BigDecimal> fmList) {
        this.fmList = fmList;
    }

    public List<BigDecimal> getAdMixtureList() {
        return adMixtureList;
    }

    public void setAdMixtureList(List<BigDecimal> adMixtureList) {
        this.adMixtureList = adMixtureList;
    }

    public List<BigDecimal> getDamagedList() {
        return damagedList;
    }

    public void setDamagedList(List<BigDecimal> damagedList) {
        this.damagedList = damagedList;
    }

    public List<BigDecimal> getWevilledList() {
        return wevilledList;
    }

    public void setWevilledList(List<BigDecimal> wevilledList) {
        this.wevilledList = wevilledList;
    }

    public List<BigDecimal> getShrivelledList() {
        return shrivelledList;
    }

    public void setShrivelledList(List<BigDecimal> shrivelledList) {
        this.shrivelledList = shrivelledList;
    }

    public List<BigDecimal> getSlightlyDamagedList() {
        return slightlyDamagedList;
    }

    public void setSlightlyDamagedList(List<BigDecimal> slightlyDamagedList) {
        this.slightlyDamagedList = slightlyDamagedList;
    }

    public List<BigDecimal> getPodsOfOtherVarietyList() {
        return podsOfOtherVarietyList;
    }

    public void setPodsOfOtherVarietyList(List<BigDecimal> podsOfOtherVarietyList) {
        this.podsOfOtherVarietyList = podsOfOtherVarietyList;
    }

    public List<BigDecimal> getShellingList() {
        return shellingList;
    }

    public void setShellingList(List<BigDecimal> shellingList) {
        this.shellingList = shellingList;
    }

    public List<BigDecimal> getDamagedAndWevilledList() {
        return damagedAndWevilledList;
    }

    public void setDamagedAndWevilledList(List<BigDecimal> damagedAndWevilledList) {
        this.damagedAndWevilledList = damagedAndWevilledList;
    }

    public List<BigDecimal> getImmatureList() {
        return immatureList;
    }

    public void setImmatureList(List<BigDecimal> immatureList) {
        this.immatureList = immatureList;
    }

    public List<BigDecimal> getOtherFoodGrainsList() {
        return otherFoodGrainsList;
    }

    public void setOtherFoodGrainsList(List<BigDecimal> otherFoodGrainsList) {
        this.otherFoodGrainsList = otherFoodGrainsList;
    }

    public List<BigDecimal> getSmallAtrophoeSeeds() {
        return smallAtrophoeSeeds;
    }

    public void setSmallAtrophoeSeeds(List<BigDecimal> smallAtrophoeSeeds) {
        this.smallAtrophoeSeeds = smallAtrophoeSeeds;
    }

    @Override
    public String toString() {
        return "StateData{" +
                "stateId=" + stateId +
                ", state='" + state + '\'' +
                ", csvList=" + csvList +
                ", totalWeightList=" + totalWeightList +
                ", acceptedWeightList=" + acceptedWeightList +
                ", rejectedWeightList=" + rejectedWeightList +
                ", truckCount=" + truckCount +
                ", acceptedTruckCount=" + acceptedTruckCount +
                ", rejectedTruckCount=" + rejectedTruckCount +
                ", moistureList=" + moistureList +
                ", fmList=" + fmList +
                ", adMixtureList=" + adMixtureList +
                ", damagedList=" + damagedList +
                ", wevilledList=" + wevilledList +
                ", shrivelledList=" + shrivelledList +
                ", slightlyDamagedList=" + slightlyDamagedList +
                ", podsOfOtherVarietyList=" + podsOfOtherVarietyList +
                ", shellingList=" + shellingList +
                ", damagedAndWevilledList=" + damagedAndWevilledList +
                ", immatureList=" + immatureList +
                ", otherFoodGrainsList=" + otherFoodGrainsList +
                ", smallAtrophoeSeeds=" + smallAtrophoeSeeds +
                '}';
    }
}

