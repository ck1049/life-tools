package com.loafer.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
/**
 * @author loafer
 * @since 2025-03-08 17:52:27
 **/
@Configuration
@ConfigurationPropertiesScan(basePackageClasses = SecurityConfigurationProperties.class)
public class CommonSecurityConfiguration {

    @PostConstruct
    public void init() {
        System.out.println("CommonSecurityConfiguration init");
    }
}
