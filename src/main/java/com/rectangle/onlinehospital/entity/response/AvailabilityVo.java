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

    @Data
    @ToString
    public static class availableDays {

        private LocalDate date;

        private Integer remainSlots;

        private Boolean reserve;
    }
}
