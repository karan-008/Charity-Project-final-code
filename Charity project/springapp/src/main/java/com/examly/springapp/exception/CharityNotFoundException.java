package com.examly.springapp.exception;

/**
 * Exception class to handle cases where a charity is not found.
 * Extends the RuntimeException class to provide a custom unchecked exception.
 * 
 * @Author - 
 */
public class CharityNotFoundException extends RuntimeException {
    
    /**
     * Constructor for CharityNotFoundException.
     * 
     * @param message the detail message explaining the reason for the exception.
     */
    public CharityNotFoundException(String message){
        super(message);
    }
}
