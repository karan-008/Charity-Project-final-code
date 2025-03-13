package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.DTO.UserDTO;
import com.examly.springapp.DTO.LoginRequestDTO;

/**
 * UserService interface provides the contract for user-related operations.
 * It includes methods for registering, retrieving, and logging in users.
 * 
 * @Author - 
 */
public interface UserService {

    /**
     * Registers a new user.
     * 
     * @param userDTO the UserDTO object containing details of the user to be registered.
     * @return the registered UserDTO object.
     */
    UserDTO registerUser(UserDTO userDTO);

    /**
     * Retrieves a list of all users.
     * 
     * @return a list of UserDTO objects representing all users.
     */
    List<UserDTO> getAllUsers();

    /**
     * Logs in a user.
     * 
     * @param loginRequestDTO the LoginRequestDTO object containing the login details.
     * @return the LoginRequestDTO object representing the logged-in user details.
     */
    LoginRequestDTO loginUser(LoginRequestDTO loginRequestDTO); // Ensure this matches
}
