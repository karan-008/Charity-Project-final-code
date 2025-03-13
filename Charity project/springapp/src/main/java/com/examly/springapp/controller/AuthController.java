package com.examly.springapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.DTO.UserDTO;
import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.DTO.LoginRequestDTO;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.service.UserServiceImpl;

import jakarta.validation.Valid;

/**
 * AuthController class handles user authentication and authorization requests.
 * It includes endpoints for user registration, login, and retrieving user details.
 * 
 * @Author - 
 */
@RestController
public class AuthController {

    @Autowired // NOSONAR
    private UserServiceImpl userServiceImpl; // Service for user-related operations

    @Autowired // NOSONAR
    private JwtUtils jwtUtils; // Utility class for handling JWT tokens

    /**
     * Endpoint for user registration.
     * 
     * @param userDTO the UserDTO object containing user details.
     * @return a ResponseEntity containing the registered UserDTO object with a 201 status, or a 500 status if registration fails.
     */
    @PostMapping("/api/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO savedUser = userServiceImpl.registerUser(userDTO);
        if (savedUser != null) {
            return ResponseEntity.status(201).body(savedUser);
        }
        return ResponseEntity.status(500).build();
    }

    /**
     * Endpoint for user login.
     * 
     * @param loginRequestDTO the LoginRequestDTO object containing login details.
     * @return a ResponseEntity containing the LoginRequestDTO object with a 200 status, or a 500 status if login fails.
     */
    @PostMapping("/api/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginRequestDTO> loginUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        LoginRequestDTO loginDTO = userServiceImpl.loginUser(loginRequestDTO);
        if (loginDTO != null) {
            String token = jwtUtils.generateToken(loginRequestDTO.getEmail(), loginRequestDTO.getRole()); // Generate JWT token
            loginDTO.setJwtToken(token);
            return ResponseEntity.status(200).body(loginDTO);
        }
        return ResponseEntity.status(500).build();
    }

    /**
     * Endpoint for retrieving all users.
     * Accessible only to users with the 'ADMIN' role.
     * 
     * @return a ResponseEntity containing a list of UserDTO objects with a 200 status, or a 500 status if retrieval fails.
     */
    @GetMapping("/api/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userServiceImpl.getAllUsers();
        if (users != null) {
            return ResponseEntity.status(200).body(users);
        }
        return ResponseEntity.status(500).build();
    }
}
