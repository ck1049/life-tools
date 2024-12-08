package com.loafer.genshin.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author loafer
 * @since 2024-12-08 13:57:44
 **/
@Getter
@Setter
@RefreshScope
@Component("genshinNacosConfigProperties")
public class NacosConfigProperties {

    @Value("${server-name}")
    private String serverName;
}
