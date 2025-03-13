package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.User;

/*
 * UserRepo is an interface which extends the JpaRepository.
 * JpaRepository is an interface which is used to interact or talk to the database.
 * The bucket in a JpaRepository contains the class name (User) and the data type of the primary key (Long).
 * 
 * @Author - 
 */

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    /**
     * Custom method to find a User by email and password.
     * This method will be implemented by Spring Data JPA based on the method name.
     *
     * @param email the email of the user.
     * @param password the password of the user.
     * @return the User entity matching the email and password.
     */
    User findByEmailAndPassword(String email, String password);
    
    /**
     * Custom method to find a User by email.
     * This method will be implemented by Spring Data JPA based on the method name.
     *
     * @param email the email of the user.
     * @return the User entity matching the email.
     */
    User findByEmail(String email);

    /**
     * Custom query to find the user ID by email.
     * The query selects the userId from the User entity where the email matches the provided parameter.
     *
     * @param email the email of the user.
     * @return the user ID matching the email.
     */
    @Query("SELECT u.userId from User u where u.email =?1")
    int findIdbyEmail(String email);

    /**
     * Custom query to find the username by email.
     * The query selects the username from the User entity where the email matches the provided parameter.
     *
     * @param email the email of the user.
     * @return the username matching the email.
     */
    @Query("SELECT u.username from User u where u.email =?1")
    String findnamebyEmail(String email);
    
}
