package com.rectangle.onlinehospital.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rectangle.onlinehospital.entity.Hospital;
import com.rectangle.onlinehospital.service.HospitalService;
import com.rectangle.onlinehospital.utils.Result;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/getAll")
    Result<List<Hospital>> getAll() {
        return Result.success(hospitalService.list());
    }

    @GetMapping("/getByPage")
    Result<IPage<Hospital>> getByPage(@RequestParam @NotNull Integer page, @RequestParam @NotNull Integer size) {
        Page<Hospital> hospitalPage = new Page<>(page, size);
        return Result.success(hospitalService.page(hospitalPage));
    }
}
