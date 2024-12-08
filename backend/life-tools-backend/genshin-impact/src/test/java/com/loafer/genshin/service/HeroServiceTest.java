package com.loafer.genshin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.loafer.genshin.GenshinImpactApplication;
import com.loafer.genshin.model.es.HeroDoc;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;

/**
 * @author loafer
 * @since 2024-12-08 01:33:01
 **/
@Slf4j
@SpringBootTest(classes = GenshinImpactApplication.class)
public class HeroServiceTest {

    @Resource
    private HeroService heroService;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Test
    public void testSave() {
        HeroDoc hero = new HeroDoc();
//        hero.setId(1L);
        hero.setName("雷电影");
        hero.setBirthday(LocalDate.of(1000, 6, 26));
        hero.setSex("女");
        hero.setArmsType("长柄武器");
        hero.setElement("雷");
        HeroDoc heroDoc = heroService.save(hero).block();
        log.info("testSave result: {}", heroDoc);
    }

    @Test
    public void findAll() throws JsonProcessingException {
        log.info(objectMapper.writeValueAsString(heroService.matchAll().collectList().block()));
    }
}
