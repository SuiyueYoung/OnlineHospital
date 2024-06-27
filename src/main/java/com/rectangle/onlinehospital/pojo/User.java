package com.rectangle.onlinehospital.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class User {
    private String userID;
    private String password;
    private String realName;
    private Integer sex;
    private String identityCard;
    private Date birthday;
    private Integer userType;
}
