package com.rectangle.onlinehospital.controller;

import com.rectangle.onlinehospital.pojo.Hospital;
import com.rectangle.onlinehospital.service.HospitalService;
import com.rectangle.onlinehospital.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
