package com.rectangle.onlinehospital.pojo;

import lombok.Data;

@Data
public class Hospital {
    private Integer hpID;
    private String name;
    private String picture;
    private String telephone;
    private String address;
    private String businessHours;
    private String deadline;
    private String rule;
    private Integer state;
}
