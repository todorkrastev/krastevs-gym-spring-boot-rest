package com.todorkrastev.krastevsgymrest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    private String secret;

    public JwtConfig() {
    }

    public String getSecret() {
        return secret;
    }

    public JwtConfig setSecret(String secret) {
        this.secret = secret;
        return this;
    }
}
