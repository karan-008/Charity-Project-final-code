package com.examly.springapp.exception;

/**
 * Exception class to handle cases where a user is not found.
 * Extends the RuntimeException class to provide a custom unchecked exception.
 * 
 * @Author - 
 */
public class UserNotFoundException extends RuntimeException {
    
    /**
     * Constructor for UserNotFoundException.
     * 
     * @param message the detail message explaining the reason for the exception.
     */
    public UserNotFoundException(String message){
        super(message);
    }
}
