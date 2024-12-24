package com.example.parking.repository;

import com.example.parking.model.ParkingSlot;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParkingSlotRepository {
    private final JdbcTemplate jdbcTemplate;

    public ParkingSlotRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Find parking slot by ID
    public ParkingSlot findById(int id) {
        String sql = "SELECT * FROM parking_slot WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new ParkingSlot(
                rs.getInt("id"),
                rs.getString("vehicle_type"),
                rs.getInt("total_slots"),
                rs.getInt("available_slots")
        ));
    }

    public List<ParkingSlot> findAll() {
        // SQL query to fetch all parking slots
        String sql = "SELECT id, vehicle_type, total_slots, available_slots FROM parking_slot";

        // RowMapper to map each row of the result set to ParkingSlot object
        RowMapper<ParkingSlot> rowMapper = (rs, rowNum) -> {
            int id = rs.getInt("id");
            String vehicleType = rs.getString("vehicle_type");
            int totalSlots = rs.getInt("total_slots");
            int availableSlots = rs.getInt("available_slots");
            return new ParkingSlot(id, vehicleType, totalSlots, availableSlots);
        };

        // Query execution and return the list of ParkingSlot objects
        try {
            return jdbcTemplate.query(sql, rowMapper);
        } catch (Exception e) {
            Throwable cause = e.getCause();
            cause.printStackTrace(); // Look for the actual exception here
             }

        return jdbcTemplate.query(sql, rowMapper);
    }
    // Save or update parking slot data
    public void save(ParkingSlot parkingSlot) {
        String sql = "UPDATE parking_slot SET available_slots = ? WHERE id = ?";
        jdbcTemplate.update(sql, parkingSlot.getAvailableSlots(), parkingSlot.getId());
    }
}
