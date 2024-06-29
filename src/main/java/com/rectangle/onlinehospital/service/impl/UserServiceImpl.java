package com.rectangle.onlinehospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rectangle.onlinehospital.mapper.UserMapper;
import com.rectangle.onlinehospital.pojo.SecurityUser;
import com.rectangle.onlinehospital.pojo.User;
import com.rectangle.onlinehospital.service.UserService;
import com.rectangle.onlinehospital.utils.JwtTokenUtil;
import com.rectangle.onlinehospital.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @Override
    public Result<User> getByUserID(String userID) {
    return null;
    }

    @Override
    public Result<List<User>> getAllUser() {
        return null;
    }

    @Override
    public Result<String> UserLogin(String userID, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userID, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            return Result.error("Wrong with username or password");
        }
        SecurityUser securityUser = (SecurityUser) authenticate.getPrincipal();
        String Token = jwtTokenUtil.generateToken(securityUser);
        return Result.success(Token);
    }
}
