package com.loafer.genshin.config;

import com.loafer.genshin.repository.EsRepositoryPackage;
import lombok.SneakyThrows;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

import javax.net.ssl.SSLContext;
import java.security.SecureRandom;

/**
 * @author loafer
 * @since 2024-12-08 10:29:15
 **/
@Configuration
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "dev")
@EnableReactiveElasticsearchRepositories(basePackageClasses = {EsRepositoryPackage.class})
public class ElasticSearchConFiguration extends ReactiveElasticsearchConfiguration {

    @Value("${spring.elasticsearch.username}")
    String username;
    @Value("${spring.elasticsearch.password}")
    String password;

    @SneakyThrows
    @Override
    public ClientConfiguration clientConfiguration() {
        // 使用了SSLContextBuilder的loadTrustMaterial方法，传入了null作为信任的证书，并且提供了一个总是返回true的信任策略,这样就关闭了SSL证书检查。
        // 请注意，关闭SSL证书检查会降低安全性，因此在生产环境中应当谨慎使用。
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(null, (chain, authType) -> true)
                .setSecureRandom(new SecureRandom())
                .build();

        return ClientConfiguration.builder()
                .connectedTo("127.0.0.1:9200")
                .usingSsl(sslContext)
                .withBasicAuth(username, password)
                .build();
    }

}
