package com.agnext.unification.model;

import java.util.HashMap;
import java.util.Map;

public class CSVModel {

    Long scanId;
    String sampleId;
    String dateOfScan;
    String warehousename;
    String locationname;
    Double weight;
    String bagCount;
    String truckNumber;
    String analysis;
    Double moisturecontent;
    Double admixture;
    Double foreignmatter;
    //Double fm;
    //Double moisture;
   // Double sample_weight;
    Double shrivelledAndImmature;
    Double damaged;
    Double weevilled;
    Double damagedAndWeevilled;
    Double smallAtrophiedSeeds;
  //  Double temperature;
    Double podsofothervar;
    Double immature;
    Double slightlydamaged;
    Double shelling;
    String acceptedBag;



    String rejectedBag;
    String remarks;
    String commodity;
    String variety;
    Double otherfoodgrains;
    String approval;
    String imageId;
    ImageModel imageModel;


    public Double getSmallAtrophiedSeeds() {
        return smallAtrophiedSeeds;
    }

    public void setSmallAtrophiedSeeds(Double smallAtrophiedSeeds) {
        this.smallAtrophiedSeeds = smallAtrophiedSeeds;
    }

    public Double getDamagedAndWeevilled() {
        return damagedAndWeevilled;
    }

    public void setDamagedAndWeevilled(Double damagedAndWeevilled) {
        this.damagedAndWeevilled = damagedAndWeevilled;
    }

    public Double getShrivelledAndImmature() {
        return shrivelledAndImmature;
    }

    public void setShrivelledAndImmature(Double shrivelledAndImmature) {
        this.shrivelledAndImmature = shrivelledAndImmature;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    Long stateId;
    Boolean isValid;

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    Map<String,String> anMap = new HashMap();

    public CSVModel() {

    }

    public CSVModel(Long scanId, String sampleId, String dateOfScan, String warehousename, String locationname) {
        this.scanId = scanId;
        this.sampleId = sampleId;
        this.dateOfScan = dateOfScan;
        this.warehousename = warehousename;
        this.locationname = locationname;
    }

    public CSVModel(Long scanId, String sampleId, String dateOfScan, String warehousename, String locationname, Double weight, String bagCount) {
        this.scanId = scanId;
        this.sampleId = sampleId;
        this.dateOfScan = dateOfScan;
        this.warehousename = warehousename;
        this.locationname = locationname;
        this.weight = weight;
        this.bagCount = bagCount;
    }
    
    public Long getScanId() {
        return scanId;
    }

    public void setScanId(Long scanId) {
        this.scanId = scanId;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getDateOfScan() {
        return dateOfScan;
    }

    public void setDateOfScan(String dateOfScan) {
        this.dateOfScan = dateOfScan;
    }

    public String getWarehousename() {
        return warehousename;
    }

    public void setWarehousename(String warehousename) {
        this.warehousename = warehousename;
    }

    public String getLocationname() {
        return locationname;
    }

    public void setLocationname(String locationname) {
        this.locationname = locationname;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getBagCount() {
        return bagCount;
    }

    public void setBagCount(String bagCount) {
        this.bagCount = bagCount;
    }

    public String getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public Double getMoisturecontent() {
        return moisturecontent;
    }

    public void setMoisturecontent(Double moisturecontent) {
        this.moisturecontent = moisturecontent;
    }

    public Double getAdmixture() {
        return admixture;
    }

    public void setAdmixture(Double admixture) {
        this.admixture = admixture;
    }

    public Double getForeignmatter() {
        return foreignmatter;
    }

    public void setForeignmatter(Double foreignmatter) {
        this.foreignmatter = foreignmatter;
    }

  /*  public Double getFm() {
        return fm;
    }

    public void setFm(Double fm) {
        this.fm = fm;
    }
*/
    /*public Double getMoisture() {
        return moisture;
    }

    public void setMoisture(Double moisture) {
        this.moisture = moisture;
    }*/


    public Double getDamaged() {
        return damaged;
    }

    public void setDamaged(Double damaged) {
        this.damaged = damaged;
    }

    public Double getWeevilled() {
        return weevilled;
    }

    public void setWeevilled(Double weevilled) {
        this.weevilled = weevilled;
    }


    public Double getPodsofothervar() {
        return podsofothervar;
    }

    public void setPodsofothervar(Double podsofothervar) {
        this.podsofothervar = podsofothervar;
    }

    public Double getImmature() {
        return immature;
    }

    public void setImmature(Double immature) {
        this.immature = immature;
    }

    public Double getSlightlydamaged() {
        return slightlydamaged;
    }

    public void setSlightlydamaged(Double slightlydamaged) {
        this.slightlydamaged = slightlydamaged;
    }

    public Double getShelling() {
        return shelling;
    }

    public void setShelling(Double shelling) {
        this.shelling = shelling;
    }

    public String getAcceptedBag() {
        return acceptedBag;
    }

    public void setAcceptedBag(String acceptedBag) {
        this.acceptedBag = acceptedBag;
    }

    public String getRejectedBag() {
        return rejectedBag;
    }

    public void setRejectedBag(String rejectedBag) {
        this.rejectedBag = rejectedBag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public Double getOtherfoodgrains() {
        return otherfoodgrains;
    }

    public void setOtherfoodgrains(Double otherfoodgrains) {
        this.otherfoodgrains = otherfoodgrains;
    }

    public Map<String, String> getAnMap() {
        return anMap;
    }

    public void setAnMap(Map<String, String> anMap) {
        this.anMap = anMap;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public ImageModel getImageModel() {
        return imageModel;
    }

    public void setImageModel(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @Override
    public String toString() {
        return "CSVModel{" +
                "scanId=" + scanId +
                ", sampleId='" + sampleId + '\'' +
                ", dateOfScan='" + dateOfScan + '\'' +
                ", warehousename='" + warehousename + '\'' +
                ", locationname='" + locationname + '\'' +
                ", weight=" + weight +
                ", bagCount='" + bagCount + '\'' +
                ", truckNumber='" + truckNumber + '\'' +
                ", analysis='" + analysis + '\'' +
                ", moisturecontent=" + moisturecontent +
                ", admixture=" + admixture +
                ", foreignmatter=" + foreignmatter +
                ", fm=" + foreignmatter +
                ", shrivelledAndImmature=" + shrivelledAndImmature +
                ", damaged=" + damaged +
                ", weevilled=" + weevilled +
                ", damagedAndWeevilled=" + damagedAndWeevilled +
                ", smallAtrophiedSeeds=" + smallAtrophiedSeeds +
                ", podsofothervar=" + podsofothervar +
                ", immature=" + immature +
                ", slightlydamaged=" + slightlydamaged +
                ", shelling=" + shelling +
                ", acceptedBag='" + acceptedBag + '\'' +
                ", rejectedBag='" + rejectedBag + '\'' +
                ", remarks='" + remarks + '\'' +
                ", commodity='" + commodity + '\'' +
                ", variety='" + variety + '\'' +
                ", otherfoodgrains=" + otherfoodgrains +
                ", approval='" + approval + '\'' +
                ", stateId=" + stateId +
                ", isValid=" + isValid +
                ", anMap=" + anMap +
                '}';
    }


}
