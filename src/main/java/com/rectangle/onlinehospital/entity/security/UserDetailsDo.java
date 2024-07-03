package com.rectangle.onlinehospital.entity.security;

import com.rectangle.onlinehospital.entity.Doctor;
import com.rectangle.onlinehospital.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetailsDo implements UserDetails {

    private final User user;
    private final Doctor doctor;

    public UserDetailsDo(User user, Doctor doctor) {
        this.user = user;
        this.doctor = doctor;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (!Objects.isNull(user)) {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

        if (!Objects.isNull(doctor)) {
            return List.of(new SimpleGrantedAuthority("ROLE_DOCTOR"));
        }
        return List.of();
    }

    @Override
    public String getPassword() {
        if (!Objects.isNull(user)) {
            return user.getPassword();
        }

        if (!Objects.isNull(doctor)) {
            return doctor.getPassword();
        }
        return null;
    }

    @Override
    public String getUsername() {
        if (!Objects.isNull(user)) {
            return user.getUserID();
        }

        if (!Objects.isNull(doctor)) {
            return doctor.getDocCode();
        }
        return null;
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
