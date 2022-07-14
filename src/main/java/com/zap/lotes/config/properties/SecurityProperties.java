package com.zap.lotes.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    private String jwtKey;
    private String username;
    private String password;
}
