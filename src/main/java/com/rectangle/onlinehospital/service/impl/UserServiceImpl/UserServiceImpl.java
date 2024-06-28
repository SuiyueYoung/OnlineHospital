package com.rectangle.onlinehospital.service.impl.UserServiceImpl;

import com.rectangle.onlinehospital.mapper.UserMapper;
import com.rectangle.onlinehospital.pojo.User;
import com.rectangle.onlinehospital.service.UserService;
import com.rectangle.onlinehospital.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return Result.success(null);
    }
}
