package com.loafer.lottery.util;

import com.loafer.lottery.enums.LotteryType;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 双色球工具类
 * @author loafer
 * @since 2024-05-16 02:58:24
 **/
public class TwoColorBallUtils extends AbstractLATUtils {

    {
        rewardConditionMap = new LinkedHashMap<>();
        // 一等奖:7个号码相符(6个红色球号码和1个蓝色球号码) (红色球号码顺序不限，下同)
        rewardConditionMap.put(1L, List.of(new RedBlueBall(6L, 1L)));

        // 二等奖:6个红色球号码相符；
        rewardConditionMap.put(2L, List.of(new RedBlueBall(6L, 0L)));

        // 三等奖:5个红色球号码和1个蓝色球号码相符;
        rewardConditionMap.put(3L, List.of(new RedBlueBall(5L, 1L)));

        // 四等奖:5个红色球号码或4个红色球号码和1个蓝色球号码相符；
        rewardConditionMap.put(4L, List.of(
                new RedBlueBall(5L, 0L),
                new RedBlueBall(4L, 1L)));

        // 五等奖:4个红色球号码或3个红色球号码和1个蓝色球号码相符；
        rewardConditionMap.put(5L, List.of(
                new RedBlueBall(4L, 0L),
                new RedBlueBall(3L, 1L)));

        // 六等奖:1个蓝色球号码相符(有无红色球号码相符均可)；
        rewardConditionMap.put(6L, List.of(
                new RedBlueBall(2L, 1L),
                new RedBlueBall(1L, 1L),
                new RedBlueBall(0L, 1L)));
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
