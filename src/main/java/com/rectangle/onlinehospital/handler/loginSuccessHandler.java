package com.rectangle.onlinehospital.handler;

import com.rectangle.onlinehospital.pojo.SecurityUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class loginSuccessHandler implements AuthenticationSuccessHandler {
    private final HttpServletResponse httpServletResponse;

    public loginSuccessHandler(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        log.info("User {} login successfully.", securityUser.getUsername());
        httpServletResponse.sendRedirect("/home");
    }
}
