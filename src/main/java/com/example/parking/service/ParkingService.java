package com.example.parking.service;

import com.example.parking.model.ParkingSlot;
import com.example.parking.model.ParkingStatus;
import com.example.parking.repository.ParkingSlotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private final ParkingSlotRepository parkingSlotRepository;

    public ParkingService(ParkingSlotRepository parkingSlotRepository) {
        this.parkingSlotRepository = parkingSlotRepository;
    }

    // Method to get the parking status (Total slots vs Available slots)
    public List<ParkingStatus> getParkingStatus() {
        List<ParkingSlot> parkingSlots = parkingSlotRepository.findAll();
        return parkingSlots.stream()
                .map(slot -> new ParkingStatus(slot.getVehicleType(), slot.getTotalSlots(), slot.getAvailableSlots()))
                .collect(Collectors.toList());
    }

    // You can use this method for parking slot booking as well (as we discussed earlier)
    // Additional booking methods as before...
}
