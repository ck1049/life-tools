package com.loafer.common.utils;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author loafer
 * @since 2023-11-04 19:55:39
 **/
@Component
public class SnowflakeUtils {

    public static SnowflakeGenerator snowflakeGenerator;

    @Resource
    public void setSnowflakeGenerator(SnowflakeGenerator snowflakeGenerator) {
        SnowflakeUtils.snowflakeGenerator = snowflakeGenerator;
    }
    public static long nextId() {
        return SnowflakeUtils.snowflakeGenerator.next();
    }

    public static String nextStrId() {
        return String.valueOf(SnowflakeUtils.snowflakeGenerator.next());
    }
}
