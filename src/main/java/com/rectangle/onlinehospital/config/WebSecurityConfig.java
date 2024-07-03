package com.rectangle.onlinehospital.config;

import com.rectangle.onlinehospital.handler.CustomerAccessDeniedHandler;
import com.rectangle.onlinehospital.filter.JwtAuthenticationFilter;
import com.rectangle.onlinehospital.handler.LoginFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final LoginFailureHandler loginFailureHandler;

    private final CustomerAccessDeniedHandler customerAccessDeniedHandler;

    @Autowired
    public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, LoginFailureHandler loginFailureHandler, CustomerAccessDeniedHandler customerAccessDeniedHandler) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.loginFailureHandler = loginFailureHandler;
        this.customerAccessDeniedHandler = customerAccessDeniedHandler;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @Author Young
     * @Date 7/1/2024 10:08 AM
     * @Description Web Config
     * @Param [http]
     * @Return org.springframework.security.web.SecurityFilterChain
     * @Since version 1.0
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.formLogin(configurer -> configurer.failureHandler(loginFailureHandler));

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/user/login").permitAll()
                .requestMatchers("/user/register").permitAll()
                .anyRequest().authenticated()
        );

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling(configure -> configure.accessDeniedHandler(customerAccessDeniedHandler));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}