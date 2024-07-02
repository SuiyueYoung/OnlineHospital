package com.rectangle.onlinehospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rectangle.onlinehospital.entity.Order;
import com.rectangle.onlinehospital.entity.request.CheckAvailabilityDto;
import com.rectangle.onlinehospital.entity.response.AvailabilityVo;
import com.rectangle.onlinehospital.utils.Result;

public interface OrderService extends IService<Order> {
    Result<AvailabilityVo> checkAvailability(CheckAvailabilityDto checkAvailabilityDto);
}
