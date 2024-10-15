package com.example.busbooking.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.busbooking.entity.User;
import com.example.busbooking.repository.UserRepository;

@Service
public class UserService {
	 @Autowired
	    private UserRepository userRepository; // Inject UserRepository

	    @Autowired
	    private PasswordEncoder passwordEncoder; // Inject password encoder

	    public User registerUser(String email, String password, String phone, String username) {
	        // Check if the user already exists
	        if (userRepository.findByEmail(email).isPresent()) {
	            throw new IllegalArgumentException("User with this email already exists");
	        }

	        // Create a new User object
	        User user = new User();
	        user.setEmail(email);
	        user.setPassword(passwordEncoder.encode(password)); // Encode the password
	        user.setPhone(phone);
	        user.setUsername(username);
	        
	        // Save user to database
	        User savedUser = userRepository.save(user);
	        System.out.println("User saved: " + savedUser);
	        return userRepository.save(user);
	    }
	    
	    public User loginUser(String email, String password) {
	        User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid email"));

	        if (!passwordEncoder.matches(password, user.getPassword())) {
	            throw new IllegalArgumentException("Invalid password");
	        }
	        return user;
	    }

}
