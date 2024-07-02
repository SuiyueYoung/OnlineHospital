package com.rectangle.onlinehospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rectangle.onlinehospital.mapper.SetMealMapper;
import com.rectangle.onlinehospital.entity.SetMeal;
import com.rectangle.onlinehospital.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetMealServiceImpl extends ServiceImpl<SetMealMapper, SetMeal> implements SetMealService {

    private final SetMealMapper setMealMapper;

    @Autowired
    public SetMealServiceImpl(SetMealMapper setMealMapper) {
        this.setMealMapper = setMealMapper;
    }

}
