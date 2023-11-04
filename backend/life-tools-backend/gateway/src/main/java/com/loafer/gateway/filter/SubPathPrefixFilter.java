package com.loafer.gateway.filter;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 删除/gateway前缀
 */
@Data
public class SubPathPrefixFilter implements GlobalFilter, Ordered {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String originalPath = exchange.getRequest().getPath().toString();

        String filteredPath = originalPath.replace(contextPath, "");

        ServerHttpRequest originalRequest = exchange.getRequest();
        ServerHttpRequest modifiedRequest = originalRequest.mutate().path(filteredPath).build();

        return chain.filter(exchange.mutate().request(modifiedRequest).build());
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
