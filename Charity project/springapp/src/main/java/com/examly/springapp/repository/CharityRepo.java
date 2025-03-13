package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Charity;
import java.util.List;
import java.util.Optional;

/**
 * @author 
 * Repository interface for Charity entities.
 * Extends `JpaRepository` to provide CRUD operations and additional query methods.
 * Annotated with `@Repository` to indicate that it is a Spring Data JPA repository.
 */
@Repository
public interface CharityRepo extends JpaRepository<Charity, Long> {

    /**
     * Finds a charity by its name.
     *
     * @param charityName the name of the charity to find.
     * @return the Charity object with the specified name.
     */
    Charity findByCharityName(String charityName);

    /**
     * Finds all active charities.
     *
     * @return a list of active Charity objects.
     */
    @Query("SELECT c FROM Charity c WHERE c.isActive = true")
    List<Charity> findAllActiveCharities();

    /**
     * Finds an active charity by its ID.
     *
     * @param charityId the ID of the charity to find.
     * @return an Optional containing the active Charity object with the specified ID, or empty if not found.
     */
    @Query("SELECT c FROM Charity c WHERE c.charityId = :charityId AND c.isActive = true")
    Optional<Charity> findActiveCharityById(Long charityId);
}
