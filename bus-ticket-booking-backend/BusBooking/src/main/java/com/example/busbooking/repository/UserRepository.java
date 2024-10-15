package com.example.busbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.busbooking.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // You can define custom query methods here if needed
	Optional<User> findByEmail(String email);
	
	 User findByUsername(String username);// Example method to find a user by username
	boolean existsByEmail(String email);
}

