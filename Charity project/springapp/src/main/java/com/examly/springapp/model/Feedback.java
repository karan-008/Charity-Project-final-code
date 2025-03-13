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
 * Entity class representing Feedback.
 * Annotated with `@Entity` to map the class to a database table.
 */
@Entity
public class Feedback {

    /**
     * Unique identifier for feedback.
     * Annotated with `@Id` to denote the primary key.
     * Uses `@GeneratedValue` with the strategy `GenerationType.AUTO` for automatic ID generation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long feedbackId;

    /**
     * Text of the feedback provided by the user.
     */
    private String feedbackText;

    /**
     * Date when the feedback was given.
     * `LocalDate` is a class in Java that represents a date without time.
     */
    private LocalDate date;

    /**
     * User who provided the feedback.
     * Annotated with `@ManyToOne` to denote a many-to-one relationship with the User entity.
     * `@JoinColumn` specifies the foreign key column name in the feedback table.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Default constructor for Feedback.
     */
    public Feedback() {
    }

    /**
     * Parameterized constructor for Feedback.
     *
     * @param feedbackId Unique identifier for feedback.
     * @param feedbackText Text of the feedback.
     * @param date Date when the feedback was given.
     * @param user User who provided the feedback.
     */
    public Feedback(Long feedbackId, String feedbackText, LocalDate date, User user) {
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
     * Gets the text of the feedback.
     *
     * @return the feedback text.
     */
    public String getFeedbackText() {
        return feedbackText;
    }

    /**
     * Sets the text of the feedback.
     *
     * @param feedbackText the feedback text to set.
     */
    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    /**
     * Gets the date when the feedback was given.
     *
     * @return the date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date when the feedback was given.
     *
     * @param date the date to set.
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
     * Returns a string representation of the Feedback object.
     *
     * @return a string representation of the Feedback.
     */
    @Override
    public String toString() {
        return "Feedback [FeedbackId=" + feedbackId + ", FeedbackText=" + feedbackText + ", Date=" + date + ", User=" + user + "]";
    }
}
