package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * @author 
 * Entity class representing a User.
 * Annotated with `@Entity` to map the class to a database table.
 */
@Entity
public class User {

    /**
     * Unique identifier for a user.
     * Annotated with `@Id` to denote the primary key.
     * Uses `@GeneratedValue` with the strategy `GenerationType.AUTO` for automatic ID generation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    /**
     * Email address of the user.
     */
    private String email;

    /**
     * Password of the user.
     * In a real-world application, passwords should be hashed and salted for security.
     */
    private String password;

    /**
     * Username of the user.
     */
    private String username;

    /**
     * Mobile number of the user.
     */
    private String mobileNumber;

    /**
     * Role of the user (e.g., admin, user).
     */
    private String role;

    /**
     * Default constructor for User.
     */
    public User() {
    }

    /**
     * Parameterized constructor for User.
     *
     * @param userId Unique identifier for a user.
     * @param email Email address of the user.
     * @param password Password of the user.
     * @param username Username of the user.
     * @param mobileNumber Mobile number of the user.
     * @param role Role of the user.
     */
    public User(Long userId, String email, String password, String username, String mobileNumber, String role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.role = role;
    }

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
}
