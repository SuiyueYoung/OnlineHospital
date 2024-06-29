package com.rectangle.onlinehospital.controller;

import com.rectangle.onlinehospital.pojo.User;
import com.rectangle.onlinehospital.service.UserService;
import com.rectangle.onlinehospital.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
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
}
