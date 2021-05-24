package com.agnext.unification.model;

import java.io.Serializable;

public class TragnextModel implements Serializable {

    private String name;
    private String email;
    private String phoneNumber;
    private TragnextParameters parameters;

    public TragnextParameters getParameters() {
        return parameters;
    }

    public void setParameters(TragnextParameters parameters) {
        this.parameters = parameters;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "TragnextModel{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
