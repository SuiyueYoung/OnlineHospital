package com.rectangle.onlinehospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rectangle.onlinehospital.config.UserRoleConfig;
import com.rectangle.onlinehospital.mapper.UserMapper;
import com.rectangle.onlinehospital.pojo.SecurityUser;
import com.rectangle.onlinehospital.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl extends ServiceImpl<UserMapper, User> implements UserDetailsService {

    private final UserRoleConfig userRoleConfig;

    @Autowired
    public UserDetailsServiceImpl(UserRoleConfig userRoleConfig) {
        this.userRoleConfig = userRoleConfig;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getById(username);
        if (Objects.isNull(user)) {
            throw new RuntimeException("User not found");
        }
        return new SecurityUser(user, userRoleConfig);
    }
}
