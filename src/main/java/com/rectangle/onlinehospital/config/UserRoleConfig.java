package com.rectangle.onlinehospital.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "user-roles")
@Data
public class UserRoleConfig {
    private Map<Integer, String> roles;

}
