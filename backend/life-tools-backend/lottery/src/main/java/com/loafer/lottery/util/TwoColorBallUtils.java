package com.loafer.lottery.util;

import com.loafer.lottery.enums.LotteryType;
import com.loafer.lottery.model.PrizeLevel;
import com.loafer.lottery.model.RedBlueBall;

import java.util.ArrayList;
import java.util.List;

/**
 * 双色球工具类
 * @author loafer
 * @since 2024-05-16 02:58:24
 **/
public class TwoColorBallUtils extends AbstractLATUtils {

    {
        // 非静态代码块初始化中奖条件
        rewardConditionList = new ArrayList<>();
        // 一等奖:7个号码相符(6个红色球号码和1个蓝色球号码) (红色球号码顺序不限，下同)
        rewardConditionList.add(new PrizeLevel(1L, 10000000L, List.of(new RedBlueBall(6L, 1L))));

        // 二等奖:6个红色球号码相符；
        rewardConditionList.add(new PrizeLevel(2L, 100000L, List.of(new RedBlueBall(6L, 0L))));

        // 三等奖:5个红色球号码和1个蓝色球号码相符;
        rewardConditionList.add(new PrizeLevel(3L, 3000L, List.of(new RedBlueBall(5L, 1L))));

        // 四等奖:5个红色球号码或4个红色球号码和1个蓝色球号码相符；
        rewardConditionList.add(new PrizeLevel(4L, 200L, List.of(
                new RedBlueBall(5L, 0L),
                new RedBlueBall(4L, 1L))));

        // 五等奖:4个红色球号码或3个红色球号码和1个蓝色球号码相符；
        rewardConditionList.add(new PrizeLevel(5L, 10L, List.of(
                new RedBlueBall(4L, 0L),
                new RedBlueBall(3L, 1L))));

        // 六等奖:1个蓝色球号码相符(有无红色球号码相符均可)；
        rewardConditionList.add(new PrizeLevel(6L, 5L, List.of(
                new RedBlueBall(2L, 1L),
                new RedBlueBall(1L, 1L),
                new RedBlueBall(0L, 1L))));
    }

    @Override
    public Long totalRedBallNum() {
        return LotteryType.TWO_COLOR_BALL.getRedBallsNum();
    }
    
    @Override
    public Long totalBlueBallNum() {
        return LotteryType.TWO_COLOR_BALL.getBlueBallsNum();
    }
    
    @Override
    public Long awardRedBallNum() {
            return 6L;
    }
    
    @Override
    public Long awardBlueBallNum() {
                return 1L;
    }

}
