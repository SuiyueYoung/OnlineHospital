package com.rectangle.onlinehospital.controller;

import com.rectangle.onlinehospital.service.UserService;
import com.rectangle.onlinehospital.utils.Result;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.regex.Pattern;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Pattern MD5_PATTERN = Pattern.compile("^[a-fA-F0-9]{32}$");

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result<String> Login(@NotNull String userID, @NotNull String password) {
        // Forbid MD5 code
        if (MD5_PATTERN.matcher(password).matches()) {
            return Result.error("Forbidden password");
        }
        System.out.println("被访问");
        return userService.UserLogin(userID, password);
    }
}
