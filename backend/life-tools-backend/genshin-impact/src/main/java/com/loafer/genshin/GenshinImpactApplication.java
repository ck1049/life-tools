package com.loafer.genshin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author loafer
 * @since 2024-12-08 00:14:38
 **/
@ComponentScan(basePackages = {"com.loafer.common", "com.loafer.genshin", "com.loafer.mq"})
@SpringBootApplication
public class GenshinImpactApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenshinImpactApplication.class, args);
    }
}
