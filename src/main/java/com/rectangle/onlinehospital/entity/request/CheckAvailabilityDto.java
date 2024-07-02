package com.rectangle.onlinehospital.entity.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class CheckAvailabilityDto {
    private Integer hpID;

    private Integer smID;

    @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd")
    private String startDate;

    @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd")
    private String endDate;
}
