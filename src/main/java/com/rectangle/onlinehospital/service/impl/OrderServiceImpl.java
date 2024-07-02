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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        int year = checkAvailabilityDto.getStartDate().getYear();
        int month = checkAvailabilityDto.getStartDate().getMonthValue();
        AvailabilityVo availabilityVo = calenderGenerate(year, month);

        // TODO

        return Result.success(availabilityVo);
    }

    public AvailabilityVo calenderGenerate(int year, int month) {
        LocalDate firstDateOfMonth = LocalDate.of(year, month, 1);
        DayOfWeek firstDayOfWeek = firstDateOfMonth.getDayOfWeek();
        int daysInMonth = firstDateOfMonth.lengthOfMonth();

        List<AvailabilityVo.availableDays> days = new ArrayList<>();

        for (int i = 1; i <= firstDayOfWeek.getValue() % 7; i++) {
            AvailabilityVo.availableDays emptyDay = new AvailabilityVo.availableDays();
            emptyDay.setDate(null);
            emptyDay.setReserve(false);
            emptyDay.setRemainSlots(0);

            days.add(emptyDay);
        }

        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = LocalDate.of(year, month, day);
            AvailabilityVo.availableDays availableDay = new AvailabilityVo.availableDays();
            availableDay.setDate(date);
            availableDay.setReserve(false);
            availableDay.setRemainSlots(0);

            days.add(availableDay);
        }

        AvailabilityVo availabilityVo = new AvailabilityVo();
        availabilityVo.setYear(year);
        availabilityVo.setMonth(month);
        availabilityVo.setAvailableDays(days);

        return availabilityVo;
    }
}
