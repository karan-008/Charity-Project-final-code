package com.examly.springapp.config;
 
import java.io.IOException;
 
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
 
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
/* Indicates that this class is a Spring component, eligible for component scanning and Spring's Dependency Injection. */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        /* Set the response status to 401 (Unauthorized) */
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        /* Send an error response with the status and message */
        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
    }
}