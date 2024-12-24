package com.example.parking.service;

import com.example.parking.model.Booking;
import com.example.parking.model.ParkingSlot;
import com.example.parking.repository.BookingRepository;
import com.example.parking.repository.ParkingSlotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BookingService {

    private final ParkingSlotRepository parkingSlotRepository;
    private final BookingRepository bookingRepository;

    public BookingService(ParkingSlotRepository parkingSlotRepository, BookingRepository bookingRepository) {
        this.parkingSlotRepository = parkingSlotRepository;
        this.bookingRepository = bookingRepository;
    }

    // Method to book a parking slot
    @Transactional
    public String bookParkingSlot(String vehicleNo , String vehicleType) {
        int  vehicleslot = findSlot(vehicleType);
        ParkingSlot parkingSlot = parkingSlotRepository.findById(vehicleslot);

        // Check if parking slot exists and if there are available slots
        if (parkingSlot != null && parkingSlot.getAvailableSlots() > 0) {
            // Decrease available slot count
            parkingSlot.setAvailableSlots(parkingSlot.getAvailableSlots() - 1);
            parkingSlotRepository.save(parkingSlot);

            // Create a booking for the parking slot
            LocalDateTime entryTime = LocalDateTime.now();
            Booking booking = new Booking(parkingSlot.getId(), vehicleNo, vehicleType, entryTime, null);
            bookingRepository.save(booking);

            return "Booking successful! Entry Time: " + entryTime;
        } else {
            return "No available parking slots for " + vehicleType;
        }
    }

    private int findSlot(String vehicleType) {
        if(vehicleType.equals("Bike"))return 2;
        else if(vehicleType.equals("Car"))return 1;
        else return 3;
    }

    // Method to handle vehicle exit and update the exit time
    public String exitParkingSlot(String vehicleNo) {
        Booking booking = bookingRepository.findById(vehicleNo);

        // Check if the booking exists and if the vehicle hasn't already exited
        if (booking != null && booking.getExitTime() == null) {
            // Set exit time for the booking
            LocalDateTime exitTime = LocalDateTime.now();
            booking.setExitTime(exitTime);
            bookingRepository.updateExitTime(booking);

            // Free up the parking slot
            ParkingSlot parkingSlot = parkingSlotRepository.findById(booking.getParkingSlotId());
            parkingSlot.setAvailableSlots(parkingSlot.getAvailableSlots() + 1);
            parkingSlotRepository.save(parkingSlot);

            return "Exit successful! Exit Time: " + exitTime;
        } else {
            return "Booking not found or already exited.";
        }
    }
}
