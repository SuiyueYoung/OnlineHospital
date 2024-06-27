package com.rectangle.onlinehospital.pojo;

import lombok.Data;

@Data
public class CiDetailedReport {
    private String cidrId;
    private String name;
    private String unit;
    private String minRange;
    private String maxRange;
    private String normalValue;
    private String normalValueString;
    private String type;
    private String value;
    private String siError;
    private String ciId;
    private String orderId;
}
