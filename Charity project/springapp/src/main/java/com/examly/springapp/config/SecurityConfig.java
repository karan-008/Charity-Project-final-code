package com.examly.springapp.config; 

// Importing required Spring and security packages.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Importing OpenAPI packages for API documentation.
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration // This class will act as a source of bean definitions for the Spring container.
@EnableWebSecurity // Enables Spring Security’s web security support and provides the Spring MVC integration.
@EnableMethodSecurity // Enables method-level security annotations like @PreAuthorize, @Secured, etc.
public class SecurityConfig {

    @Autowired // Autowires the JwtAuthenticationFilter bean into this class.
    private JwtAuthenticationFilter myJwtAuthFilter;

    @Autowired // Autowires the JwtAuthenticationEntryPoint bean into this class.
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired // Autowires the AuthenticationProvider bean into this class.
    private AuthenticationProvider authenticationProvider;

    @Bean // This method produces a bean to be managed by the Spring container.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors() // Enables CORS (Cross-Origin Resource Sharing) configuration.
                .and()
                .csrf().disable() // Disables CSRF (Cross-Site Request Forgery) protection.
                .authorizeHttpRequests() // Configures URL-based authorization.
                .requestMatchers(HttpMethod.POST, "/api/register", "/api/login").permitAll() // Permits all POST requests to these endpoints.
                .requestMatchers("/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html").permitAll() // Permits access to Swagger documentation.
                .anyRequest().authenticated() // All other requests require authentication.
                .and()
                .exceptionHandling() // Configures exception handling.
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // Sets the entry point for handling authentication exceptions.
                .and()
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configures session management to be stateless.
                .authenticationProvider(authenticationProvider) // Configures the custom authentication provider.
                .addFilterBefore(myJwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // Adds the JWT authentication filter before the UsernamePasswordAuthenticationFilter.
                .build(); // Builds the SecurityFilterChain instance.
    }

    @Bean // This method produces a bean to be managed by the Spring container.
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement()
                .addList("Bearer Authentication")) // Adds a security requirement for the OpenAPI documentation.
                .components(new Components()
                .addSecuritySchemes("Bearer Authentication", new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT") // Specifies that the security scheme uses JWT.
                .scheme("bearer"))); // Configures the scheme to use Bearer tokens.
    }
}