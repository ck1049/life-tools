package com.loafer.oss.config;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyuncs.exceptions.ClientException;
import jakarta.annotation.Resource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {OssProperties.class})
public class OssConfig {

    @Resource(name = "ossProperties")
    private OssProperties ossProperties;

    @Bean
    public OSS ossClient() throws ClientException {
        // 创建OSSClient实例。
        ClientBuilderConfiguration configuration = new ClientBuilderConfiguration();
        // 设置超时时间5s
        configuration.setConnectionTimeout(5000);
        return new OSSClientBuilder().build(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getSecretAccessKey(), configuration);
    }


}
