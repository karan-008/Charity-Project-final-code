package com.examly.springapp.config;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
 
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
 
/* Indicates that this class is a Spring component, eligible for component scanning and Spring's Dependency Injection. */
@Component
public class JwtUtils {
 
    Logger logger = LoggerFactory.getLogger(JwtUtils.class);
 
    private final SecretKey SECRET_KEY;
 
    public JwtUtils(@Value("${spring.security.jwt.secret-key}") String secretKey) {
        /* Initialize the secret key for signing the JWT */
        this.SECRET_KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
        logger.info("Initialized SECRET_KEY: {}", secretKey);
    }


    
    private String createToken(Map<String, Object> claims, String username) {
        /* Create the token with the specified claims and username */
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) /* 1 day expiration */
                .signWith(SECRET_KEY)
                .compact();
        logger.info("Generated Token: {}", token);
        return token;
    }
 
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        /* Extract a specific claim from the token */
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        /* Extract the username (subject) from the token */
        return extractClaim(token, Claims::getSubject);
    }
 
    public Date extractExpiration(String token) {
        /* Extract the expiration date from the token */
        return extractClaim(token, Claims::getExpiration);
    }
 
   
 
    private Claims extractAllClaims(String token) {
        try {
            /* Extract all claims from the token */
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            logger.info("Parsed Claims: {}", claims);
            return claims;
        } catch (Exception e) {
            logger.error("Failed to parse claims", e);
            throw e;
        }
    }
 
    private Boolean isTokenExpired(String token) {
        /* Check if the token is expired */
        Date expiration = extractExpiration(token);
        logger.info("ExpirationToken: {}", expiration);
        return expiration.before(new Date());
    }
    
    public String generateToken(String username, String role) {
        /* Generate a new token with the username and role */
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return createToken(claims, username);
    }
 
    public Boolean validateToken(String token, UserDetails userDetails) {
        try {
            /* Validate the token against the user details */
            final String username = extractUsername(token);
            logger.info("Extracted Username: {} and {}", username, userDetails.getUsername());
            boolean isValid = (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
            if (!isValid) {
                logger.warn("Token validation failed for user: {}", username);
            }
            return isValid;
        } catch (Exception e) {
            logger.error("Token validation error", e);
            return false;
        }
    }
 
    
 
}