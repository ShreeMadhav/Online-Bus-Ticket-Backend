package com.example.busbooking.controller;

import com.example.busbooking.DTO.LoginRequest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import com.example.busbooking.DTO.LoginResponse;
import com.example.busbooking.service.AuthService; // Import your AuthService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService; // Service to handle JWT generation

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        // Generate JWT token
        String token = authService.generateToken(authentication);
        
        // Create LoginResponse
        LoginResponse loginResponse = new LoginResponse(token, loginRequest.getUsername(), "Login successful");

        return ResponseEntity.ok(loginResponse);
    }
}
