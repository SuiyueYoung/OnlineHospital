package com.rectangle.onlinehospital.pojo;

import lombok.Data;

@Data
public class CheckItemDetailed {
    private String cdID;
    private String name;
    private String unit;
    private String minRange;
    private String maxRange;
    private String normalValue;
    private String normalValueString;
    private String type;
    private String ciID;
    private String remarks;
}
