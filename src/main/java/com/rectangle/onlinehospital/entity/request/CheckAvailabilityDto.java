package com.rectangle.onlinehospital.entity.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CheckAvailabilityDto {
    private Integer hpID;

    private Integer smID;

    @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd")
    private LocalDate endDate;
}
