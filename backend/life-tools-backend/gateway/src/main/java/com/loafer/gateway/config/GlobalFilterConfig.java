package com.loafer.gateway.config;

import com.loafer.gateway.filter.AddPathPrefixFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalFilterConfig {

    //@Bean
    GlobalFilter customizeGlobalFilter() {
        return new AddPathPrefixFilter();
    }
}
