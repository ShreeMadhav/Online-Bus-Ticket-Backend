package com.example.busbooking.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.busbooking.entity.Booking;
import com.example.busbooking.repository.BookingRepository;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> findBookingsByEmail(String email) {
        return bookingRepository.findByEmail(email); // Assume you have a method to fetch bookings by email
    }

   

    // Delete a booking by ID
    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }

    // Method to get the current user's ID (example)
    private Long getCurrentUserId() {
        // Fetch the authenticated user's ID based on your security setup
        return 1L; // Replace with actual logic to get the authenticated user's ID
    }
}
