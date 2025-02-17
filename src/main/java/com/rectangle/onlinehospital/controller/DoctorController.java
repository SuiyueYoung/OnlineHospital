package com.rectangle.onlinehospital.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rectangle.onlinehospital.entity.request.LoginDto;
import com.rectangle.onlinehospital.entity.response.PhyExamReserveVo;
import com.rectangle.onlinehospital.service.DoctorService;
import com.rectangle.onlinehospital.utils.Result;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/login")
    public Result<String> login(@RequestBody @NotNull LoginDto loginDto) {
        return doctorService.doctorLogin(loginDto.getUsername(), loginDto.getPassword());
    }

    @GetMapping("/phyExamReserve")
    public Result<IPage<PhyExamReserveVo>> getPhyExamReserve(@RequestParam @NotNull Integer page, @RequestParam @NotNull Integer size, @RequestParam @NotNull Integer doctorID) {
        return Result.success(doctorService.getPhyExamReserve(page, size, doctorID));
    }
}
