package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.DTO.DonationDTO;
import com.examly.springapp.exception.DonationNotFoundException;
import com.examly.springapp.model.Charity;
import com.examly.springapp.model.Donation;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.CharityRepo;
import com.examly.springapp.repository.DonationRepo;
import com.examly.springapp.repository.UserRepo;

/**
 * Service implementation for donation-related operations.
 * Implements the DonationService interface to provide the actual logic for managing donations.
 * 
 * @Author - 
 */
@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    private DonationRepo donationRepo; // Repository for interacting with the Donation entity

    @Autowired
    private CharityRepo charityRepo; // Repository for interacting with the Charity entity

    @Autowired
    private UserRepo userRepo; // Repository for interacting with the User entity

    /**
     * Adds a new donation.
     * 
     * @param donationDTO the DonationDTO object containing details of the donation to be added.
     * @return the added DonationDTO object.
     */
    @Override
    public DonationDTO addDonation(DonationDTO donationDTO) {
        User user = userRepo.findById(donationDTO.getUser().getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        Charity charity = charityRepo.findById(donationDTO.getCharity().getCharityId())
                .orElseThrow(() -> new IllegalArgumentException("Charity not found."));

        Donation donation = convertToEntity(donationDTO, user, charity);
        donation.setDonationDate(LocalDate.now()); // Set the current date as the donation date
        Donation savedDonation = donationRepo.save(donation);
        return convertToDTO(savedDonation);
    }

    /**
     * Retrieves a list of all donations.
     * 
     * @return a list of DonationDTO objects representing all donations.
     */
    @Override
    public List<DonationDTO> getAllDonations() {
        List<Donation> donations = donationRepo.findAll();
        List<DonationDTO> donationDTOList = new ArrayList<>();
        for (Donation donation : donations) {
            donationDTOList.add(convertToDTO(donation));
        }
        return donationDTOList;
    }

    /**
     * Retrieves a list of donations made by a specific user.
     * 
     * @param userId the unique ID of the user.
     * @return a list of DonationDTO objects representing donations made by the specified user.
     */
    @Override
    public List<DonationDTO> getAllDonationsByUserId(Long userId) {
        List<Donation> donations = donationRepo.findByUserId(userId);
        if (donations.isEmpty()) {
            throw new DonationNotFoundException("No donations found for user with ID: " + userId);
        }
        List<DonationDTO> donationDTOList = new ArrayList<>();
        for (Donation donation : donations) {
            donationDTOList.add(convertToDTO(donation));
        }
        return donationDTOList;
    }

    /**
     * Retrieves a list of donations made to a specific charity.
     * 
     * @param charityId the unique ID of the charity.
     * @return a list of DonationDTO objects representing donations made to the specified charity.
     */
    @Override
    public List<DonationDTO> getAllDonationsByCharityId(Long charityId) {
        List<Donation> donations = donationRepo.findByCharityId(charityId);
        if (donations.isEmpty()) {
            throw new DonationNotFoundException("No donations found for charity with ID: " + charityId);
        }
        List<DonationDTO> donationDTOList = new ArrayList<>();
        for (Donation donation : donations) {
            donationDTOList.add(convertToDTO(donation));
        }
        return donationDTOList;
    }

    /**
     * Calculates the total amount of donations made to a specific charity.
     * 
     * @param charityId the unique ID of the charity.
     * @return the total amount of donations made to the specified charity.
     */
    @Override
    public double getTotalDonationsByCharityId(Long charityId) {
        return donationRepo.sumTotalDonationsByCharityId(charityId).orElse(0.0);
    }

    /**
     * Converts a DonationDTO to a Donation entity.
     * 
     * @param donationDTO the DonationDTO object to be converted.
     * @param user the User entity associated with the donation.
     * @param charity the Charity entity associated with the donation.
     * @return the converted Donation entity.
     */
    private Donation convertToEntity(DonationDTO donationDTO, User user, Charity charity) {
        return new Donation(donationDTO.getDonationId(), donationDTO.getDonationDate(), donationDTO.getAmount(), user, charity);
    }

    /**
     * Converts a Donation entity to a DonationDTO.
     * 
     * @param donation the Donation entity to be converted.
     * @return the converted DonationDTO object.
     */
    private DonationDTO convertToDTO(Donation donation) {
        return new DonationDTO(donation.getDonationId(), donation.getDonationDate(), donation.getAmount(), donation.getUser(), donation.getCharity());
    }
}
