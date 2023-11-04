package com.loafer.common.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author loafer
 * @since 2023-11-04 19:55:39
 **/
@Component
public class SnowflakedUtils {

    public static SnowflakeGenerator snowflakeGenerator;

    @Resource
    public void setSnowflakeGenerator(SnowflakeGenerator snowflakeGenerator) {
        this.snowflakeGenerator = snowflakeGenerator;
    }
    public static long nextId() {
        return snowflakeGenerator.next();
    }

    public static String nextStrId() {
        return String.valueOf(snowflakeGenerator.next());
    }
}
