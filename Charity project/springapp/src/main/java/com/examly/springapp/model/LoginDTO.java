package com.examly.springapp.model;

/**
 * @author 
 * Data Transfer Object (DTO) class representing login details.
 * DTOs are used to transfer data between software application subsystems.
 */
public class LoginDTO {

    /**
     * Unique identifier for the user.
     */
    private int userId;

    /**
     * Email address of the user.
     */
    private String email;

    /**
     * JSON Web Token (JWT) for authentication.
     * JWT is a compact, URL-safe means of representing claims to be transferred between two parties.
     */
    private String jwtToken;

    /**
     * Role of the user (e.g., admin, user).
     */
    private String role;

    /**
     * Username of the user.
     */
    private String username;

    /**
     * Default constructor for LoginDTO.
     */
    public LoginDTO() {
    }

    /**
     * Parameterized constructor for LoginDTO.
     *
     * @param userId Unique identifier for the user.
     * @param email Email address of the user.
     * @param jwtToken JWT for authentication.
     * @param role Role of the user.
     * @param username Username of the user.
     */
    public LoginDTO(int userId, String email, String jwtToken, String role, String username) {
        this.userId = userId;
        this.email = email;
        this.jwtToken = jwtToken;
        this.role = role;
        this.username = username;
    }

    /**
     * Gets the unique identifier for the user.
     *
     * @return the user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param userId the user ID to set.
     */
    public void setUserId(int userId) {
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
     * Gets the JWT for authentication.
     *
     * @return the JWT token.
     */
    public String getJwtToken() {
        return jwtToken;
    }

    /**
     * Sets the JWT for authentication.
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
}
