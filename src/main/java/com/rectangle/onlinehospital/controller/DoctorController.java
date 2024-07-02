package com.rectangle.onlinehospital.controller;

import com.rectangle.onlinehospital.entity.request.LoginDto;
import com.rectangle.onlinehospital.service.DoctorService;
import com.rectangle.onlinehospital.utils.Result;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    /**
     * @Author Young
     * @Date 7/2/2024 10:16 PM
     * @Description Login for doctor
     * @Param [loginDto]
     * @Return com.rectangle.onlinehospital.utils.Result<java.lang.String>
     * @Since version 1.0
     */
    @RequestMapping("/login")
    public Result<String> login(@RequestBody @NotNull LoginDto loginDto) {
        return doctorService.doctorLogin(loginDto.getUserID(), loginDto.getPassword());
    }
}
