package com.examly.springapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.examly.springapp.DTO.FeedbackDTO;
import com.examly.springapp.service.FeedbackService;

/**
 * FeedbackController class handles feedback-related requests.
 * It includes endpoints for adding, retrieving, and deleting feedback entries.
 * 
 * @Author - 
 */
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService; // Service for feedback-related operations

    /**
     * Endpoint for adding a new feedback entry.
     * Accessible only to users with the 'USER' role.
     * 
     * @param feedbackDTO the FeedbackDTO object containing feedback details.
     * @return a ResponseEntity containing the added FeedbackDTO object with a 201 status.
     */
    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<FeedbackDTO> addFeedBack(@Valid @RequestBody FeedbackDTO feedbackDTO) {
        FeedbackDTO savedFeedbackDTO = feedbackService.addFeedBack(feedbackDTO);
        return ResponseEntity.status(201).body(savedFeedbackDTO);
    }

    /**
     * Endpoint for retrieving all feedback entries.
     * Accessible only to users with the 'ADMIN' role.
     * 
     * @return a ResponseEntity containing a list of FeedbackDTO objects with a 200 status.
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedback() {
        List<FeedbackDTO> feedbackDTOs = feedbackService.getAllFeedback();
        return ResponseEntity.status(200).body(feedbackDTOs);
    }

    /**
     * Endpoint for deleting a feedback entry by its unique ID.
     * Accessible only to users with the 'USER' role.
     * 
     * @param feedbackId the unique ID of the feedback to be deleted.
     * @return a ResponseEntity with a 200 status and a success message.
     */
    @DeleteMapping("/{feedbackId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return ResponseEntity.status(200).body("Deleted successfully!");
    }

    /**
     * Endpoint for retrieving feedback entries by a specific user.
     * Accessible only to users with the 'USER' role.
     * 
     * @param userId the unique ID of the user.
     * @return a ResponseEntity containing a list of FeedbackDTO objects with a 200 status.
     */
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbackByUserId(@PathVariable Long userId) {
        List<FeedbackDTO> feedbackDTOs = feedbackService.getAllFeedbackByUserId(userId);
        return ResponseEntity.status(200).body(feedbackDTOs);
    }
}
