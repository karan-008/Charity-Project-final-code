package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.DTO.CharityDTO;
import com.examly.springapp.exception.CharityAlreadyExistsException;
import com.examly.springapp.exception.CharityNotFoundException;
import com.examly.springapp.model.Charity;
import com.examly.springapp.repository.CharityRepo;
import com.examly.springapp.repository.DonationRepo;

import jakarta.transaction.Transactional;

/**
 * Service implementation for charity-related operations.
 * Implements the CharityService interface to provide the actual logic for managing charities.
 * 
 * @Author - 
 */
@Service
public class CharityServiceImpl implements CharityService {

    @Autowired
    private CharityRepo charityRepo; // Repository for interacting with the Charity entity

    @Autowired
    private DonationRepo donationRepo; // Repository for interacting with the Donation entity

    /**
     * Adds a new charity.
     * 
     * @param charityDTO the CharityDTO object containing details of the charity to be added.
     * @return the added CharityDTO object.
     */
    @Override
    public CharityDTO addCharity(CharityDTO charityDTO) {
        Charity charity = convertToEntity(charityDTO);
        if (charityRepo.findByCharityName(charity.getCharityName()) == null) {
            charity.setCreationDate(LocalDate.now());
            charity.setActive(true); // Set as active upon creation
            return convertToDTO(charityRepo.save(charity));
        }
        throw new CharityAlreadyExistsException("Charity with same name already exists!");
    }

    /**
     * Retrieves a list of all active charities.
     * 
     * @return a list of CharityDTO objects representing all active charities.
     */
    @Override
    public List<CharityDTO> getAllCharities() {
        List<Charity> charities = charityRepo.findAllActiveCharities();
        List<CharityDTO> charityDTOList = new ArrayList<>();
        for (Charity charity : charities) {
            charityDTOList.add(convertToDTO(charity));
        }
        return charityDTOList;
    }

    /**
     * Retrieves a charity by its unique ID.
     * 
     * @param charityId the unique ID of the charity.
     * @return the CharityDTO object representing the charity with the specified ID.
     */
    @Override
    public CharityDTO getAllCharitiesBycharityId(Long charityId) {
        return convertToDTO(charityRepo.findActiveCharityById(charityId)
            .orElseThrow(() -> new CharityNotFoundException("Charity Not Found!")));
    }

    /**
     * Edits an existing charity.
     * 
     * @param charityId the unique ID of the charity to be edited.
     * @param updatedCharityDTO the CharityDTO object containing updated details of the charity.
     * @return the updated CharityDTO object.
     */
    @Override
    public CharityDTO editCharity(Long charityId, CharityDTO updatedCharityDTO) {
        Charity charity = charityRepo.findActiveCharityById(charityId)
            .orElseThrow(() -> new CharityNotFoundException("Charity Not Found!"));
        if (updatedCharityDTO.getCharityName() != null) charity.setCharityName(updatedCharityDTO.getCharityName());
        if (updatedCharityDTO.getDescription() != null) charity.setDescription(updatedCharityDTO.getDescription());
        if (updatedCharityDTO.getStatus() != null) charity.setStatus(updatedCharityDTO.getStatus());
        return convertToDTO(charityRepo.save(charity));
    }

    /**
     * Soft deletes a charity by its unique ID.
     * Marks the charity as inactive instead of removing it from the database.
     * 
     * @param charityId the unique ID of the charity to be deleted.
     * @return a Boolean indicating the success of the deletion operation.
     */
    @Override
    @Transactional
    public Boolean deleteCharity(Long charityId) {
        Charity charity = charityRepo.findActiveCharityById(charityId)
            .orElseThrow(() -> new CharityNotFoundException("Charity Not Found!"));
        charity.setActive(false); // Soft delete by setting isActive to false
        charityRepo.save(charity);
        return true;
    }

    /**
     * Converts a Charity entity to a CharityDTO.
     * 
     * @param charity the Charity entity to be converted.
     * @return the converted CharityDTO object.
     */
    private CharityDTO convertToDTO(Charity charity) {
        return new CharityDTO(
            charity.getCharityId(),
            charity.getCharityName(),
            charity.getFounder(),
            charity.getDescription(),
            charity.getStatus(),
            charity.getCreationDate(),
            charity.isActive()
        );
    }

    /**
     * Converts a CharityDTO to a Charity entity.
     * 
     * @param charityDTO the CharityDTO object to be converted.
     * @return the converted Charity entity.
     */
    private Charity convertToEntity(CharityDTO charityDTO) {
        return new Charity(
            charityDTO.getCharityId(),
            charityDTO.getCharityName(),
            charityDTO.getFounder(),
            charityDTO.getDescription(),
            charityDTO.getStatus(),
            charityDTO.getCreationDate(),
            charityDTO.isActive()
        );
    }
}
