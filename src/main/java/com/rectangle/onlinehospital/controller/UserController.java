package com.rectangle.onlinehospital.controller;

import com.rectangle.onlinehospital.pojo.LoginRequest;
import com.rectangle.onlinehospital.pojo.User;
import com.rectangle.onlinehospital.service.UserService;
import com.rectangle.onlinehospital.utils.Result;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userList")
    public Result<List<User>> getAll() {
        try {
            return Result.success(userService.list());
        } catch (Exception ignore) {
            return Result.error("Database access error");
        }
    }

    @PostMapping("/getById")
    public Result<User> getByUserID(@RequestBody String userID) {
        try {
            return Result.success(userService.getById(userID));
        } catch (Exception ignore) {
            return Result.error("No such userID");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody @NotNull LoginRequest loginRequest) {
        return userService.login(loginRequest.getUserID(), loginRequest.getPassword());
    }
}
