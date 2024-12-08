package com.loafer.genshin.service;

import com.loafer.genshin.model.es.HeroDoc;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 英雄信息服务
 * @author loafer
 * @since 2024-12-08 01:25:32
 **/
public interface HeroService {

    /**
     * 索引文档
     * @param heroDoc heroDoc
     * @return result
     */
    Mono<HeroDoc> save(HeroDoc heroDoc);

    /**
     * es查询全部记录
     * @return list
     */
    Flux<HeroDoc> matchAll();
}
