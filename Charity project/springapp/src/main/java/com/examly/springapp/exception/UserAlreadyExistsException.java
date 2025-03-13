package com.examly.springapp.exception;

/**
 * Exception class to handle cases where a user already exists.
 * Extends the RuntimeException class to provide a custom unchecked exception.
 * 
 * @Author - 
 */
public class UserAlreadyExistsException extends RuntimeException {
    
    /**
     * Constructor for UserAlreadyExistsException.
     * 
     * @param message the detail message explaining the reason for the exception.
     */
    public UserAlreadyExistsException(String message){
        super(message);
    }
}
