package com.rectangle.onlinehospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rectangle.onlinehospital.entity.Hospital;
import com.rectangle.onlinehospital.entity.Order;
import com.rectangle.onlinehospital.entity.request.CheckAvailabilityDto;
import com.rectangle.onlinehospital.entity.response.AvailabilityVo;
import com.rectangle.onlinehospital.mapper.OrderMapper;
import com.rectangle.onlinehospital.service.HospitalService;
import com.rectangle.onlinehospital.service.OrderService;
import com.rectangle.onlinehospital.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final OrderMapper orderMapper;

    private final HospitalService hospitalService;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, HospitalService hospitalService) {
        this.orderMapper = orderMapper;
        this.hospitalService = hospitalService;
    }

    @Override
    public Result<AvailabilityVo> checkAvailability(CheckAvailabilityDto checkAvailabilityDto) {
        Hospital hospital = hospitalService.getById(checkAvailabilityDto.getHpID());
        String[] rules = hospital.getRule().split(",");
        Map<Integer, Integer> reserve = new HashMap<>();
        for (int i = 0; i < rules.length; i++) {
            reserve.put(i, Integer.valueOf(rules[i]));
        }
        return null;
    }
}
