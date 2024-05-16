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

    /**
     * 中间条件
     */
    protected Map<Long, List<RedBlueBall>> rewardConditionMap;

    @Override
    public Map<Long, List<RedBlueBall>> awardCondition() {
        return rewardConditionMap;
    }

    @Override
    public Set<Integer>[] randomLottery() {
        return randomLottery(awardRedBallNum(), awardBlueBallNum());
    }

    /**
     * 随机生成复试彩票
     *
     * @param redBallNum 准备选购的红球个数
     * @param blueBallNum 准备选购的蓝球个数
     * @return 机选彩票结果
     */
    public Set<Integer>[] randomLottery(long redBallNum, long blueBallNum) {
        if (!verifyPurchaseBallNum(redBallNum, blueBallNum)) {
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

    @Override
    public Map<Long, Long> calculateAwardLevelNumMap(long myRedBallNum, long myBlueBallNum, long matchRedBallNum, long matchBlueBallNum) {

        if (!verifyPurchaseBallNum(myRedBallNum, myBlueBallNum) || !verifyMatchBallNum(matchRedBallNum, matchBlueBallNum)) {
            throw new IllegalArgumentException("计算奖金等级失败，请检查输入参数");
        }

        Map<Long, Long> awardLevelNumMap = new LinkedHashMap<>();

        rewardConditionMap.forEach((awardLevel, awardConditionList) -> {

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
                }
            }

            awardLevelNumMap.put(awardLevel, awardNum);
        });
        return awardLevelNumMap;
    }

    /**
     * 匹配成功数量
     *
     * @param awardBalls 开奖号码球
     * @param myBalls 选购的号码球
     * @return 中奖球数
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
     * @param redBallNum 准备选购的红球数
     * @param blueBallNum 准备选购的蓝球数
     * @return 校验结果
     */
    protected boolean verifyPurchaseBallNum(long redBallNum, long blueBallNum) {
        return redBallNum >= awardRedBallNum() && blueBallNum >= awardBlueBallNum()
                && redBallNum <= totalRedBallNum() && blueBallNum <= totalBlueBallNum();
    }

    /**
     * 校验输入的中奖红蓝球数是否合法
     * @param matchRedBallNum 中奖红球数
     * @param matchBlueBallNum 中奖蓝球数
     * @return 校验结果
     */
    protected boolean verifyMatchBallNum(long matchRedBallNum, long matchBlueBallNum) {
        return matchRedBallNum <= awardRedBallNum() && matchBlueBallNum <= awardBlueBallNum()
                && matchRedBallNum >= 0 && matchBlueBallNum >= 0;
    }
}
