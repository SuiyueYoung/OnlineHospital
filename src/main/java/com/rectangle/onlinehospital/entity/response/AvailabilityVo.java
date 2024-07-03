package com.rectangle.onlinehospital.entity.response;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@ToString
public class AvailabilityVo {

    private Integer year;

    private Integer month;

    private List<availableDays> availableDays;

    //内部类
    @Data
    @ToString
    public static class availableDays {

        private LocalDate date;//当月日期

        private Integer remainSlots;//剩余预约量

        private Boolean reserve;//这天是否可以预约
    }
}
