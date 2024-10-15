package com.example.busbooking.repository;

import com.example.busbooking.entity.Booking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	  List<Booking> findByEmail(String email);
}
