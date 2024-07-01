package com.rectangle.onlinehospital.exception;

import org.springframework.security.core.AuthenticationException;

public class CustomerAuthenticationException extends AuthenticationException {

    /**
     * @Author Young
     * @Date 7/1/2024 3:48 PM
     * @Description auth exception
     * @Param [msg]
     * @Return
     * @Since version 1.0
     */
    public CustomerAuthenticationException(String msg) {
        super(msg);
    }
}
