package com.loafer.lottery.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 彩票工具接口
 * @author loafer
 * @since 2024-05-16 02:57:50
 **/
public interface LotteryUtils {

    /**
     * 获取可投注红球总数
     * @return
     */
    Long totalRedBallNum();

    /**
     * 获取可投注蓝球总数
     * @return
     */
    Long totalBlueBallNum();

    /**
     * 标准开奖红球数
     * @return
     */
    Long awardRedBallNum();

    /**
     * 标准开奖蓝球数
     * @return
     */
    Long awardBlueBallNum();

    /**
     * 获取各奖级中奖条件
     * @return
     */
    Map<Long, List<RedBlueBall>> awardCondition();

    /**
     * 随机生成彩票
     * @return
     */
    Set<Integer>[] randomLottery();

    /**
     * 计算各奖级中奖数量
     *
     * @param myRedBallNum     投注的红球数量
     * @param myBlueBallNum
     * @param matchRedBallNum
     * @param matchBlueBallNum
     * @return
     */
    Map<Long, Long> calculateAwardLevelNumMap(long myRedBallNum, long myBlueBallNum, long matchRedBallNum, long matchBlueBallNum);
}
