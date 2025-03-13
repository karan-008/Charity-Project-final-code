package com.examly.springapp.config;
 
import com.examly.springapp.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
 
import java.util.Collection;
import java.util.Collections;
 
public class MySpringUser implements UserDetails {
    private String email;
    private String password;
    private String role;
 
    public MySpringUser(User user) {
        /* Initialize email and password from the User object */
        this.email = user.getEmail();
        this.password = user.getPassword();
        /* Initialize role from the User object */
        this.role = user.getRole();
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /* Return the role as a granted authority */
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }
 
    @Override
    public String getPassword() {
        /* Return the user's password */
        return this.password;
    }
 
    @Override
    public String getUsername() {
        /* Return the user's email as the username */
        return this.email;
    }
 
    @Override
    public boolean isAccountNonExpired() {
        /* Indicate that the account is not expired */
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        /* Indicate that the account is not locked */
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        /* Indicate that the credentials are not expired */
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        /* Indicate that the account is enabled */
        return true;
    }
}