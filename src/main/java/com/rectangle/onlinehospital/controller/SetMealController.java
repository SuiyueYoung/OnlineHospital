package com.rectangle.onlinehospital.controller;

import com.rectangle.onlinehospital.pojo.SetMeal;
import com.rectangle.onlinehospital.service.SetMealService;
import com.rectangle.onlinehospital.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/setMeal")
public class SetMealController {

    private final SetMealService setMealService;

    @Autowired
    public SetMealController(SetMealService setMealService) {
        this.setMealService = setMealService;
    }

    @GetMapping("/getAll")
    public Result<List<SetMeal>> getAll() {
        return Result.success(setMealService.list());
    }
}
