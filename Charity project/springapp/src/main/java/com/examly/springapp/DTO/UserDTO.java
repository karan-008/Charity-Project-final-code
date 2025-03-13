package com.examly.springapp.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * UserDTO class represents a Data Transfer Object for User.
 * It includes fields such as userId, email, password, username, mobileNumber, and role.
 * 
 * @Author - 
 */
public class UserDTO {

    private Long userId; // Unique identifier for the user

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email; // Email address of the user

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password; // Password of the user

    @NotBlank(message = "Username is mandatory")
    @Size(max = 50, message = "Username cannot be longer than 50 characters")
    private String username; // Username of the user

    @NotBlank(message = "Mobile number is mandatory")
    @Size(max = 10, message = "Mobile number cannot be longer than 15 characters")
    private String mobileNumber; // Mobile number of the user

    @NotBlank(message = "Role is mandatory")
    private String role; // Role of the user

    /**
     * Default constructor for UserDTO.
     */
    public UserDTO() {
    }

    /**
     * Parameterized constructor for UserDTO.
     * 
     * @param userId Unique identifier for the user.
     * @param email Email address of the user.
     * @param password Password of the user.
     * @param username Username of the user.
     * @param mobileNumber Mobile number of the user.
     * @param role Role of the user.
     */
    public UserDTO(Long userId, String email, String password, String username, String mobileNumber, String role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.role = role;
    }

    // Getters and setters

    /**
     * Gets the unique identifier for the user.
     * 
     * @return the user ID.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier for the user.
     * 
     * @param userId the user ID to set.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets the email address of the user.
     * 
     * @return the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     * 
     * @param email the email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the user.
     * 
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * 
     * @param password the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the username of the user.
     * 
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     * 
     * @param username the username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the mobile number of the user.
     * 
     * @return the mobile number.
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Sets the mobile number of the user.
     * 
     * @param mobileNumber the mobile number to set.
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * Gets the role of the user.
     * 
     * @return the role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     * 
     * @param role the role to set.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Provides a string representation of the UserDTO object.
     * 
     * @return a string representation of the UserDTO object.
     */
    @Override
    public String toString() {
        return "UserDTO [userId=" + userId + ", email=" + email + ", username=" + username +
                ", mobileNumber=" + mobileNumber + ", role=" + role + "]";
    }
}
