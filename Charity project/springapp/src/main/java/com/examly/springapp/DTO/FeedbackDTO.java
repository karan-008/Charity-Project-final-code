package com.examly.springapp.DTO;

import java.time.LocalDate;

import com.examly.springapp.model.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * FeedbackDTO class represents a Data Transfer Object for Feedback.
 * It includes fields such as feedbackId, feedbackText, date, and user.
 * 
 * @Author - 
 */
public class FeedbackDTO {
    private Long feedbackId; // Unique identifier for the feedback

    @NotBlank(message = "Feedback text is mandatory")
    @Size(max = 500, message = "Feedback text cannot be longer than 500 characters")
    private String feedbackText; // Text content of the feedback

    private LocalDate date; // Date the feedback was given

    @NotNull(message = "User ID is mandatory")
    private User user; // User who provided the feedback

    /**
     * Default constructor for FeedbackDTO.
     */
    public FeedbackDTO() {
    }

    /**
     * Parameterized constructor for FeedbackDTO.
     * 
     * @param feedbackId Unique identifier for the feedback.
     * @param feedbackText Text content of the feedback.
     * @param date Date the feedback was given.
     * @param user User who provided the feedback.
     */
    public FeedbackDTO(Long feedbackId, String feedbackText, LocalDate date, User user) {
        this.feedbackId = feedbackId;
        this.feedbackText = feedbackText;
        this.date = date;
        this.user = user;
    }

    /**
     * Gets the unique identifier for the feedback.
     * 
     * @return the feedback ID.
     */
    public Long getFeedbackId() {
        return feedbackId;
    }

    /**
     * Sets the unique identifier for the feedback.
     * 
     * @param feedbackId the feedback ID to set.
     */
    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    /**
     * Gets the text content of the feedback.
     * 
     * @return the feedback text.
     */
    public String getFeedbackText() {
        return feedbackText;
    }

    /**
     * Sets the text content of the feedback.
     * 
     * @param feedbackText the feedback text to set.
     */
    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    /**
     * Gets the date the feedback was given.
     * 
     * @return the feedback date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date the feedback was given.
     * 
     * @param date the feedback date to set.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the user who provided the feedback.
     * 
     * @return the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who provided the feedback.
     * 
     * @param user the user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Provides a string representation of the FeedbackDTO object.
     * 
     * @return a string representation of the FeedbackDTO object.
     */
    @Override
    public String toString() {
        return "FeedbackDTO [feedbackId=" + feedbackId + ", feedbackText=" + feedbackText + ", date=" + date + ", user="
                + user + "]";
    }
}
