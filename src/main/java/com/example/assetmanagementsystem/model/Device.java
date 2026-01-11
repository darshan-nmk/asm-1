package com.example.assetmanagementsystem.model;

public class Device {
    private int id;
    private String name;
    private String serialNumber;
    private Status status;

    public enum Status{
        ASSIGNED,
        AVAILABLE
    }
    public Device(){};
    public Device(int id, String name, String serialNumber) {
        this.id = id;
        this.name = name;
        this.serialNumber = serialNumber;
        this.status = Status.AVAILABLE;
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

    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
