package com.rectangle.onlinehospital.pojo;

import lombok.Data;

@Data
public class LoginRequest {
    private String userID;

    private String password;
}
