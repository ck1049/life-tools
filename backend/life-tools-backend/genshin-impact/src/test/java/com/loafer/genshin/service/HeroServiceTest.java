package com.loafer.genshin.service;

import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.loafer.genshin.GenshinImpactApplication;
import com.loafer.genshin.model.es.HeroDoc;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import static com.devskiller.jfairy.producer.person.Person.Sex.MALE;

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
    private final Fairy fairy = Fairy.create(Locale.CHINA);
    private static final String[] ARMS_TYPE = {"单手剑", "双手剑", "长柄武器", "弓", "法器"};
    private static final String[] ELEMENT = {"火", "冰", "雷", "水", "风", "草", "岩"};
    private static LocalDateTime createTime = LocalDateTime.now();

    /**
     * 随机生成英雄信息
     * @return HeroDoc
     */
    private HeroDoc testRandomHero() {
        Random random = new Random();
        Person person = fairy.person();
        HeroDoc hero = new HeroDoc();
        hero.setName(person.getLastName() + person.getFirstName());
        hero.setBirthday(person.getDateOfBirth());
        hero.setSex(person.getSex().equals(MALE) ? "男" : "女");
        hero.setArmsType(ARMS_TYPE[random.nextInt(ARMS_TYPE.length)]);
        hero.setElement(ELEMENT[random.nextInt(ELEMENT.length)]);
        hero.setCreateTime(createTime);
        return hero;
    }

    /**
     * 批量生成英雄信息
     * @param num 生成数量
     * @return List<HeroDoc>
     */
    private List<HeroDoc> batchRandomHero(int num) {
        List<HeroDoc> heroDocList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            heroDocList.add(testRandomHero());
        }
        return heroDocList;
    }

    /**
     * 保存英雄信息
     */
    @Test
    public void testSave() {
        heroService.save(testRandomHero()).block();
    }

    /**
     * 批量保存英雄信息
     * @throws InterruptedException 线程等待异常
     */
    @Test
    public void testBulkSave() {
        // 生成英雄数量
        int heroNum = 1_000_000_000;
        int batchSize = 10_000;
        long start = System.currentTimeMillis();
        for (int i = heroNum; i > 0; i-=batchSize) {
            createTime = LocalDateTime.now();
            heroService.bulkSave(batchRandomHero(batchSize)).blockLast();
        }
        log.info("testSave end, cost time: {}ms", System.currentTimeMillis() - start);
    }

    @Test
    public void findAll() throws JsonProcessingException {
        log.info(objectMapper.writeValueAsString(heroService.matchAll().collectList().block()));
    }

}
