package com.example.busbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.busbooking.entity.Booking;
import com.example.busbooking.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from your React frontend
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Get booking history for a specific user (based on userId)
    @GetMapping("/history/email/{email}")
    public ResponseEntity<List<Booking>> getBookingHistoryByEmail(@PathVariable String email) {
        List<Booking> bookings = bookingService.findBookingsByEmail(email);
        return ResponseEntity.ok(bookings);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBookingById(id);
        return ResponseEntity.noContent().build();
    }
}

