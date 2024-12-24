package com.example.parking.controller;

import com.example.parking.model.ParkingSlot;
import com.example.parking.model.Booking;
import com.example.parking.model.ParkingStatus;
import com.example.parking.service.ParkingService;
import com.example.parking.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingController {

    private final ParkingService parkingService;
    private final BookingService bookingService;

    // Constructor Injection of Services
    public ParkingController(ParkingService parkingService, BookingService bookingService) {
        this.parkingService = parkingService;
        this.bookingService = bookingService;
    }

    // Endpoint to get the current parking status
    @GetMapping("/parking/status")
    public List<ParkingStatus> getParkingStatus() {
        return parkingService.getParkingStatus();
    }

    // Endpoint to make a booking (POST request)
    @PostMapping("/parking/book")
    public String bookParkingSlot(@RequestParam String vehicleNo ,@RequestParam String vehicleType) {
         return bookingService.bookParkingSlot(vehicleNo,vehicleType);
    }

    @PostMapping("/parking/exit")
    public String exitParkingSlot(@RequestParam String vehicleNo){
        return bookingService.exitParkingSlot(vehicleNo);
    }
}
