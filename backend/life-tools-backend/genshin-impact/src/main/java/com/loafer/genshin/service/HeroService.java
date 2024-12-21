package com.loafer.genshin.service;

import com.loafer.genshin.model.es.HeroDoc;
import org.springframework.data.elasticsearch.core.SearchHit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.HashMap;
import java.util.List;

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
     * 批量插入文档
     * @param heroDocList heroDocList
     */
    Flux<HeroDoc> bulkSave(List<HeroDoc> heroDocList);

    /**
     * es查询全部记录
     * @return list
     */
    Flux<HeroDoc> matchAll();

    /**
     * 根据姓名查询分页信息
     * @param name 姓名
     * @param lastId 上次查询最后一条记录的id
     * @return 查询结果
     */
    List<SearchHit<HashMap>> searchAfterByName(String name, Long lastId);

    /**
     * 根据姓名查询分页信息(使用script)
     * @param name 姓名
     * @param lastId 上次查询最后一条记录的id
     * @return 查询结果
     */
    List<SearchHit<HashMap>> searchAfterByNameUseScript(String name, Long lastId);
}
