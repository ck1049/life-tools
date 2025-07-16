package com.loafer.config;

import jakarta.validation.constraints.Pattern;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author loafer
 * @since 2025-03-09 21:44:47
 **/
@ConfigurationProperties(prefix = "security.authentication")
public class AuthenticationProperties {

    @Pattern(regexp = "enable|disable", message = "custom must be enable or disable")
    private String custom = "disable";

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }
}
