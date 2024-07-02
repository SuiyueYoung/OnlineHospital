package com.rectangle.onlinehospital.entity.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailabilityVo {

    private Integer year;

    private Integer month;

    @Data
    private static class days {

        private LocalDate date;

        private Integer remainSlots;

        private Boolean reserve;
    }
}
