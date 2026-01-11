package com.example.assetmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class DeviceAssignment {
    private int deviceId;
    private int employeeId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate assignedFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate assignedTill;
    public DeviceAssignment() {}
    public DeviceAssignment(int deviceId, int employeeId, LocalDate assignedFrom) {
        this.deviceId = deviceId;
        this.employeeId = employeeId;
        this.assignedFrom = assignedFrom;
    }

    public int getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getAssignedFrom() {
        return assignedFrom;
    }
    public void setAssignedFrom(LocalDate assignedFrom) {
        this.assignedFrom = assignedFrom;
    }

    public LocalDate getAssignedTill() {
        return assignedTill;
    }
    public void setAssignedTill(LocalDate assignedTill) {
        this.assignedTill = assignedTill;
    }
}
