package com.examly.springapp.DTO;

import java.time.LocalDate;

import com.examly.springapp.model.Charity;
import com.examly.springapp.model.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DonationDTO class represents a Data Transfer Object for Donation.
 * It includes fields such as donationId, donationDate, amount, user, and charity.
 * 
 * @Author - 
 */
public class DonationDTO {

    private long donationId; // Unique identifier for the donation

    private LocalDate donationDate; // Date of the donation

    @NotNull(message = "Amount is mandatory")
    @Min(value = 1, message = "Amount should be at least 1")
    private double amount; // Amount donated

    @NotNull(message = "User ID is mandatory")
    private User user; // User who made the donation

    @NotNull(message = "Charity ID is mandatory")
    private Charity charity; // Charity to which the donation was made

    /**
     * Default constructor for DonationDTO.
     */
    public DonationDTO() {
    }

    /**
     * Parameterized constructor for DonationDTO.
     * 
     * @param donationId Unique identifier for the donation.
     * @param donationDate Date of the donation.
     * @param amount Amount donated.
     * @param user User who made the donation.
     * @param charity Charity to which the donation was made.
     */
    public DonationDTO(long donationId, LocalDate donationDate, double amount, User user, Charity charity) {
        this.donationId = donationId;
        this.donationDate = donationDate;
        this.amount = amount;
        this.user = user;
        this.charity = charity;
    }

    /**
     * Gets the unique identifier for the donation.
     * 
     * @return the donation ID.
     */
    public long getDonationId() {
        return donationId;
    }

    /**
     * Sets the unique identifier for the donation.
     * 
     * @param donationId the donation ID to set.
     */
    public void setDonationId(long donationId) {
        this.donationId = donationId;
    }

    /**
     * Gets the date of the donation.
     * 
     * @return the donation date.
     */
    public LocalDate getDonationDate() {
        return donationDate;
    }

    /**
     * Sets the date of the donation.
     * 
     * @param donationDate the donation date to set.
     */
    public void setDonationDate(LocalDate donationDate) {
        this.donationDate = donationDate;
    }

    /**
     * Gets the amount donated.
     * 
     * @return the amount donated.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount donated.
     * 
     * @param amount the amount to set.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets the user who made the donation.
     * 
     * @return the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who made the donation.
     * 
     * @param user the user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the charity to which the donation was made.
     * 
     * @return the charity.
     */
    public Charity getCharity() {
        return charity;
    }

    /**
     * Sets the charity to which the donation was made.
     * 
     * @param charity the charity to set.
     */
    public void setCharity(Charity charity) {
        this.charity = charity;
    }

    /**
     * Provides a string representation of the DonationDTO object.
     * 
     * @return a string representation of the DonationDTO object.
     */
    @Override
    public String toString() {
        return "DonationDTO [donationId=" + donationId + ", donationDate=" + donationDate + ", amount=" + amount
                + ", user=" + user + ", charity=" + charity + "]";
    }

}
