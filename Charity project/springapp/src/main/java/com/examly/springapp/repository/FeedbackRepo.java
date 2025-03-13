package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Feedback;

/**
 * Repository interface for managing Feedback entities.
 * Annotated with @Repository to indicate that it's a Spring Data repository.
 * Extends JpaRepository to provide basic CRUD operations and additional query methods.
 * 
 * @Author - 
 */
@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Long> {

    /**
     * Custom query to find feedback entries by user ID.
     * The query selects Feedback entities where the user ID matches the provided parameter.
     *
     * @param userId the ID of the user whose feedback entries are to be fetched.
     * @return a list of Feedback entities associated with the specified user ID.
     */
    @Query("select c from Feedback c where c.user.userId=:userId")
    public List<Feedback> findByUserId(@Param("userId") Long userId);
}
