package com.example.parking.repository;

import com.example.parking.model.Booking;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;

@Repository
public class BookingRepository {
    private final JdbcTemplate jdbcTemplate;

    public BookingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Save booking data (entry and exit times)
    public void save(Booking booking) {
        String sql = "INSERT INTO booking (vehicle_no , slot_id , vehicle_type, entry_time, exit_time) VALUES (?, ?, ?, ?,?)";
        jdbcTemplate.update(sql,
                booking.getVehicleNo(),
                booking.getParkingSlotId(),
                booking.getVehicleType(),
                booking.getEntryTime() != null ? Timestamp.valueOf(booking.getEntryTime()) : null,
                booking.getExitTime() != null ? Timestamp.valueOf(booking.getExitTime()) : null);
    }

    // Update exit time of the booking
    public void updateExitTime(Booking booking) {
        String sql = "UPDATE booking SET exit_time = ? WHERE vehicle_no = ?";
        jdbcTemplate.update(sql,
                Timestamp.valueOf(booking.getExitTime()),
                booking.getVehicleNo());
    }

    // Find booking by booking ID
    public Booking findById(String vehicleNo) {
        String sql = "SELECT * FROM booking WHERE vehicle_no = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{vehicleNo}, (rs, rowNum) -> {
            return new Booking(
                    rs.getInt("slot_id"), // slot_id column
                    rs.getString("vehicle_no"), // vehicle_no column
                    rs.getString("vehicle_type"), // vehicle_type column
                    rs.getTimestamp("entry_time").toLocalDateTime(), // Convert entry_time to LocalDateTime
                    rs.getTimestamp("exit_time") != null ? rs.getTimestamp("exit_time").toLocalDateTime() : null // Convert exit_time to LocalDateTime if not null
            );
        });
    }
}
