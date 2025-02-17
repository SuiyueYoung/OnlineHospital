package com.rectangle.onlinehospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rectangle.onlinehospital.entity.Order;
import com.rectangle.onlinehospital.entity.User;
import com.rectangle.onlinehospital.entity.request.LoginDto;
import com.rectangle.onlinehospital.utils.Result;

public interface UserService extends IService<User> {

    Result<String> userLogin(String username, String password);

    Result<String> userRegister(User user);

    Result<String> updateInfo(User user);

    Result<String> updatePassword(LoginDto updateInfo);

    Result<String> submitOrder(Order order);
}