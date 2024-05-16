package com.loafer.lottery.util;

import com.loafer.lottery.enums.LotteryType;
import com.loafer.lottery.model.PrizeLevel;
import com.loafer.lottery.model.RedBlueBall;

import java.util.*;

/**
 * 大乐透工具类
 * @author loafer
 * @since 2024-05-16 02:58:24
 **/
public class LottoUtils extends AbstractLATUtils {

    {
        // 非静态代码块初始化单倍追加奖金比例、中奖条件
        appendAwardRatio = 0.8F;

        rewardConditionList = new ArrayList<>();
        // 一等奖：投注号码与当期开奖号码全部相同(顺序不限，下同)，即中奖；
        rewardConditionList.add(new PrizeLevel(1L, 10000000L, List.of(new RedBlueBall(5L, 2L))));

        // 二等奖：投注号码与当期开奖号码中的五个前区号码及任意一个后区号码相同，即中奖；
        rewardConditionList.add(new  PrizeLevel(2L, 200000L, List.of(new RedBlueBall(5L, 1L))));

        // 三等奖：投注号码与当期开奖号码中的五个前区号码相同，即中奖；
        rewardConditionList.add(new  PrizeLevel(3L, 10000L, List.of(new RedBlueBall(5L, 0L))));

        // 四等奖：投注号码与当期开奖号码中的任意四个前区号码及两个后区号码相同，即中奖；
        rewardConditionList.add(new  PrizeLevel(4L, 3000L, List.of(new RedBlueBall(4L, 2L))));

        // 五等奖：投注号码与当期开奖号码中的任意四个前区号码及任意一个后区号码相同，即中奖；
        rewardConditionList.add(new  PrizeLevel(5L, 300L, List.of(new RedBlueBall(4L, 1L))));

        // 六等奖：投注号码与当期开奖号码中的任意三个前区号码及两个后区号码相同，即中奖；
        rewardConditionList.add(new  PrizeLevel(6L, 200L, List.of(new RedBlueBall(3L, 2L))));

        // 七等奖：投注号码与当期开奖号码中的任意四个前区号码相同，即中奖；
        rewardConditionList.add(new  PrizeLevel(7L, 100L, List.of(new RedBlueBall(4L, 0L))));

        // 八等奖：投注号码与当期开奖号码中的任意三个前区号码及任意一个后区号码相同，或者任意两个前区号码及两个后区号码相同，即中奖；
        rewardConditionList.add(new  PrizeLevel(8L, 15L, List.of(
                new RedBlueBall(3L, 1L),
                new RedBlueBall(2L, 2L))));

        // 九等奖：投注号码与当期开奖号码中的任意三个前区号码相同，或者任意一个前区号码及两个后区号码相同，
        // 或者任意两个前区号码及任意一个后区号码相同，或者两个后区号码相同，即中奖。
        rewardConditionList.add(new  PrizeLevel(9L, 5L, List.of(
                new RedBlueBall(3L, 0L),
                new RedBlueBall(1L, 2L),
                new RedBlueBall(2L, 1L),
                new RedBlueBall(0L, 2L))));
    }

    @Override
    public Long totalRedBallNum() {
        return LotteryType.LOTTO.getRedBallsNum();
    }
    
    @Override
    public Long totalBlueBallNum() {
        return LotteryType.LOTTO.getBlueBallsNum();
    }
    
    @Override
    public Long awardRedBallNum() {
            return 5L;
    }
    
    @Override
    public Long awardBlueBallNum() {
                return 2L;
    }
}
