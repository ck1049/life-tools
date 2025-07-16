package com.loafer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author loafer
 * @since 2025-03-09 21:15:36
 **/
@ConfigurationProperties(prefix = "security")
public class SecurityConfigurationProperties {

    private AuthenticationProperties authentication;

    public AuthenticationProperties getAuthentication() {
        return authentication;
    }

    public void setAuthentication(AuthenticationProperties authentication) {
        this.authentication = authentication;
    }
}


