package com.loafer.lottery.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * @author loafer
 * @since 2023-11-04 21:49:30
 **/
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(10000))
                .setReadTimeout(Duration.ofMillis(10000))
                .build();
    }
}
