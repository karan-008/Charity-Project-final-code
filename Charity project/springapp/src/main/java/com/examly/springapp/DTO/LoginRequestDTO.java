package com.examly.springapp.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * LoginRequestDTO class represents a Data Transfer Object for user login requests.
 * It includes fields such as userId, email, jwtToken, role, username, and password.
 * 
 * @Author - 
 */
public class LoginRequestDTO {
    
    private Long userId; // Unique identifier for the user

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email; // Email address of the user

    private String jwtToken; // JWT token for authentication
    private String role; // Role of the user
    private String username; // Username of the user

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password should be at least 6 characters long")
    private String password; // Password of the user

    /**
     * Default constructor for LoginRequestDTO.
     */
    public LoginRequestDTO() {
    }

    /**
     * Parameterized constructor for LoginRequestDTO.
     * 
     * @param userId Unique identifier for the user.
     * @param email Email address of the user.
     * @param jwtToken JWT token for authentication.
     * @param role Role of the user.
     * @param username Username of the user.
     * @param password Password of the user.
     */
    public LoginRequestDTO(Long userId, String email, String jwtToken, String role, String username, String password) {
        this.userId = userId;
        this.email = email;
        this.jwtToken = jwtToken;
        this.role = role;
        this.username = username;
        this.password = password;
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
     * Gets the JWT token for authentication.
     * 
     * @return the JWT token.
     */
    public String getJwtToken() {
        return jwtToken;
    }

    /**
     * Sets the JWT token for authentication.
     * 
     * @param jwtToken the JWT token to set.
     */
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
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
     * Provides a string representation of the LoginRequestDTO object.
     * 
     * @return a string representation of the LoginRequestDTO object.
     */
    @Override
    public String toString() {
        return "LoginRequestDTO [userId=" + userId + ", email=" + email + ", jwtToken=" + jwtToken + 
               ", role=" + role + ", username=" + username + ", password=" + password + "]";
    }
}
