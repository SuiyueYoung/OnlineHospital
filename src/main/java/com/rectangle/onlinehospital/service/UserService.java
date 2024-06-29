package com.rectangle.onlinehospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rectangle.onlinehospital.pojo.User;
import com.rectangle.onlinehospital.utils.Result;

public interface UserService extends IService<User> {

    Result<String> login(String username, String password);

}
