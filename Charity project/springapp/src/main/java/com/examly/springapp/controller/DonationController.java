package com.examly.springapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.examly.springapp.DTO.DonationDTO;
import com.examly.springapp.service.DonationService;

import jakarta.validation.Valid;

/**
 * DonationController class handles donation-related requests.
 * It includes endpoints for adding, retrieving donations, and calculating total donations.
 * 
 * @Author - 
 */
@RestController
@RequestMapping("/api/donations")
@Validated
public class DonationController {

    @Autowired
    private DonationService donationService; // Service for donation-related operations

    /**
     * Endpoint for adding a new donation.
     * Accessible only to users with the 'USER' role.
     * 
     * @param donationDTO the DonationDTO object containing donation details.
     * @return a ResponseEntity containing the added DonationDTO object with a 201 status, or a 500 status if addition fails.
     */
    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<DonationDTO> addDonation(@Valid @RequestBody DonationDTO donationDTO) {
        DonationDTO donation = donationService.addDonation(donationDTO);
        if (donation != null) {
            return ResponseEntity.status(201).body(donation);
        }
        return ResponseEntity.status(500).build();
    }

    /**
     * Endpoint for retrieving all donations.
     * Accessible only to users with the 'ADMIN' role.
     * 
     * @return a ResponseEntity containing a list of DonationDTO objects with a 200 status, or a 500 status if retrieval fails.
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<DonationDTO>> getAllDonations() {
        List<DonationDTO> donations = donationService.getAllDonations();
        if (donations != null) {
            return ResponseEntity.status(200).body(donations);
        }
        return ResponseEntity.status(500).build();
    }

    /**
     * Endpoint for retrieving all donations made by a specific user.
     * Accessible only to users with the 'USER' role.
     * 
     * @param userId the unique ID of the user.
     * @return a ResponseEntity containing a list of DonationDTO objects with a 200 status, or a 500 status if retrieval fails.
     */
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<DonationDTO>> getAllDonationsByUserId(@PathVariable Long userId) {
        List<DonationDTO> donations = donationService.getAllDonationsByUserId(userId);
        if (donations != null) {
            return ResponseEntity.status(200).body(donations);
        }
        return ResponseEntity.status(500).build();
    }

    /**
     * Endpoint for retrieving all donations made to a specific charity.
     * Accessible only to users with the 'USER' role.
     * 
     * @param charityId the unique ID of the charity.
     * @return a ResponseEntity containing a list of DonationDTO objects with a 200 status, or a 500 status if retrieval fails.
     */
    @GetMapping("/charity/{charityId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<DonationDTO>> getAllDonationsByCharityId(@PathVariable Long charityId) {
        List<DonationDTO> donations = donationService.getAllDonationsByCharityId(charityId);
        if (donations != null) {
            return ResponseEntity.status(200).body(donations);
        }
        return ResponseEntity.status(500).build();
    }

    /**
     * Endpoint for calculating the total amount of donations made to a specific charity.
     * Accessible only to users with the 'USER' role.
     * 
     * @param charityId the unique ID of the charity.
     * @return a ResponseEntity containing the total donations amount with a 200 status, or a 500 status if retrieval fails.
     */
    @GetMapping("/total/charity/{charityId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Double> getTotalDonationsByCharityId(@PathVariable Long charityId) {
        double totalDonations = donationService.getTotalDonationsByCharityId(charityId);
        if (totalDonations != 0) {
            return ResponseEntity.status(200).body(totalDonations);
        }
        return ResponseEntity.status(500).build();
    }
}
