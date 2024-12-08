package com.loafer.genshin.callback.es;

import com.loafer.common.utils.SnowflakeUtils;
import com.loafer.genshin.model.es.HeroDoc;
import org.springframework.core.annotation.Order;
import org.springframework.data.elasticsearch.core.event.ReactiveBeforeConvertCallback;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author loafer
 * @since 2024-12-08 17:44:20
 **/
@Order(100)
@Component
public class HeroDocCallBack implements ReactiveBeforeConvertCallback<HeroDoc> {

    @Override
    public Mono<HeroDoc> onBeforeConvert(HeroDoc entity, IndexCoordinates index) {
        if (entity.getId() == null) {
            entity.setId(SnowflakeUtils.nextId());
        }
        return Mono.just(entity);
    }
}