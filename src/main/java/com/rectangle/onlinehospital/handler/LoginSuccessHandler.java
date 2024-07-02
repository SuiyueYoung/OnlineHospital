package com.rectangle.onlinehospital.handler;

import com.rectangle.onlinehospital.entity.security.SecurityUserDo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final HttpServletResponse httpServletResponse;

    public LoginSuccessHandler(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        SecurityUserDo securityUserDo = (SecurityUserDo) authentication.getPrincipal();
        log.info("User {} login successfully.", securityUserDo.getUsername());
        httpServletResponse.sendRedirect("/home");
    }
}
