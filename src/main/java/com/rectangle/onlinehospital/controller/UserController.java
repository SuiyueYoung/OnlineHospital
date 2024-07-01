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

    /**
     * @Author Young
     * @Date 6/30/2024 2:58 PM
     * @Description
     * @Param [null]
     * @Return com.rectangle.onlinehospital.utils.Result<java.util.List < com.rectangle.onlinehospital.pojo.User>>
     * @Since version 1.0
     */
    @GetMapping("/getAll")
    public Result<List<User>> getAll() {
        return Result.success(userService.list());
    }

    /**
     * @Author Young
     * @Date 6/30/2024 2:59 PM
     * @Description
     * @Param [userID]
     * @Return com.rectangle.onlinehospital.utils.Result<com.rectangle.onlinehospital.pojo.User>
     * @Since version 1.0
     */
    @PostMapping("/getByUserID")
    public Result<User> getByUserID(@RequestBody String userID) {
        return Result.success(userService.getById(userID));
    }

    /**
     * @Author Young
     * @Date 6/30/2024 10:02 PM
     * @Description
     * @Param [user]
     * @Return com.rectangle.onlinehospital.utils.Result<java.lang.String>
     * @Since version 1.0
     */
    @PostMapping("/updateUserInfo")
    public Result<String> updateUserInfo(@RequestBody User user) {
        return userService.updateInfo(user);
    }

    /**
     * @Author Young
     * @Date 6/30/2024 10:06 PM
     * @Description
     * @Param [user]
     * @Return com.rectangle.onlinehospital.utils.Result<java.lang.String>
     * @Since version 1.0
     */
    @PostMapping("/updateUserPassword")
    public Result<String> updateUserPassword(@RequestBody User user) {
        return userService.updatePassword(user);
    }

    /**
     * @Author Young
     * @Date 6/30/2024 2:59 PM
     * @Description
     * @Param [loginRequest]
     * @Return com.rectangle.onlinehospital.utils.Result<java.lang.String>
     * @Since version 1.0
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody @NotNull LoginRequest loginRequest) {
        return userService.userLogin(loginRequest.getUserID(), loginRequest.getPassword());
    }

    /**
     * @Author Young
     * @Date 6/30/2024 2:59 PM
     * @Description
     * @Param [user]
     * @Return com.rectangle.onlinehospital.utils.Result<java.lang.String>
     * @Since version 1.0
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody @NotNull User user) {
        return userService.userRegister(user);
    }
}
