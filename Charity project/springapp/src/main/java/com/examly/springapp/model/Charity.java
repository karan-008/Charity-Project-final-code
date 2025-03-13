package com.examly.springapp.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * @author 
 * Entity class representing a Charity.
 * Annotated with `@Entity` to map the class to a database table.
 */
@Entity
public class Charity {

    /**
     * Unique identifier for a charity.
     * Annotated with `@Id` to denote the primary key.
     * Uses `@GeneratedValue` with the strategy `GenerationType.AUTO` for automatic ID generation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long charityId;

    private String charityName;    // Name of the charity
    private String founder;        // Founder of the charity
    private String description;    // Description of the charity
    private String status;         // Status of the charity (e.g., active, inactive)

    /**
     * Date when the charity was created.
     * `LocalDate` is a class in Java that represents a date without time.
     */
    private LocalDate creationDate;

    /**
     * Field to indicate if the charity is active.
     * Used for soft delete functionality, where a record is marked as inactive instead of being deleted from the database.
     */
    private boolean isActive;

    /**
     * Default constructor for Charity.
     */
    public Charity() {
    }

    /**
     * Parameterized constructor for Charity.
     *
     * @param charityId Unique identifier for a charity.
     * @param charityName Name of the charity.
     * @param founder Founder of the charity.
     * @param description Description of the charity.
     * @param status Status of the charity.
     * @param creationDate Date when the charity was created.
     * @param isActive Indicates if the charity is active.
     */
    public Charity(Long charityId, String charityName, String founder, String description, String status,
            LocalDate creationDate, boolean isActive) {
        this.charityId = charityId;
        this.charityName = charityName;
        this.founder = founder;
        this.description = description;
        this.status = status;
        this.creationDate = creationDate;
        this.isActive = isActive;
    }

    /**
     * Gets the unique identifier for the charity.
     *
     * @return the charity ID.
     */
    public Long getCharityId() {
        return charityId;
    }

    /**
     * Sets the unique identifier for the charity.
     *
     * @param charityId the charity ID to set.
     */
    public void setCharityId(Long charityId) {
        this.charityId = charityId;
    }

    /**
     * Gets the name of the charity.
     *
     * @return the charity name.
     */
    public String getCharityName() {
        return charityName;
    }

    /**
     * Sets the name of the charity.
     *
     * @param charityName the charity name to set.
     */
    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    /**
     * Gets the founder of the charity.
     *
     * @return the founder.
     */
    public String getFounder() {
        return founder;
    }

    /**
     * Sets the founder of the charity.
     *
     * @param founder the founder to set.
     */
    public void setFounder(String founder) {
        this.founder = founder;
    }

    /**
     * Gets the description of the charity.
     *
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the charity.
     *
     * @param description the description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the status of the charity.
     *
     * @return the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the charity.
     *
     * @param status the status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the creation date of the charity.
     *
     * @return the creation date.
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the creation date of the charity.
     *
     * @param creationDate the creation date to set.
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Checks if the charity is active.
     *
     * @return true if the charity is active, false otherwise.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets the active status of the charity.
     *
     * @param isActive the active status to set.
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
