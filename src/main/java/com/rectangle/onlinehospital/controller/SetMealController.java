package com.rectangle.onlinehospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rectangle.onlinehospital.entity.CheckItem;
import com.rectangle.onlinehospital.entity.SetMeal;
import com.rectangle.onlinehospital.entity.SetMealDetailed;
import com.rectangle.onlinehospital.service.CheckItemService;
import com.rectangle.onlinehospital.service.SetMealDetailedService;
import com.rectangle.onlinehospital.service.SetMealService;
import com.rectangle.onlinehospital.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/setMeal")
public class SetMealController {

    private final SetMealService setMealService;

    private final SetMealDetailedService setMealDetailedService;

    private final CheckItemService checkItemService;


    @Autowired
    public SetMealController(SetMealService setMealService, SetMealDetailedService setMealDetailedService, CheckItemService checkItemService) {
        this.setMealService = setMealService;
        this.setMealDetailedService = setMealDetailedService;
        this.checkItemService = checkItemService;
    }

    @GetMapping("/getAll")
    public Result<List<SetMeal>> list() {
        List<SetMeal> setmealList = setMealService.list();
        setmealList.forEach(sm -> {
            QueryWrapper<SetMealDetailed> qw = new QueryWrapper<>();
            qw.eq("smId", sm.getSmID());
            List<SetMealDetailed> smdList = setMealDetailedService.list(qw);
            List<Integer> ciIds = smdList.stream().map(smd -> smd.getCiID()).collect(Collectors.toList());
            List<CheckItem> checkitemList = checkItemService.listByIds(ciIds);
            sm.setCheckitemList(checkitemList);
        });
        return Result.success(setmealList);
    }
}