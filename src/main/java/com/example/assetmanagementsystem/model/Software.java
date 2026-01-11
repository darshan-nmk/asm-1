package com.example.assetmanagementsystem.model;

public class Software {
    private int id;
    private String name;
    private float price;
    private int validityInDays;
    private int vendorId;

    public Software() {}
    public Software(int id, String name, float price, int validityInDays, int vendorId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.validityInDays = validityInDays;
        this.vendorId = vendorId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

    public int getVendorId() {
        return vendorId;
    }
    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }
}
