package com.examly.springapp.config;
 
import java.io.IOException;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.examly.springapp.config.JwtUtils;
 
 
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@Component /* Indicates that this class is a Spring component, eligible for component scanning and Spring's Dependency Injection. */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
 
    @Autowired /* Dependency injection of JwtUtils. */
    private JwtUtils jwtUtils;
 
    @Autowired /* Dependency injection of UserDetailsService. */
    private UserDetailsService userDetailsService;
 
    Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        logger.info("INSIDE JWT AUTHENTICATION FILTER");
        String token = null;
        String username = null;
 
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            /* Extract the JWT token from the authorization header */
            token = authorizationHeader.substring(7);
            logger.info("TOKEN : {}", token);
            /* Extract the username from the token */
            username = jwtUtils.extractUsername(token);
        }
 
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            /* Load user details using the username */
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            logger.info("USER DETAILS: {}", userDetails);
            if (jwtUtils.validateToken(token, userDetails)) {
                logger.info("TOKEN IS VALID {}", token);
                if (jwtUtils.validateToken(token, userDetails)) {
                    /* Create authentication token and set it in the security context */
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    logger.info("AUTHENTICATED");
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    logger.info("TOKEN VALIDATION FAILED");
                }
            }
        } else {
            logger.info("USERNAME IS NULL OR ALREADY AUTHENTICATED");
        }
        /* Continue the filter chain */
        filterChain.doFilter(request, response);
    }
}