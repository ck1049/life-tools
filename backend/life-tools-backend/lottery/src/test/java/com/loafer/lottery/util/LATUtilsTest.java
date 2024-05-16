package com.loafer.lottery.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 双色球/大乐透工具测试类
 * @author loafer
 * @since 2024-05-16 16:40:22
 **/
public class LATUtilsTest {

    int[] awardRedBalls = new int[] {7, 17, 22, 30, 34};
    int[] awardBlueBalls = new int[] {5, 7};
    AbstractLATUtils lottoUtils = new LottoUtils();

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

        System.out.println("开奖号码：" + Arrays.toString(awardRedBalls) + " " + Arrays.toString(awardBlueBalls));
        System.out.println("我的号码：" + Arrays.toString(myRedBalls) + " " + Arrays.toString(myBlueBalls));
        System.out.println("号码生成耗时：" + (end - start) + "ms.");

    }

    /**
     * 根据单/复式类型和匹配号码计算中奖等级情况
     * @throws JsonProcessingException
     */
    @Test
    public void testAwardAnalysis() throws JsonProcessingException {

        long myRedBallNum = 6;
        long myBlueBallNum = 3;
        long matchRedBallNum = 3;
        long matchBlueBallNum = 2;
        long start = System.currentTimeMillis();
        Map<Long, Long> awardLevelNumMap = lottoUtils.calculateAwardLevelNumMap(myRedBallNum, myBlueBallNum, matchRedBallNum, matchBlueBallNum);
        long end = System.currentTimeMillis();
        System.out.println(new ObjectMapper().writeValueAsString(awardLevelNumMap));
        System.out.println("计算耗时：" + (end - start) + "ms.");
    }
}
