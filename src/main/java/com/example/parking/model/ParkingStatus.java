package com.example.parking.model;

public class ParkingStatus {

    private String vehicleType;
    private int totalSlots;
    private int availableSlots;

    public ParkingStatus(String vehicleType, int totalSlots, int availableSlots) {
        this.vehicleType = vehicleType;
        this.totalSlots = totalSlots;
        this.availableSlots = availableSlots;
    }

    // Getters and setters
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }
}
