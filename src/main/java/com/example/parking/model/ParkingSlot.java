package com.example.parking.model;

public class ParkingSlot {
    private int id;
    private String vehicleType;
    private int totalSlots;
    private int availableSlots;

    // Default constructor
    public ParkingSlot() {
    }

    // Parameterized constructor
    public ParkingSlot(int id, String vehicleType, int totalSlots, int availableSlots) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.totalSlots = totalSlots;
        this.availableSlots = availableSlots;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
