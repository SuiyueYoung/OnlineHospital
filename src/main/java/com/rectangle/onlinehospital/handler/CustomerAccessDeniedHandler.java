package com.rectangle.onlinehospital.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.rectangle.onlinehospital.utils.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class CustomerAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * @Author Young
     * @Date 7/1/2024 3:39 PM
     * @Description
     * @Param [request, response, accessDeniedException]
     * @Return void
     * @Since version 1.0
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream servletOutputStream = response.getOutputStream();
        String result = JSON.toJSONString(
                Result.error(null).code(700).message("Lower power to access"),
                SerializerFeature.DisableCircularReferenceDetect
        );
        servletOutputStream.write(result.getBytes(StandardCharsets.UTF_8));
        servletOutputStream.flush();
        servletOutputStream.close();
    }
}
