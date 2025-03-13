package com.examly.springapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.DTO.CharityDTO;
import com.examly.springapp.service.CharityServiceImpl;
import com.examly.springapp.service.DonationServiceImpl;

import jakarta.validation.Valid;

/**
 * CharityController class handles charity-related requests.
 * It includes endpoints for adding, retrieving, editing, and deleting charity details,
 * as well as retrieving total donations for a charity.
 * 
 * @Author - 
 */
@RestController
@RequestMapping("/api/charities")
public class CharityController {

    @Autowired
    private CharityServiceImpl charityServiceImpl; // Service for charity-related operations

    @Autowired
    private DonationServiceImpl donationServiceImpl; // Service for donation-related operations

    /**
     * Endpoint for adding a new charity.
     * Accessible only to users with the 'ADMIN' role.
     * 
     * @param charityDTO the CharityDTO object containing charity details.
     * @return a ResponseEntity containing the added CharityDTO object with a 201 status, or a 500 status if addition fails.
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CharityDTO> addCharity(@Valid @RequestBody CharityDTO charityDTO) {
        CharityDTO savedCharity = charityServiceImpl.addCharity(charityDTO);
        if (savedCharity != null) {
            return ResponseEntity.status(201).body(savedCharity);
        }
        return ResponseEntity.status(500).build();
    }

    /**
     * Endpoint for retrieving all charities.
     * Accessible to users with 'ADMIN' and 'USER' roles.
     * 
     * @return a ResponseEntity containing a list of CharityDTO objects with a 200 status, or a 500 status if retrieval fails.
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<List<CharityDTO>> getAllCharities() {
        List<CharityDTO> charities = charityServiceImpl.getAllCharities();
        if (charities != null) {
            return ResponseEntity.status(200).body(charities);
        }
        return ResponseEntity.status(500).build();
    }

    /**
     * Endpoint for retrieving a charity by its unique ID.
     * Accessible only to users with the 'ADMIN' role.
     * 
     * @param charityId the unique ID of the charity.
     * @return a ResponseEntity containing the CharityDTO object with a 200 status, or a 500 status if retrieval fails.
     */
    @GetMapping("/{charityId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CharityDTO> getCharityById(@PathVariable Long charityId) {
        CharityDTO charity = charityServiceImpl.getAllCharitiesBycharityId(charityId);
        if (charity != null) {
            return ResponseEntity.status(200).body(charity);
        }
        return ResponseEntity.status(500).build();
    }

    /**
     * Endpoint for editing an existing charity.
     * Accessible only to users with the 'ADMIN' role.
     * 
     * @param charityId the unique ID of the charity to be edited.
     * @param updatedCharityDTO the CharityDTO object containing updated details of the charity.
     * @return a ResponseEntity containing the updated CharityDTO object with a 200 status, or a 500 status if editing fails.
     */
    @PutMapping("/{charityId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CharityDTO> editCharity(@PathVariable Long charityId, @Valid @RequestBody CharityDTO updatedCharityDTO) {
        CharityDTO updatedCharity = charityServiceImpl.editCharity(charityId, updatedCharityDTO);
        if (updatedCharity != null) {
            return ResponseEntity.status(200).body(updatedCharity);
        }
        return ResponseEntity.status(500).build();
    }

    /**
     * Endpoint for deleting a charity by its unique ID.
     * Accessible only to users with the 'ADMIN' role.
     * 
     * @param charityId the unique ID of the charity to be deleted.
     * @return a ResponseEntity with a 200 status and a success message if deletion succeeds, or a 500 status if deletion fails.
     */
    @DeleteMapping("/{charityId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteCharity(@PathVariable Long charityId) {
        boolean charity = charityServiceImpl.deleteCharity(charityId);
        if (charity) {
            return ResponseEntity.status(200).body("Deleted Successfully!");
        }
        return ResponseEntity.status(500).build();
    }

    /**
     * Endpoint for retrieving the total amount of donations made to a specific charity.
     * Accessible only to users with the 'ADMIN' role.
     * 
     * @param charityId the unique ID of the charity.
     * @return a ResponseEntity containing the total donations amount with a 200 status, or a 500 status if retrieval fails.
     */
    @GetMapping("/{charityId}/total-donations")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Double> getTotalDonationsByCharityId(@PathVariable Long charityId) {
        double totalDonations = donationServiceImpl.getTotalDonationsByCharityId(charityId);
        if (totalDonations != 0) {
            return ResponseEntity.status(200).body(totalDonations);
        }
        return ResponseEntity.status(500).build();
    }
}
