package com.loafer.lottery.util;

import com.loafer.lottery.model.AwardAnalysis;
import com.loafer.lottery.model.PrizeLevel;
import com.loafer.lottery.model.RedBlueBall;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static com.loafer.lottery.util.MathUtils.combinatorial;

/**
 * 大乐透和双色球抽象工具类
 *
 * @author loafer
 * @since 2024-05-16 03:48:36
 **/
public abstract class AbstractLATUtils implements LotteryUtils {


    protected float appendAwardRatio = 0;

    /**
     * 中间条件
     */
    protected List<PrizeLevel> rewardConditionList;

    @Override
    public List<PrizeLevel> awardCondition() {
        return rewardConditionList;
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

    /**
     * 计算每个奖级的中奖注数
     * @param myRedBallNum 选购的红球个数
     * @param myBlueBallNum 选购的蓝球个数
     * @param matchRedBallNum 中奖的红球个数
     * @param matchBlueBallNum 中奖的蓝球个数
     * @return 每个奖级的中奖注数
     */
    public List<AwardAnalysis> calculateAwardLevelNumList(long myRedBallNum, long myBlueBallNum, long matchRedBallNum, long matchBlueBallNum) {
        return calculateAwardLevelNumList(myRedBallNum, myBlueBallNum, matchRedBallNum, matchBlueBallNum, 0);
    }

    /**
     * 计算每个奖级的中奖注数
     * @param myRedBallNum 选购的红球个数
     * @param myBlueBallNum 选购的蓝球个数
     * @param matchRedBallNum 中奖的红球个数
     * @param matchBlueBallNum 中奖的蓝球个数
     * @param multiple 倍数
     * @return 每个奖级的中奖注数
     */
    public List<AwardAnalysis> calculateAwardLevelNumList(long myRedBallNum, long myBlueBallNum, long matchRedBallNum, long matchBlueBallNum, long multiple) {

        if (!verifyPurchaseBallNum(myRedBallNum, myBlueBallNum) || !verifyMatchBallNum(matchRedBallNum, matchBlueBallNum)) {
            throw new IllegalArgumentException("计算奖金等级失败，请检查输入参数");
        }

        List<AwardAnalysis> result = new ArrayList<>();

        // 计算转换成单式的总注数
        long totalCombination = combinatorial(myRedBallNum, awardRedBallNum()) * combinatorial(myBlueBallNum, awardBlueBallNum());

        rewardConditionList.forEach(prizeLevel -> {
            // 奖级
            Long level = prizeLevel.getLevel();
            // 单倍奖金
            long singleAward = prizeLevel.getAward();

            long awardNum = 0;
            for (RedBlueBall redBlueBall : prizeLevel.getAwardConditionList()) {
                Long redCondition = redBlueBall.getRedBallNum();
                Long blueCondition = redBlueBall.getBlueBallNum();

                // 计算满足条件的组合数
                // 红球组合 = 组合(选购中奖红球个数, 本奖级红球条件) * 组合(选购未中奖红球个数, 本奖级红球条件未中奖个数)
                // 蓝球组合 = 组合(选购中奖蓝球个数, 本奖级蓝球条件) * 组合(选购未中奖蓝球个数, 本奖级蓝球条件未中奖个数)
                if (matchRedBallNum >= redCondition && matchBlueBallNum >= blueCondition
                        && myRedBallNum >= matchRedBallNum + awardRedBallNum() - redCondition
                        && myBlueBallNum >= matchBlueBallNum + awardBlueBallNum() - blueCondition) {
                    awardNum += combinatorial(matchRedBallNum, redCondition)
                            * combinatorial(myRedBallNum - matchRedBallNum, awardRedBallNum() - redCondition)
                            * combinatorial(matchBlueBallNum, blueCondition)
                            * combinatorial(myBlueBallNum - matchBlueBallNum, awardBlueBallNum() - blueCondition);
                }
            }

            AwardAnalysis analysis = new AwardAnalysis();
            analysis.setLevel(level);
            if (level <= 2 && awardNum > 0) {
                // 追加奖金为单注固定奖金的比例，例如：大乐透80%，双色球暂时不支持追加
                analysis.setAward((long) (singleAward * (awardNum + multiple * appendAwardRatio)));
            } else {
                analysis.setAward(singleAward * awardNum);
            }
            analysis.setAwardNum(awardNum);
            analysis.setPrice(totalCombination * (singlePrice + multiple));
            analysis.setMultiple(multiple);
            result.add(analysis);
        });
        return result;
    }

    /**
     * 计算每个奖级的中奖概率
     * @param myRedBallNum 选购的红球个数
     * @param myBlueBallNum 选购的蓝球个数
     * @return 每个奖级的中奖注数
     */
    public List<AwardAnalysis> calculateAwardLevelProbabilityList(long myRedBallNum, long myBlueBallNum) {
        return calculateAwardLevelProbabilityList(myRedBallNum, myBlueBallNum, 0);
    }

    /**
     * 计算每个奖级的中奖概率
     * @param myRedBallNum 选购的红球个数
     * @param myBlueBallNum 选购的蓝球个数
     * @param multiple 倍数
     * @return 每个奖级的中奖注数
     */
    public List<AwardAnalysis> calculateAwardLevelProbabilityList(long myRedBallNum, long myBlueBallNum, long multiple) {

        if (!verifyPurchaseBallNum(myRedBallNum, myBlueBallNum)) {
            throw new IllegalArgumentException("计算奖金概率失败，请检查输入参数");
        }

        List<AwardAnalysis> result = new ArrayList<>();

        // 所有组合数
        long totalProbability = combinatorial(totalRedBallNum(), myRedBallNum) * combinatorial(totalBlueBallNum(), myBlueBallNum);

        rewardConditionList.forEach(prizeLevel -> {

            Long level = prizeLevel.getLevel();
            AwardAnalysis analysis = new AwardAnalysis();
            analysis.setLevel(level);
            analysis.setMultiple(multiple);
            analysis.setPrice(combinatorial(myRedBallNum, awardRedBallNum()) * combinatorial(myBlueBallNum, awardBlueBallNum()) * (singlePrice + multiple));
            
            long awardNum = 0;
            for (RedBlueBall redBlueBall : prizeLevel.getAwardConditionList()) {
                Long redCondition = redBlueBall.getRedBallNum();
                Long blueCondition = redBlueBall.getBlueBallNum();

                // 计算满足条件的组合数
                // 红球组合 = 组合(开奖红球个数, 本奖级红球条件) * 组合(所有未中奖红球个数, 选购未中奖红球个数)
                // 蓝球组合 = 组合(开奖蓝球个数, 本奖级蓝球条件) * 组合(所有未中奖蓝球个数, 选购未中奖蓝球个数)
                long elementAwardNum = combinatorial(awardRedBallNum(), redCondition)
                        * combinatorial(totalRedBallNum() - awardRedBallNum(), myRedBallNum - redCondition)
                        * combinatorial(awardBlueBallNum(), blueCondition)
                        * combinatorial(totalBlueBallNum() - awardBlueBallNum(), myBlueBallNum - blueCondition);
                awardNum += elementAwardNum;
                // 根据这种情况，计算出每个奖级的中奖注数
                List<AwardAnalysis> awardLevelNumList = calculateAwardLevelNumList(myRedBallNum, myBlueBallNum, redCondition, blueCondition, multiple);
                redBlueBall.setTotalAward(awardLevelNumList.stream().mapToLong(AwardAnalysis::getAward).sum());
                redBlueBall.setLevel(level);
                redBlueBall.setProbability(BigDecimal.valueOf(elementAwardNum).divide(BigDecimal.valueOf(totalProbability), 10, RoundingMode.HALF_UP));
                analysis.getChildren().put(redBlueBall, awardLevelNumList);
            }

            analysis.setProbability(BigDecimal.valueOf(awardNum).divide(BigDecimal.valueOf(totalProbability), 10, RoundingMode.HALF_UP));
            result.add(analysis);
        });
        return result;
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
