package com.loafer.lottery.util;

import com.loafer.lottery.model.PrizeLevel;

import java.util.List;
import java.util.Set;

/**
 * 彩票工具接口
 * @author loafer
 * @since 2024-05-16 02:57:50
 **/
public interface LotteryUtils {

    /**
     * 单注成本
     */
    Long singlePrice = 2L;

    /**
     * @return 最大可选红球个数
     */
    Long totalRedBallNum();

    /**
     * @return 最大可选蓝球个数
     */
    Long totalBlueBallNum();

    /**
     * @return 标准开奖红球数
     */
    Long awardRedBallNum();

    /**
     * @return 标准开奖蓝球数
     */
    Long awardBlueBallNum();

    /**
     * 获取各奖级中奖条件
     * @return 各奖级中奖条件
     */
    List<PrizeLevel> awardCondition();

    /**
     * 随机生成复试彩票
     * @return 机选(单式)彩票结果
     */
    Set<Integer>[] randomLottery();

}
