package com.rectangle.onlinehospital.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class Order {
    private Integer orderID;
    private Date orderDate;
    private String userID;
    private Integer hpID;
    private Integer smID;
    private Integer state;
}
