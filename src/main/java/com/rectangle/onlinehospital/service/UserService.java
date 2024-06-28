package com.rectangle.onlinehospital.service;

import com.rectangle.onlinehospital.pojo.User;
import com.rectangle.onlinehospital.utils.Result;

import java.util.List;

public interface UserService {

    Result<User> getByUserID(String userID);

    Result<List<User>> getAllUser();

    Result<String> UserLogin(String userID, String password);
}
