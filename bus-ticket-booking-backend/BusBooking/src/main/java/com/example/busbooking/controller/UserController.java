package com.example.busbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.example.busbooking.DTO.LoginRequest;
import com.example.busbooking.DTO.LoginResponse;

import com.example.busbooking.entity.User;
import com.example.busbooking.service.AuthService;
import com.example.busbooking.service.UserService;

@RestController
@RequestMapping("/api") 
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	 @Autowired
	    private UserService userService; // Inject UserService

	    @Autowired
	    private AuthService authService; // Inject AuthService

	    @Autowired
	    private AuthenticationManager authenticationManager; // Inject AuthenticationManager

	    // User registration endpoint
	    @PostMapping("/register")
	    public ResponseEntity<?> registerUser(@RequestBody User user) {
	        try {
	            userService.registerUser(user.getEmail(), user.getPassword(), user.getPhone(), user.getUsername());
	            return ResponseEntity.ok("User registered successfully");
	        } catch (Exception e) {
	            // Log the error message
	            System.err.println("Registration error: " + e.getMessage());
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
	        }
	    }
	     
	    // User login endpoint
	    @PostMapping("/login")
	    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
	        try {
	            User user = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
	            return ResponseEntity.ok(user);
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials");
	        }
	    }

}
	  