package com.agnext.unification.model;

import java.io.Serializable;

public class TragnextParameters implements Serializable {

    private String avgWeightOfTruck;
    private String noOfTrucksPerDay;
    private String percentageOfBoughtLeaves;
    private String deltaBetweenFLCSlab;
    private String avgPriceOfFlc;
    private String avgPercentageOfWater;

    public String getAvgWeightOfTruck() {
        return avgWeightOfTruck;
    }

    public void setAvgWeightOfTruck(String avgWeightOfTruck) {
        this.avgWeightOfTruck = avgWeightOfTruck;
    }

    public String getNoOfTrucksPerDay() {
        return noOfTrucksPerDay;
    }

    public void setNoOfTrucksPerDay(String noOfTrucksPerDay) {
        this.noOfTrucksPerDay = noOfTrucksPerDay;
    }

    public String getPercentageOfBoughtLeaves() {
        return percentageOfBoughtLeaves;
    }

    public void setPercentageOfBoughtLeaves(String percentageOfBoughtLeaves) {
        this.percentageOfBoughtLeaves = percentageOfBoughtLeaves;
    }

    public String getDeltaBetweenFLCSlab() {
        return deltaBetweenFLCSlab;
    }

    public void setDeltaBetweenFLCSlab(String deltaBetweenFLCSlab) {
        this.deltaBetweenFLCSlab = deltaBetweenFLCSlab;
    }

    public String getAvgPriceOfFlc() {
        return avgPriceOfFlc;
    }

    public void setAvgPriceOfFlc(String avgPriceOfFlc) {
        this.avgPriceOfFlc = avgPriceOfFlc;
    }

    public String getAvgPercentageOfWater() {
        return avgPercentageOfWater;
    }

    public void setAvgPercentageOfWater(String avgPercentageOfWater) {
        this.avgPercentageOfWater = avgPercentageOfWater;
    }

    @Override
    public String toString() {
        return "TragnextParameters{" +
                "avgWeightOfTruck='" + avgWeightOfTruck + '\'' +
                ", noOfTrucksPerDay='" + noOfTrucksPerDay + '\'' +
                ", percentageOfBoughtLeaves='" + percentageOfBoughtLeaves + '\'' +
                ", deltaBetweenFLCSlab='" + deltaBetweenFLCSlab + '\'' +
                ", avgPriceOfFlc='" + avgPriceOfFlc + '\'' +
                ", avgPercentageOfWater='" + avgPercentageOfWater + '\'' +
                '}';
    }
}
