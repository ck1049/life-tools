package com.loafer.ai.config;

import com.alibaba.cloud.ai.dashscope.embedding.DashScopeEmbeddingModel;
import jakarta.annotation.Resource;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author loafer
 * @since 2025-03-06 21:42:41
 **/
@Configuration
public class EmbeddingModelConfig {

    @Resource(type = OllamaEmbeddingModel.class)
    private EmbeddingModel embeddingModel;

/*    @Bean
    public EmbeddingModel embeddingModel() {
        // 返回你希望作为默认的 EmbeddingModel 实例
        return embeddingModel;
    }*/

}
