package com.rectangle.onlinehospital.service.impl;

import com.rectangle.onlinehospital.mapper.UserMapper;
import com.rectangle.onlinehospital.pojo.User;
import com.rectangle.onlinehospital.service.UserService;
import com.rectangle.onlinehospital.utils.JwtUtil;
import com.rectangle.onlinehospital.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public Result<User> getByUserID(String userID) {
        try {
            return Result.success(userMapper.findByUserID(userID));
        } catch (Exception e) {
            return Result.error(null);
        }
    }

    @Override
    public Result<List<User>> getAllUser() {
        try {
            return Result.success(userMapper.findAllUser());
        } catch (Exception ignore) {
            return Result.error(null);
        }
    }

    @Override
    public Result<String> UserLogin(String userID, String password) {
        try {
            User user = userMapper.findByUserID(userID);
            if (user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("userID", user.getUserID());
                claims.put("userType", user.getUserType());
                String token = JwtUtil.getToken(claims);
                return Result.success(token);
            }
            return Result.error("Wrong with username or password");
        } catch (Exception ignore) {
            return Result.error("Wrong with username or password");
        }
    }
}
