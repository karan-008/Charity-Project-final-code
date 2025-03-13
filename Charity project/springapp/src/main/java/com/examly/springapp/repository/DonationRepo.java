package com.examly.springapp.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.examly.springapp.model.Donation;
import jakarta.transaction.Transactional;

/**
 * @author 
 * Repository interface for Donation entities.
 * Extends `JpaRepository` to provide CRUD operations and additional query methods.
 */
public interface DonationRepo extends JpaRepository<Donation, Long> {

    /**
     * Finds all donations made by a specific user.
     *
     * @param userId the ID of the user.
     * @return a list of Donation objects made by the user.
     */
    @Query("select d from Donation d where d.user.userId = :userId")
    List<Donation> findByUserId(@Param("userId") Long userId);

    /**
     * Finds all donations made to a specific charity.
     *
     * @param charityId the ID of the charity.
     * @return a list of Donation objects made to the charity.
     */
    @Query("select d from Donation d where d.charity.charityId = :charityId")
    List<Donation> findByCharityId(@Param("charityId") Long charityId);

    /**
     * Calculates the total amount donated to a specific charity.
     *
     * @param charityId the ID of the charity.
     * @return an Optional containing the total amount donated, or empty if no donations are found.
     */
    @Query("SELECT SUM(d.amount) FROM Donation d WHERE d.charity.charityId = :charityId")
    Optional<Double> sumTotalDonationsByCharityId(@Param("charityId") Long charityId);

    /**
     * Deletes all donations made to a specific charity.
     *
     * @param charityId the ID of the charity.
     *
     * @Transactional is used to indicate that the method should be executed within a transactional context.
     * This ensures that the operation is atomic, consistent, isolated, and durable (ACID properties).
     *
     * @Modifying indicates that the query is an update or delete operation, which modifies the database.
     * This annotation is necessary for any query that changes the state of the database.
     */
    @Transactional
    @Modifying
    @Query("delete from Donation d where d.charity.charityId = :charityId")
    void deleteByCharityId(@Param("charityId") Long charityId);
}
