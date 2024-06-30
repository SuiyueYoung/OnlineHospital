package com.rectangle.onlinehospital.pojo;

import com.rectangle.onlinehospital.config.UserRoleConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class SecurityUser implements UserDetails {

    private final User user;

    private final UserRoleConfig userRoleConfig;

    public SecurityUser(User user, UserRoleConfig userRoleConfig) {
        this.user = user;
        this.userRoleConfig = userRoleConfig;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = userRoleConfig.getRoles().get(user.getUserType());
        if (Objects.isNull(role)) {
            throw new IllegalArgumentException("Invalid UserType" + user.getUserType());
        }
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserID();
    }
}
