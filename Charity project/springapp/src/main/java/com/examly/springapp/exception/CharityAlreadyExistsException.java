package com.examly.springapp.exception;

/**
 * Exception class to handle cases where a charity already exists.
 * Extends the RuntimeException class to provide a custom unchecked exception.
 * 
 * @Author - 
 */
public class CharityAlreadyExistsException extends RuntimeException {
    
    /**
     * Constructor for CharityAlreadyExistsException.
     * 
     * @param message the detail message explaining the reason for the exception.
     */
    public CharityAlreadyExistsException(String message){
        super(message);
    }
}
