package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.examly.springapp.DTO.FeedbackDTO;
import com.examly.springapp.exception.CharityNotFoundException;
import com.examly.springapp.exception.UserNotFoundException;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.model.User;

/**
 * Service implementation for feedback-related operations.
 * Implements the FeedbackService interface to provide the actual logic for managing feedback entries.
 * 
 * @Author - 
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepo feedbackRepo; // Repository for interacting with the Feedback entity

    @Autowired
    private UserRepo userRepo; // Repository for interacting with the User entity

    /**
     * Adds a new feedback entry.
     * 
     * @param feedbackDTO the FeedbackDTO object containing details of the feedback to be added.
     * @return the added FeedbackDTO object.
     */
    @Override
    public FeedbackDTO addFeedBack(FeedbackDTO feedbackDTO) {
        User user = userRepo.findById(feedbackDTO.getUser().getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found."));
        
        Feedback feedback = convertToEntity(feedbackDTO, user);
        feedback.setDate(LocalDate.now()); // Set the current date as the feedback date
        Feedback savedFeedback = feedbackRepo.save(feedback);
        return convertToDTO(savedFeedback);
    }

    /**
     * Deletes a feedback entry by its unique ID.
     * 
     * @param feedbackId the unique ID of the feedback to be deleted.
     * @return a Boolean indicating the success of the deletion operation.
     */
    @Override
    public Boolean deleteFeedback(Long feedbackId) {
        feedbackRepo.findById(feedbackId)
                .orElseThrow(() -> new CharityNotFoundException("Feedback Not Found!"));
        feedbackRepo.deleteById(feedbackId);
        return true;
    }

    /**
     * Retrieves a list of all feedback entries.
     * 
     * @return a list of FeedbackDTO objects representing all feedback entries.
     */
    @Override
    public List<FeedbackDTO> getAllFeedback() {
        List<Feedback> feedbackList = feedbackRepo.findAll();
        List<FeedbackDTO> feedbackDTOList = new ArrayList<>();
        for (Feedback feedback : feedbackList) {
            feedbackDTOList.add(convertToDTO(feedback));
        }
        return feedbackDTOList;
    }

    /**
     * Retrieves a list of feedback entries by a specific user.
     * 
     * @param userId the unique ID of the user.
     * @return a list of FeedbackDTO objects representing feedback entries by the specified user.
     */
    @Override
    public List<FeedbackDTO> getAllFeedbackByUserId(Long userId) {
        List<Feedback> feedbackList = feedbackRepo.findByUserId(userId);
        if (feedbackList.isEmpty()) {
            throw new UserNotFoundException("No Feedback Found for User with ID: " + userId);
        }
        List<FeedbackDTO> feedbackDTOList = new ArrayList<>();
        for (Feedback feedback : feedbackList) {
            feedbackDTOList.add(convertToDTO(feedback));
        }
        return feedbackDTOList;
    }

    /**
     * Converts a FeedbackDTO to a Feedback entity.
     * 
     * @param feedbackDTO the FeedbackDTO object to be converted.
     * @param user the User entity associated with the feedback.
     * @return the converted Feedback entity.
     */
    private Feedback convertToEntity(FeedbackDTO feedbackDTO, User user) {
        return new Feedback(feedbackDTO.getFeedbackId(), feedbackDTO.getFeedbackText(), feedbackDTO.getDate(), user);
    }

    /**
     * Converts a Feedback entity to a FeedbackDTO.
     * 
     * @param feedback the Feedback entity to be converted.
     * @return the converted FeedbackDTO object.
     */
    private FeedbackDTO convertToDTO(Feedback feedback) {
        return new FeedbackDTO(feedback.getFeedbackId(), feedback.getFeedbackText(), feedback.getDate(), feedback.getUser());
    }
}
