package com.rectangle.onlinehospital.entity.security;

import com.rectangle.onlinehospital.config.UserRoleConfig;
import com.rectangle.onlinehospital.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class SecurityUserDo implements UserDetails {

    private final User user;

    private final UserRoleConfig userRoleConfig;

    public SecurityUserDo(User user, UserRoleConfig userRoleConfig) {
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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
