package com.rectangle.onlinehospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rectangle.onlinehospital.pojo.User;
import com.rectangle.onlinehospital.utils.Result;

import java.util.List;

public interface UserService extends IService<User> {

    Result<User> getByUserID(String userID);

    Result<List<User>> getAllUser();

    Result<String> UserLogin(String userID, String password);
}
