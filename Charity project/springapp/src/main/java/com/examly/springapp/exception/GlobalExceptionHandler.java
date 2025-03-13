package com.examly.springapp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalExceptionHandler class handles various exceptions that occur in the application.
 * It provides custom responses for specific exception types.
 * 
 * @Author - 
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles CharityAlreadyExistsException.
     * 
     * @param exception the CharityAlreadyExistsException thrown.
     * @return a ResponseEntity with a 404 status and the exception message.
     */
    @ExceptionHandler(CharityAlreadyExistsException.class)
    public ResponseEntity<String> handleCharityAlreadyExistsException(Exception exception){
        return ResponseEntity.status(404).body(exception.getMessage());
    }

    /**
     * Handles CharityNotFoundException.
     * 
     * @param exception the CharityNotFoundException thrown.
     * @return a ResponseEntity with a 404 status and the exception message.
     */
    @ExceptionHandler(CharityNotFoundException.class)
    public ResponseEntity<String> handleCharityNotFoundException(Exception exception){
        return ResponseEntity.status(404).body(exception.getMessage());
    }

    /**
     * Handles UserNotFoundException.
     * 
     * @param e the UserNotFoundException thrown.
     * @return a ResponseEntity with a 404 status and the exception message.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(Exception e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    /**
     * Handles UserAlreadyExistsException.
     * 
     * @param e the UserAlreadyExistsException thrown.
     * @return a ResponseEntity with a 404 status and the exception message.
     */
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExists(Exception e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    /**
     * Handles DonationNotFoundException.
     * 
     * @param e the DonationNotFoundException thrown.
     * @return a ResponseEntity with a 404 status and the exception message.
     */
    @ExceptionHandler(DonationNotFoundException.class)
    public ResponseEntity<String> handleDonationNotFound(Exception e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    /**
     * Handles IllegalArgumentException.
     * 
     * @param e the IllegalArgumentException thrown.
     * @return a ResponseEntity with a 400 status and the exception message.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    /**
     * Handles MethodArgumentNotValidException.
     * This exception is thrown when validation on an argument annotated with @Valid fails.
     * 
     * @param ex the MethodArgumentNotValidException thrown.
     * @return a ResponseEntity with a 400 status and a map of field errors.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(400).body(errors);
    }
}
