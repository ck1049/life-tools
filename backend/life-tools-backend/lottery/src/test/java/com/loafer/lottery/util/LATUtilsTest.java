package com.loafer.lottery.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 双色球/大乐透工具测试类
 * @author loafer
 * @since 2024-05-16 16:40:22
 **/
@Slf4j
public class LATUtilsTest {

    int[] awardRedBalls = new int[] {7, 17, 22, 30, 34};
    int[] awardBlueBalls = new int[] {5, 7};
    AbstractLATUtils lottoUtils = new LottoUtils();
    AbstractLATUtils twoColorBallUtils = new TwoColorBallUtils();

    /**
     * 随机生成双色球/大乐透号码
     */
    @Test
    public void testRandomLottery() {

        long start = System.currentTimeMillis();
        Set<Integer>[] randomLottery = lottoUtils.randomLottery(6, 3);
        int[] myRedBalls = randomLottery[0].stream().mapToInt(Integer::intValue).sorted().toArray();
        int[] myBlueBalls = randomLottery[1].stream().mapToInt(Integer::intValue).sorted().toArray();
        long end = System.currentTimeMillis();

        log.info("大乐透开奖号码：{} {}", Arrays.toString(awardRedBalls), Arrays.toString(awardBlueBalls));
        log.info("我的大乐透号码：{} {}", Arrays.toString(myRedBalls), Arrays.toString(myBlueBalls));
        log.info("大乐透号码生成耗时：{}ms.", end - start);
        
        start = System.currentTimeMillis();
        randomLottery = twoColorBallUtils.randomLottery(7, 2);
        myRedBalls = randomLottery[0].stream().mapToInt(Integer::intValue).sorted().toArray();
        myBlueBalls = randomLottery[1].stream().mapToInt(Integer::intValue).sorted().toArray();
        end = System.currentTimeMillis();
        log.info("我的双色球号码：{} {}", Arrays.toString(myRedBalls), Arrays.toString(myBlueBalls));
        log.info("双色球号码生成耗时：{}ms.", end - start);
    }

    /**
     * 根据单/复式类型和匹配号码计算中奖等级情况
     * @throws JsonProcessingException json序列化异常
     */
    @Test
    public void testCalculateAwardSituation() throws JsonProcessingException {

        long myRedBallNum = 6;
        long myBlueBallNum = 3;
        long matchRedBallNum = 3;
        long matchBlueBallNum = 2;
        long start = System.currentTimeMillis();
        Map<Long, Long> awardLevelNumMap = lottoUtils.calculateAwardLevelNumMap(myRedBallNum, myBlueBallNum, matchRedBallNum, matchBlueBallNum);
        long end = System.currentTimeMillis();
        log.info(new ObjectMapper().writeValueAsString(awardLevelNumMap));
        log.info("大乐透中奖情况计算耗时：{}ms.", end - start);

        myRedBallNum = 7;
        myBlueBallNum = 2;
        matchRedBallNum = 2;
        matchBlueBallNum = 1;
        start = System.currentTimeMillis();
        awardLevelNumMap = twoColorBallUtils.calculateAwardLevelNumMap(myRedBallNum, myBlueBallNum, matchRedBallNum, matchBlueBallNum);
        end = System.currentTimeMillis();
        log.info(new ObjectMapper().writeValueAsString(awardLevelNumMap));
        log.info("双色球中奖情况计算耗时：{}ms.", end - start);
    }
}
