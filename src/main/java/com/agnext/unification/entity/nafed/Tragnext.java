package com.agnext.unification.entity.nafed;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tragnext")
public class Tragnext implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "avgprice")
    private String avgPrice;

    @Column(name = "avgwaterpercentage")
    private String avgWaterPercentage;

    @Column(name = "avgweight")
    private String averageWeight;

    @Column(name = "boughtleaves")
    private String noOfBoughtLeaves;

    @Column(name = "delta")
    private String deltaFlcSlab;

    @Column(name = "nooftruck")
    private String noOfTruck;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(String avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getAvgWaterPercentage() {
        return avgWaterPercentage;
    }

    public void setAvgWaterPercentage(String avgWaterPercentage) {
        this.avgWaterPercentage = avgWaterPercentage;
    }

    public String getAverageWeight() {
        return averageWeight;
    }

    public void setAverageWeight(String averageWeight) {
        this.averageWeight = averageWeight;
    }

    public String getNoOfBoughtLeaves() {
        return noOfBoughtLeaves;
    }

    public void setNoOfBoughtLeaves(String noOfBoughtLeaves) {
        this.noOfBoughtLeaves = noOfBoughtLeaves;
    }

    public String getDeltaFlcSlab() {
        return deltaFlcSlab;
    }

    public void setDeltaFlcSlab(String deltaFlcSlab) {
        this.deltaFlcSlab = deltaFlcSlab;
    }

    public String getNoOfTruck() {
        return noOfTruck;
    }

    public void setNoOfTruck(String noOfTruck) {
        this.noOfTruck = noOfTruck;
    }

    @Override
    public String toString() {
        return "Tragnext{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", avgPrice='" + avgPrice + '\'' +
                ", avgWaterPercentage='" + avgWaterPercentage + '\'' +
                ", averageWeight='" + averageWeight + '\'' +
                ", noOfBoughtLeaves='" + noOfBoughtLeaves + '\'' +
                ", deltaFlcSlab='" + deltaFlcSlab + '\'' +
                ", noOfTruck='" + noOfTruck + '\'' +
                '}';
    }
}
