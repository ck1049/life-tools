package com.loafer.mq.config;

import com.loafer.mq.producer.StreamProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: mq配置
 * @author loafer
 * @since 2025-01-19 16:13:24
 **/
@Configuration
public class MqConfiguration {

    @Bean
    public StreamProducer streamProducer() {
        return new StreamProducer();
    }
}
