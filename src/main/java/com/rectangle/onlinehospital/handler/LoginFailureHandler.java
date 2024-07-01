package com.rectangle.onlinehospital.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.rectangle.onlinehospital.utils.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    /**
     * @Author Young
     * @Date 7/1/2024 10:12 AM
     * @Description Run LoginFailureHandler if user fail to login
     * @Param [request, response, exception]
     * @Return void
     * @Since version 1.0
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json:charset=STF-8");

        ServletOutputStream outputStream = response.getOutputStream();
        String message = null;
        int code = 500;

        if (exception instanceof AccountExpiredException) {
            message = "User overdue";
        } else if (exception instanceof BadCredentialsException) {
            message = "Username or password error";
        } else if (exception instanceof CredentialsExpiredException) {
            message = "password overdue";
        } else if (exception instanceof DisabledException) {
            message = "Account denied";
        } else if (exception instanceof LockedException) {
            message = "Account locked";
        } else if (exception instanceof InsufficientAuthenticationException) {
            message = "Account not exits";
        } else {
            message = "Login error";
        }

        String result2Json = JSON.toJSONString(Result.error(message).code(code), SerializerFeature.DisableCircularReferenceDetect);
        outputStream.write(result2Json.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
