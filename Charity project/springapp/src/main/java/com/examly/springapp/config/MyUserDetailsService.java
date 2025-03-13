package com.examly.springapp.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
 
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;
 
/**
 * MyUserDetailsService is a configuration class for setting up user details service,
 * password encoding, and authentication provider in the application.
 * This class directly accesses the database to check whether a user exists.
 */
 
//For directly accecssing the database to check whether the user exists in our database
@Configuration
public class MyUserDetailsService {
   
    private UserRepo userRepo;
    public MyUserDetailsService(UserRepo userRepo){
        this.userRepo = userRepo;
    }
    /**
     * Configures a UserDetailsService bean that loads user details from the database.
     *
     * @return a UserDetailsService instance.
     */
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService(){
 
            /**
             * Loads user details by email from the database.
             *
             * @param email the email of the user.
             * @return the user details.
             * @throws UsernameNotFoundException if the user is not found.
             */
 
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                User user = userRepo.findByEmail(email);
                if(user==null)
                    throw new UsernameNotFoundException("Username not found");
                return new MySpringUser(user);
            }
 
        };
    }
 
     /**
     * Configures a PasswordEncoder bean that encodes passwords using BCrypt.
     * BCryptPasswordEncoder provides methods for encoding, verifying, and matching passwords.
     *
     * @return a PasswordEncoder instance.
     */
    //encodes the password before saving into table
    //Encode,verify and matches are methods provided by BCryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
 
    /**
     * Configures an AuthenticationProvider bean that acts as a layer between the service and the database.
     * The DaoAuthenticationProvider uses the UserDetailsService and PasswordEncoder for authentication.
     *
     * @return an AuthenticationProvider instance.
     */
    //DAO is layer between service and database
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
 
    /**
     * Configures an AuthenticationManager bean.
     *
     * @param authenticationConfiguration the configuration for authentication.
     * @return an AuthenticationManager instance.
     * @throws Exception if an error occurs during authentication configuration.
     */
 
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}