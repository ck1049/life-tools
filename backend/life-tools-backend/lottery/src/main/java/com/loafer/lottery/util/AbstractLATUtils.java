package com.loafer.lottery.util;

import java.util.*;

import static com.loafer.lottery.util.MathUtils.combinatorial;

/**
 * 大乐透和双色球抽象工具类
 *
 * @author loafer
 * @since 2024-05-16 03:48:36
 **/
public abstract class AbstractLATUtils implements LotteryUtils {

    public static Map<Long, List<RedBlueBall>> REWARD_CONDITION_MAP;

    @Override
    public Map<Long, List<RedBlueBall>> awardCondition() {
        return REWARD_CONDITION_MAP;
    }

    @Override
    public Set<Integer>[] randomLottery() {
        return randomLottery(awardRedBallNum(), awardBlueBallNum());
    }

    /**
     * 随机生成复试彩票
     *
     * @param redBallNum
     * @param blueBallNum
     * @return
     */
    public Set<Integer>[] randomLottery(long redBallNum, long blueBallNum) {
        if (!verifyLottery(redBallNum, blueBallNum)) {
            throw new IllegalArgumentException("彩票随机生成失败，请检查输入参数");
        }
        Set<Integer> redBallSet = new HashSet<>();
        Random random = new Random();
        while (redBallSet.size() < redBallNum) {
            redBallSet.add(random.nextInt(totalRedBallNum().intValue()) + 1);
        }
        Set<Integer> blueBallSet = new HashSet<>();
        while (blueBallSet.size() < blueBallNum) {
            blueBallSet.add(random.nextInt(totalBlueBallNum().intValue()) + 1);
        }

        return new Set[]{redBallSet, blueBallSet};
    }

    /**
     * 计算每个奖级的中奖注数
     *
     * @param myRedBallNum
     * @param myBlueBallNum
     * @param matchRedBallNum
     * @param matchBlueBallNum
     * @return
     */
    @Override
    public Map<Long, Long> calculateAwardLevelNumMap(long myRedBallNum, long myBlueBallNum, long matchRedBallNum, long matchBlueBallNum) {
        Map<Long, Long> awardLevelNumMap = new LinkedHashMap<>();

        REWARD_CONDITION_MAP.forEach((awardLevel, awardConditionList) -> {

            long awardNum = 0;
            for (RedBlueBall redBlueBall : awardConditionList) {
                Long redCondition = redBlueBall.getRedBallNum();
                Long blueCondition = redBlueBall.getBlueBallNum();

                // 计算满足条件的组合数
                if (matchRedBallNum >= redCondition && matchBlueBallNum >= blueCondition
                        && myRedBallNum >= matchRedBallNum + awardRedBallNum() - redCondition
                        && myBlueBallNum >= matchBlueBallNum + awardBlueBallNum() - blueCondition) {
                    awardNum += combinatorial(matchRedBallNum, redCondition)
                            * combinatorial(myRedBallNum - matchRedBallNum, awardRedBallNum() - redCondition)
                            * combinatorial(matchBlueBallNum, blueCondition)
                            * combinatorial(myBlueBallNum - matchBlueBallNum, awardBlueBallNum() - blueCondition);
                    ;
                }
            }

            awardLevelNumMap.put(awardLevel, awardNum);
        });
        return awardLevelNumMap;
    }

    /**
     * 匹配成功数量
     *
     * @param awardBalls
     * @param myBalls
     * @return
     */
    public long matchBallNum(int[] awardBalls, int[] myBalls) {
        long matchRedBallNum = 0;
        for (int awardRedBall : awardBalls) {
            for (int myRedBall : myBalls) {
                if (awardRedBall == myRedBall) {
                    matchRedBallNum++;
                    break;
                }
            }
        }
        return matchRedBallNum;
    }

    /**
     * 校验输入的投注红蓝球数是否合法
     * @param redBallNum
     * @param blueBallNum
     * @return
     */
    protected boolean verifyLottery(long redBallNum, long blueBallNum) {
        return redBallNum >= awardRedBallNum() && blueBallNum >= awardBlueBallNum()
                && redBallNum <= totalRedBallNum() && blueBallNum <= totalBlueBallNum();
    }
}
