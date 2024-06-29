package com.rectangle.onlinehospital.controller;

import com.rectangle.onlinehospital.pojo.LoginRequest;
import com.rectangle.onlinehospital.service.UserService;
import com.rectangle.onlinehospital.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result<String> Login(@RequestBody LoginRequest request) {
        return userService.UserLogin(request.getUserID(),request.getPassword());
    }
}
