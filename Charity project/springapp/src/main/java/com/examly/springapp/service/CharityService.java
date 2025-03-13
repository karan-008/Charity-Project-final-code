package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.DTO.CharityDTO;

/**
 * CharityService interface provides the contract for charity-related operations.
 * It includes methods for adding, retrieving, editing, and deleting charity details.
 * 
 * @Author - 
 */
public interface CharityService {

    /**
     * Adds a new charity.
     * 
     * @param charity the CharityDTO object containing details of the charity to be added.
     * @return the added CharityDTO object.
     */
    CharityDTO addCharity(CharityDTO charity);

    /**
     * Retrieves a list of all charities.
     * 
     * @return a list of CharityDTO objects representing all charities.
     */
    List<CharityDTO> getAllCharities();

    /**
     * Retrieves a charity by its unique ID.
     * 
     * @param charityId the unique ID of the charity.
     * @return the CharityDTO object representing the charity with the specified ID.
     */
    CharityDTO getAllCharitiesBycharityId(Long charityId);

    /**
     * Edits an existing charity.
     * 
     * @param charityId the unique ID of the charity to be edited.
     * @param updatedCharity the CharityDTO object containing updated details of the charity.
     * @return the updated CharityDTO object.
     */
    CharityDTO editCharity(Long charityId, CharityDTO updatedCharity);

    /**
     * Deletes a charity by its unique ID.
     * 
     * @param charityId the unique ID of the charity to be deleted.
     * @return a Boolean indicating the success of the deletion operation.
     */
    Boolean deleteCharity(Long charityId);
}
