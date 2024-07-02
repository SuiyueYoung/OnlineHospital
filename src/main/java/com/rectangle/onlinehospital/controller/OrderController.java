package com.rectangle.onlinehospital.controller;

import com.rectangle.onlinehospital.entity.request.CheckAvailabilityDto;
import com.rectangle.onlinehospital.entity.response.AvailabilityVo;
import com.rectangle.onlinehospital.service.OrderService;
import com.rectangle.onlinehospital.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/checkAvailability")
    public Result<AvailabilityVo> checkAvailability(@RequestBody CheckAvailabilityDto checkAvailabilityDto) {
        return orderService.checkAvailability(checkAvailabilityDto);
    }
}
