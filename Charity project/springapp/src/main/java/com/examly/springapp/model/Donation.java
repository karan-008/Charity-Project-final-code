package com.examly.springapp.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * @author 
 * Entity class representing a Donation.
 * Annotated with `@Entity` to map the class to a database table.
 */
@Entity
public class Donation {

    /**
     * Unique identifier for a donation.
     * Annotated with `@Id` to denote the primary key.
     * Uses `@GeneratedValue` with the strategy `GenerationType.AUTO` for automatic ID generation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long donationId;

    /**
     * Date when the donation was made.
     * `LocalDate` is a class in Java that represents a date without time.
     */
    private LocalDate donationDate;

    /**
     * Amount of money donated.
     */
    private double amount;

    /**
     * User who made the donation.
     * Annotated with `@ManyToOne` to denote a many-to-one relationship with the User entity.
     * `@JoinColumn` specifies the foreign key column name in the donation table.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Charity to which the donation was made.
     * Annotated with `@ManyToOne` to denote a many-to-one relationship with the Charity entity.
     * `@JoinColumn` specifies the foreign key column name in the donation table.
     */
    @ManyToOne
    @JoinColumn(name = "charity_id", nullable = false)
    private Charity charity;

    /**
     * Default constructor for Donation.
     */
    public Donation() {
    }

    /**
     * Parameterized constructor for Donation.
     *
     * @param donationId Unique identifier for a donation.
     * @param donationDate Date when the donation was made.
     * @param amount Amount of money donated.
     * @param user User who made the donation.
     * @param charity Charity to which the donation was made.
     */
    public Donation(long donationId, LocalDate donationDate, double amount, User user, Charity charity) {
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
     * Gets the date when the donation was made.
     *
     * @return the donation date.
     */
    public LocalDate getDonationDate() {
        return donationDate;
    }

    /**
     * Sets the date when the donation was made.
     *
     * @param donationDate the donation date to set.
     */
    public void setDonationDate(LocalDate donationDate) {
        this.donationDate = donationDate;
    }

    /**
     * Gets the amount of money donated.
     *
     * @return the amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of money donated.
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
}
