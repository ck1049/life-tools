package com.loafer.genshin.service.impl;

import co.elastic.clients.elasticsearch._types.FieldSort;
import co.elastic.clients.elasticsearch._types.SortOrder;
import com.loafer.genshin.model.es.HeroDoc;
import com.loafer.genshin.repository.HeroRepository;
import com.loafer.genshin.service.HeroService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.data.elasticsearch.core.query.highlight.Highlight;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightField;
import org.springframework.data.elasticsearch.core.script.Script;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.*;

/**
 * 英雄表服务实现类
 *
 * @author loafer
 * @since 2024-12-08 01:26:02
 **/
@Slf4j
@Service("heroService")
public class HeroServiceImpl implements HeroService {

    @Resource(name = "heroRepository")
    private HeroRepository heroRepository;
    @Resource
    private ReactiveElasticsearchOperations reactiveElasticsearchOperations;
    private final IndexCoordinates index = IndexCoordinates.of("hero");

    @PostConstruct
    private void init() {
        // repository保存文档时创建的索引会缺失索引配置，所以这里提前创建索引
        reactiveElasticsearchOperations.indexOps(HeroDoc.class).create();
    }

    @Override
    public Mono<HeroDoc> save(HeroDoc heroDoc) {
        return reactiveElasticsearchOperations.save(heroDoc);
    }

    @Override
    public Flux<HeroDoc> bulkSave(List<HeroDoc> heroDocList) {
        return heroRepository.saveAll(heroDocList);
    }

    @Override
    public Flux<HeroDoc> matchAll() {
        return heroRepository.findAll();
    }

    @Override
    public List<SearchHit<HashMap>> searchAfterByName(String name, Long lastId) {
        HighlightQuery highlightQuery = new HighlightQuery(new Highlight(List.of(new HighlightField("name"))), HashMap.class);
        NativeQueryBuilder builder = NativeQuery.builder();
        if (StringUtils.isNotBlank(name)) {
            builder.withQuery(q -> q.match(m -> m.field("name").query(name)));
        }
        Query query = builder
                .withSort(m -> m.field(FieldSort.of(f -> f.field("id").order(SortOrder.Asc))))
                .withSearchAfter(List.of(lastId))
                .withHighlightQuery(highlightQuery)
                .build();
        return Objects.requireNonNull(reactiveElasticsearchOperations.searchForHits(query, HashMap.class, index).block())
                .getSearchHits().collectList().block();
    }

    @Override
    public List<SearchHit<HashMap>> searchAfterByNameUseScript(String name, Long lastId) {
        String nameQuery = "";
        if (StringUtils.isNotBlank(name)) {
            nameQuery = """
                    "query": {
                      "match": {
                        "name": "{{name}}"
                      }
                    },
                    """;
        }
        String dsl = """
                {
                """ + nameQuery +
                """
                          "sort": [
                            {
                              "id": {
                                "order": "asc"
                              }
                            }
                          ],
                          "search_after": [{{lastId}}],
                          "highlight": {
                            "fields": {
                              "name": {}
                            },
                            "pre_tags": "<red>",
                            "post_tags": "</red>"
                          }
                        }
                        """;
        final String scriptId = "highlight_search_by_name";
        // 发送脚本，如果已经存在该脚本，此步骤可以忽略，脚本可以被复用
        reactiveElasticsearchOperations.putScript(Script.builder()
                .withId(scriptId)
                .withLanguage("mustache")
                .withSource(dsl)
                .build()).block();
        var query = SearchTemplateQuery.builder()
                .withId(scriptId)
                .withParams(
                        StringUtils.isNotBlank(name)
                                ? Map.of("name", name, "lastId", lastId)
                                : Map.of("lastId", lastId)
                )
                .build();
        return reactiveElasticsearchOperations.search(query, HashMap.class, index).collectList().block();
    }
}
