package com.rectangle.onlinehospital.filter;

import com.rectangle.onlinehospital.exception.CustomerAuthenticationException;
import com.rectangle.onlinehospital.handler.LoginFailureHandler;
import com.rectangle.onlinehospital.service.impl.UserDetailsServiceImpl;
import com.rectangle.onlinehospital.utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsServiceImpl jwtUserDetailsService;
    private final LoginFailureHandler loginFailureHandler;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil, UserDetailsServiceImpl jwtUserDetailsService, LoginFailureHandler loginFailureHandler) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.loginFailureHandler = loginFailureHandler;
    }

    /**
     * @Author Young
     * @Date 6/30/2024 3:00 PM
     * @Description JWT Authentication
     * @Param [request, response, filterChain]
     * @Return void
     * @Since version 1.0
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();
            if (!"/user/login".equals(uri) && !"/user/register".equals(uri)) {
                this.validateToken(request);
            }
        } catch (AuthenticationException e) {
            loginFailureHandler.onAuthenticationFailure(request, response, e);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * @Author Young
     * @Date 7/1/2024 9:42 AM
     * @Description validate token in JwtFilter
     * @Param [request]
     * @Return void
     * @Since version 1.0
     */
    private void validateToken(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");

        if (ObjectUtils.isEmpty(authorizationHeader)) {
            throw new CustomerAuthenticationException("Token is empty");
        }

        // JWT Token is in the form "Bearer token". Remove "Bearer " and get only the token
        String token = authorizationHeader.substring(7);

        // Validate the token
        try {
            String username = jwtTokenUtil.extractUsername(token);
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, null);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } catch (Exception e) {
            logger.error("Token authenticate failed");
            throw new CustomerAuthenticationException("Token authenticate failed");
        }
    }
}
