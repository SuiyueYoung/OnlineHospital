package com.rectangle.onlinehospital.interceptor;

import com.rectangle.onlinehospital.utils.JwtUtil;
import com.rectangle.onlinehospital.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 令牌验证
        String token = request.getHeader("Authorization");
        try {
            // 解析令牌
            Map<String, Object> claims = JwtUtil.parseToken(token);
            if ((Integer) claims.get("userCategory") == 0) {
                ThreadLocalUtil.set(claims);
                return true;
            } else {
                response.setStatus(401);
                return false;
            }
        } catch (Exception ignore) {
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
