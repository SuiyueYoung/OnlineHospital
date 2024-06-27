package com.rectangle.onlinehospital.pojo;

import lombok.Data;

@Data
public class Doctor {
    private Integer docID;
    private String docCode;
    private String realName;
    private String password;
    private Integer sex;
    private Integer deptno;
}
