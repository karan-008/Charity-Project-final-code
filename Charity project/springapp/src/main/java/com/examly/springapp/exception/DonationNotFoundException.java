package com.examly.springapp.exception;

/**
 * Exception class to handle cases where a donation is not found.
 * Extends the RuntimeException class to provide a custom unchecked exception.
 * 
 * @Author - 
 */
public class DonationNotFoundException extends RuntimeException {
    
    /**
     * Constructor for DonationNotFoundException.
     * 
     * @param message the detail message explaining the reason for the exception.
     */
    public DonationNotFoundException(String message){
        super(message);
    }
}
