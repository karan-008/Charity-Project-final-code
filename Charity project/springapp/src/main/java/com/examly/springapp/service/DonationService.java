package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.DTO.DonationDTO;

/**
 * DonationService interface provides the contract for donation-related operations.
 * It includes methods for adding, retrieving, and calculating donations.
 * 
 * @Author - 
 */
public interface DonationService {

    /**
     * Adds a new donation.
     * 
     * @param donationDTO the DonationDTO object containing details of the donation to be added.
     * @return the added DonationDTO object.
     */
    DonationDTO addDonation(DonationDTO donationDTO);

    /**
     * Retrieves a list of all donations.
     * 
     * @return a list of DonationDTO objects representing all donations.
     */
    List<DonationDTO> getAllDonations();

    /**
     * Retrieves a list of donations made by a specific user.
     * 
     * @param userId the unique ID of the user.
     * @return a list of DonationDTO objects representing donations made by the specified user.
     */
    List<DonationDTO> getAllDonationsByUserId(Long userId);

    /**
     * Retrieves a list of donations made to a specific charity.
     * 
     * @param charityId the unique ID of the charity.
     * @return a list of DonationDTO objects representing donations made to the specified charity.
     */
    List<DonationDTO> getAllDonationsByCharityId(Long charityId);

    /**
     * Calculates the total amount of donations made to a specific charity.
     * 
     * @param charityId the unique ID of the charity.
     * @return the total amount of donations made to the specified charity.
     */
    double getTotalDonationsByCharityId(Long charityId);
}
