package com.examly.springapp.DTO;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * CharityDTO class represents a Data Transfer Object for Charity.
 * It includes fields such as charityId, charityName, founder, description, status, creationDate, and isActive.
 * 
 * @Author - 
 */
public class CharityDTO {

    private Long charityId; // Unique identifier for the charity

    @NotNull(message = "Charity name is mandatory")
    @Size(max = 100, message = "Charity name cannot be longer than 100 characters")
    private String charityName; // Name of the charity

    @NotBlank(message = "Founder is mandatory")
    @Size(max = 100, message = "Founder cannot be longer than 100 characters")
    private String founder; // Founder of the charity

    @NotBlank(message = "Description is mandatory")
    @Size(max = 500, message = "Description cannot be longer than 500 characters")
    private String description; // Description of the charity

    @NotBlank(message = "Status is mandatory")
    @Size(max = 50, message = "Status cannot be longer than 50 characters")
    private String status; // Status of the charity

    private LocalDate creationDate; // Creation date of the charity
    private boolean isActive; // Field for soft delete

    /**
     * Default constructor for CharityDTO.
     */
    public CharityDTO() {
    }

    /**
     * Parameterized constructor for CharityDTO.
     * 
     * @param charityId Unique identifier for the charity.
     * @param charityName Name of the charity.
     * @param founder Founder of the charity.
     * @param description Description of the charity.
     * @param status Status of the charity.
     * @param creationDate Creation date of the charity.
     * @param isActive Indicates if the charity is active.
     */
    public CharityDTO(Long charityId, String charityName, String founder, String description, String status,
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
     * @return the founder name.
     */
    public String getFounder() {
        return founder;
    }

    /**
     * Sets the founder of the charity.
     * 
     * @param founder the founder name to set.
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

    /**
     * Provides a string representation of the CharityDTO object.
     * 
     * @return a string representation of the CharityDTO object.
     */
    @Override
    public String toString() {
        return "CharityDTO [charityId=" + charityId + ", charityName=" + charityName + ", founder=" + founder +
                ", description=" + description + ", status=" + status + ", creationDate=" + creationDate +
                ", isActive=" + isActive + "]";
    }
}
