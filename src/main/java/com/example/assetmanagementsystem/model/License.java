package com.example.assetmanagementsystem.model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class License {
    private int id;
    private int softwareId;
    private int deviceId;
    private String licenseKey;
    private float price;
    private int validityInDays;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate activatedOn;

    public License(){}
    public License(int id, int softwareId,int deviceId,String licenseKey, float price, int validityInDays, LocalDate activatedOn) {
        this.id = id;
        this.softwareId =softwareId;
        this.deviceId = deviceId;
        this.licenseKey = licenseKey;
        this.price = price;
        this.validityInDays = validityInDays;
        this.activatedOn = activatedOn;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getSoftwareId() {
        return softwareId;
    }
    public void setSoftwareId(int softwareId) {
        this.softwareId = softwareId;
    }

    public int getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getLicenseKey() {
        return licenseKey;
    }
    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    public int getValidityInDays() {
        return validityInDays;
    }
    public void setValidityInDays(int validityInDays) {
        this.validityInDays = validityInDays;
    }

    public LocalDate getActivatedOn() {
        return activatedOn;
    }
    public void setActivatedOn(LocalDate activatedOn) {
        this.activatedOn = activatedOn;
    }
}

