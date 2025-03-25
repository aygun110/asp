package com.example.config;

import com.example.model.UserDtls;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class CustomUser implements UserDetails {
    private UserDtls user;

    public CustomUser(UserDtls user) {
        super();
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority= new SimpleGrantedAuthority(user.getRole());
        return Arrays.asList(authority);
        // Implement logic to return user roles/authorities, if needed. // Modify this as per your requirements
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();  // Assuming `getName` is your equivalent of username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Return true if the account is not expired, else return false
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Return true if the account is not locked, else return false
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Return true if the credentials are not expired, else return false
    }

    @Override
    public boolean isEnabled() {
        return true;  // Assuming `isEnabled` is a field in `UserDtls`
    }
}
