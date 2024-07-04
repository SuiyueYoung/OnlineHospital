package com.rectangle.onlinehospital.entity.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PhyExamReserveVo {
    private Integer OrderID;

    private String phoneNumber;

    private String realName;

    private Integer sex;

    private Integer setMealType;

    private String hospitalName;

    private LocalDate physicalExaminationDate;
}
