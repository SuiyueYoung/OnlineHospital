package com.rectangle.onlinehospital;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.rectangle.onlinehospital.entity.request.CheckAvailabilityDto;
import com.rectangle.onlinehospital.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class OnlineHospitalApplicationTests {

    @Test
    void JsonTest() {
        String rules = "{\"2003-07-03\":\"200\",\"2004-03-01\":\"300\"}";
        JSONObject jsonObject = JSON.parseObject(rules);
        jsonObject.forEach((key, value) -> {
            LocalDate date = LocalDate.parse(key, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            int remaining = Integer.parseInt(value.toString());

            System.out.println("日期：" + date + " " + "余量：" + remaining);
        });
    }

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    void methodTest() {
        CheckAvailabilityDto checkAvailabilityDto = new CheckAvailabilityDto();
        checkAvailabilityDto.setHpID(1);
        checkAvailabilityDto.setSmID(1);
        LocalDate localDate = LocalDate.parse("2023-07-01");
        checkAvailabilityDto.setStartDate(localDate);
        orderService.checkAvailability(checkAvailabilityDto).getData().getAvailableDays().forEach(item -> {
            System.out.println("日期：" + item.getDate() + "余量：" + item.getRemainSlots() + "可以预约：" + item.getReserve());
        });
    }

}
