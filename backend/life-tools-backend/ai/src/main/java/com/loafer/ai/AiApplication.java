package com.loafer.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Ai Module
 * @author 18238
 */
@SpringBootApplication
@ComponentScan("com.loafer")
public class AiApplication {

    public static void main( String[] args ) {
        SpringApplication.run(AiApplication.class, args);
    }
}
