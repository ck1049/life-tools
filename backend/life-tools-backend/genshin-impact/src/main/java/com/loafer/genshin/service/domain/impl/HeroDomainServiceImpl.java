package com.loafer.genshin.service.domain.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loafer.genshin.api.request.HeroSaveRequest;
import com.loafer.genshin.api.response.CommonResponse;
import com.loafer.genshin.model.es.HeroDoc;
import com.loafer.genshin.service.HeroService;
import com.loafer.genshin.service.domain.HeroDomainService;
import com.loafer.mq.producer.StreamProducer;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

import static com.loafer.common.exception.enums.GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR;

/**
 * @author loafer
 * @since 2024-12-08 18:17:25
 **/
@Service
public class HeroDomainServiceImpl implements HeroDomainService {

    @Resource
    private HeroService heroService;
    @Resource
    private ModelMapper modelMapper;
    @Resource
    private StreamProducer streamProducer;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public CommonResponse<String> save(HeroSaveRequest heroSaveRequest) throws JsonProcessingException {
        HeroDoc heroDoc = heroService.save(modelMapper.map(heroSaveRequest, HeroDoc.class)).block();
        if (heroDoc != null) {
            streamProducer.send("hero-out-0", "hero-save", objectMapper.writeValueAsString(heroDoc));
            return CommonResponse.of("保存英雄信息成功");
        } else {
            return CommonResponse.fail(INTERNAL_SERVER_ERROR.getCode(), "保存英雄信息失败");
        }
    }

    @Override
    public CommonResponse<List<SearchHit<HashMap>>> searchAfterByName(String name, Long lastId) {
        List<SearchHit<HashMap>> searchHits = heroService.searchAfterByName(name, lastId);
        return CommonResponse.of(searchHits);
    }

    @Override
    public CommonResponse<List<SearchHit<HashMap>>> searchAfterByNameUseScript(String name, Long lastId) {
        List<SearchHit<HashMap>> searchHits = heroService.searchAfterByNameUseScript(name, lastId);
        return CommonResponse.of(searchHits);
    }
}
