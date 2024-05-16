package com.loafer.lottery.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;

/**
 * @author loafer
 * @since 2024-05-16 02:05:34
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class RedBlueBall {

    /**
     * 红球命中数量
     */
    private Long redBallNum;
    /**
     * 蓝球命中数量
     */
    private Long blueBallNum;
    /**
     * 总奖金
     */
    private Long totalAward;
    /**
     * 奖级
     */
    private Long level;

    /**
     * 概率
     */
    private BigDecimal probability;

    /**
     * 概率的非科学计数法字符串值
     */
    private String probabilityStr;

    public RedBlueBall(long redBallNum, long blueBallNum) {
        this.redBallNum = redBallNum;
        this.blueBallNum = blueBallNum;
    }

    /**
     * 避免概率科学计数法无法显示的问题
     * @return 概率的非科学计数法字符串值
     */
    public String getProbabilityStr() {
        return ObjectUtils.isEmpty(probability) ? "" : this.probability.multiply(BigDecimal.valueOf(100)).toPlainString() + "%";
    }
}