package com.loafer.genshin.repository;

import com.loafer.genshin.model.es.HeroDoc;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

/**
 * @author loafer
 * @since 2024-12-08 01:23:59
 **/
public interface HeroRepository extends ReactiveElasticsearchRepository<HeroDoc, Long> {
}
