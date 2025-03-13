package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.DTO.FeedbackDTO;

/**
 * FeedbackService interface provides the contract for feedback-related operations.
 * It includes methods for adding, retrieving, and deleting feedback entries.
 * 
 * @Author - 
 */
public interface FeedbackService {

    /**
     * Adds a new feedback entry.
     * 
     * @param feedbackDTO the FeedbackDTO object containing details of the feedback to be added.
     * @return the added FeedbackDTO object.
     */
    FeedbackDTO addFeedBack(FeedbackDTO feedbackDTO);

    /**
     * Deletes a feedback entry by its unique ID.
     * 
     * @param feedbackId the unique ID of the feedback to be deleted.
     * @return a Boolean indicating the success of the deletion operation.
     */
    Boolean deleteFeedback(Long feedbackId); // Ensure this returns Boolean

    /**
     * Retrieves a list of all feedback entries.
     * 
     * @return a list of FeedbackDTO objects representing all feedback entries.
     */
    List<FeedbackDTO> getAllFeedback();

    /**
     * Retrieves a list of feedback entries by a specific user.
     * 
     * @param userId the unique ID of the user.
     * @return a list of FeedbackDTO objects representing feedback entries by the specified user.
     */
    List<FeedbackDTO> getAllFeedbackByUserId(Long userId);
}
