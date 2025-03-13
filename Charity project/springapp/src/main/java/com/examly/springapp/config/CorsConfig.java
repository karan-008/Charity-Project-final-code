package com.examly.springapp.config;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
/**
 * Configuration class to set up CORS (Cross-Origin Resource Sharing) settings.
 * This configuration allows the server to handle requests from different origins
 * (domains) and specifies which origins, HTTP methods, and headers are permitted
 * in requests.
 */
@Configuration
 
/**
 * Enables Spring MVC support in the application.
 * This annotation activates the Spring MVC components and allows for configuring
 * various aspects of web request handling.
 */
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
 
    /**
     * Configures the CORS mappings for the application.
     * This method is used to customize how CORS is handled.
     *
     * @param registry the registry to add CORS mappings to
     */
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        /*
         * Add mappings for all paths (/**)
         */
        registry.addMapping("/**")
                /*
                 * Allow all origins (*), can be restricted to specific domains
                 */
                .allowedOriginPatterns("*")
                /*
                 * Allow specified HTTP methods
                 */
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                /*
                 * Allow specified headers
                 */
                .allowedHeaders("Authorization", "Content-Type", "Cache-Control")
                /*
                 * Do not allow credentials (e.g., cookies) in cross-origin requests
                 */
                .allowCredentials(false);
    }
}