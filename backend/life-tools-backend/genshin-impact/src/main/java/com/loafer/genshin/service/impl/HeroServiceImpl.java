package com.loafer.genshin.service.impl;

import com.loafer.genshin.model.es.HeroDoc;
import com.loafer.genshin.repository.HeroRepository;
import com.loafer.genshin.service.HeroService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 英雄表服务实现类
 * @author loafer
 * @since 2024-12-08 01:26:02
 **/
@Service("heroService")
public class HeroServiceImpl implements HeroService {

    @Resource(name = "heroRepository")
    HeroRepository heroRepository;

    @Override
    public Mono<HeroDoc> save(HeroDoc heroDoc){
        return heroRepository.save(heroDoc);
    }

    @Override
    public Flux<HeroDoc> matchAll() {
        return heroRepository.findAll();
    }
}
