package com.loafer.common.config;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.loafer.common.utils.IPAddressUtil;
import jakarta.annotation.Resource;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

/**
 * @author loafer
 * @since 2023-11-04 20:48:08
 **/
@Configuration
@EnableScheduling
public class SnowflakeConfig {

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;
    public static Integer WORKER_ID;
    public static String SERVER_IP_ADDRESS = IPAddressUtil.getServerIPAddress();

    public static Long DURATION = 1000 * 60 * 60 * 24L;;

    public static String KEY_PREFIX = "snowflake:";

    @Bean
    public SnowflakeGenerator snowflakeIdWorker() {
        for (int workerId = 0; workerId < 32; workerId++) {
            String ip = redisTemplate.boundValueOps(KEY_PREFIX + workerId).get();
            if (StringUtils.isBlank(ip) || SERVER_IP_ADDRESS.equals(ip)) {
                redisTemplate.boundValueOps(KEY_PREFIX + workerId).set(SERVER_IP_ADDRESS, DURATION, TimeUnit.MILLISECONDS);
                WORKER_ID = workerId;
                return new SnowflakeGenerator(workerId, 1);
            }
            workerId++;
        }
        throw new IndexOutOfBoundsException("雪花id生成器机器号越界！");
    }

    @Scheduled(cron = "0 0 5 * * ?")
    public void resetWorkerId() {
        redisTemplate.boundValueOps(KEY_PREFIX + WORKER_ID).set(SERVER_IP_ADDRESS, DURATION, TimeUnit.MILLISECONDS);
    }

}
