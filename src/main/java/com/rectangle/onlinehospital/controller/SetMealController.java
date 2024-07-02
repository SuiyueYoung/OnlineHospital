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

    /**
     * @Author X1a0hu
     * @Date 2024/7/2 上午10:02
     * @Description
     * @Param [setMealService, setMealDetailedService, checkItemService]
     * @Return
     * @Since version 1.0
     */
    @Autowired
    public SetMealController(SetMealService setMealService, SetMealDetailedService setMealDetailedService, CheckItemService checkItemService) {
        this.setMealService = setMealService;
        this.setMealDetailedService = setMealDetailedService;
        this.checkItemService = checkItemService;
    }

    /**
     * @Author X1a0hu
     * @Date 2024/7/2 上午10:01
     * @Description
     * @Param
     * @Return
     * @Since version 1.0
     */
    @GetMapping("/getAll")
    public Result<List<SetMeal>> getAll() {
        List<SetMeal> setmealList = setMealService.list();//所有体检套餐
        setmealList.forEach(sm -> {//sm就是循环取出集合中每个 体检套餐对象
            QueryWrapper<SetMealDetailed> qw = new QueryWrapper<>();
            qw.eq("smId", sm.getSmID());
            List<SetMealDetailed> smdList = setMealDetailedService.list(qw);
            //循环取出 关联表对象smd中检查项id ci_id
            //集合中存放是 所有checkItem 的检查项id
            List<Integer> ciIds = smdList.stream().map(smd -> smd.getCiID()).collect(Collectors.toList());
            //使用查询出的套餐 所关联的  检查项id，到检查项checkItem表中查询具体数据
            List<CheckItem> checkitemList = checkItemService.listByIds(ciIds);
            sm.setCheckitemList(checkitemList);
        });
        //使用集合查询 集合中体检套餐 所包含的 检查项的 id（多个）
        return Result.success(setmealList);
    }

    @GetMapping("/getest")
    public Result<String> get() {
        return Result.success("success");
    }
}