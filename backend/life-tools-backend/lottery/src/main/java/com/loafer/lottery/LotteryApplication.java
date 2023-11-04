package com.loafer.lottery;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@EnableFeignClients
@ComponentScan(basePackages = {"com.loafer.common", "com.loafer.lottery"})
@MapperScan(basePackages = "com.loafer.lottery.mapper")
@SpringBootApplication
public class LotteryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LotteryApplication.class, args);
    }
}
