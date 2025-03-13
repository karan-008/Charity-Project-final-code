package com.examly.springapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.exception.UserAlreadyExistsException;
import com.examly.springapp.DTO.UserDTO;
import com.examly.springapp.DTO.LoginRequestDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;

/**
 * Service implementation for user-related operations.
 * Implements the UserService interface to provide the actual logic for managing users.
 * 
 * @Author - 
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired 
    private JwtUtils jwtutils; // Utility class for handling JWT tokens
    
    @Autowired 
    private AuthenticationManager authManager; // Manages authentication processes
    
    @Autowired  
    private PasswordEncoder passwordEncoder; // Encodes passwords for security
    
    @Autowired   
    private UserRepo userRepo; // Repository for interacting with the User entity

    /**
     * Registers a new user.
     * 
     * @param userDTO the UserDTO object containing details of the user to be registered.
     * @return the registered UserDTO object.
     */
    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User newUser = userRepo.findByEmail(userDTO.getEmail());
        if (newUser == null) {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encrypt the user's password
            User savedUser = userRepo.save(convertToEntity(userDTO));
            return convertToDTO(savedUser);
        }
        throw new UserAlreadyExistsException("User Already Exists!");
    }

    /**
     * Retrieves a list of all users.
     * 
     * @return a list of UserDTO objects representing all users.
     */
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    /**
     * Logs in a user.
     * 
     * @param loginRequestDTO the LoginRequestDTO object containing the login details.
     * @return the LoginRequestDTO object representing the logged-in user details.
     */
    @Override
    public LoginRequestDTO loginUser(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
        
        if (authentication.isAuthenticated()) {
            List<String> roleList = authentication.getAuthorities().stream().map(r -> r.getAuthority())
                    .collect(Collectors.toList());
            String role = roleList.get(0); // Assume the first role is the primary role
            LoginRequestDTO authUser = new LoginRequestDTO();
            authUser.setEmail(loginRequestDTO.getEmail());
            authUser.setJwtToken(jwtutils.generateToken(loginRequestDTO.getEmail(), role)); // Generate JWT token
            authUser.setRole(role);
            authUser.setUserId(userRepo.findByEmail(loginRequestDTO.getEmail()).getUserId());
            authUser.setUsername(userRepo.findByEmail(loginRequestDTO.getEmail()).getUsername());
            return authUser;
        }
        throw new IllegalArgumentException("Invalid Username or Password");
    }

    /**
     * Converts a User entity to a UserDTO.
     * 
     * @param user the User entity to be converted.
     * @return the converted UserDTO object.
     */
    private UserDTO convertToDTO(User user) {
        return new UserDTO(
            user.getUserId(),
            user.getEmail(),
            user.getPassword(),
            user.getUsername(),
            user.getMobileNumber(),
            user.getRole()
        );
    }

    /**
     * Converts a UserDTO to a User entity.
     * 
     * @param userDTO the UserDTO object to be converted.
     * @return the converted User entity.
     */
    private User convertToEntity(UserDTO userDTO) {
        return new User(
            userDTO.getUserId(),
            userDTO.getEmail(),
            userDTO.getPassword(),
            userDTO.getUsername(),
            userDTO.getMobileNumber(),
            userDTO.getRole()
        );
    }
}
