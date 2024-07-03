package com.rectangle.onlinehospital.entity.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CheckAvailabilityDto {
    private Integer hpID;//体检医院id

    private Integer smID;//体检套餐id

    @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd")
    private LocalDate startDate;
}
