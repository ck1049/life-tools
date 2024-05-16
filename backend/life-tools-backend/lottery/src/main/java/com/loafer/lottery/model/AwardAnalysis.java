package com.loafer.lottery.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 奖金分析
 * @author loafer
 * @since 2024-05-16 19:23:46
 **/
@Data
@Accessors(chain = true)
public class AwardAnalysis {

    /**
     * 奖金等级
     */
    private Long level;
    /**
     * 奖金
     */
    private Long award;
    /**
     * 该奖级命中数
     */
    private Long awardNum;
    /**
     * 成本
     */
    private Long price;
    /**
     * 追加倍数
     */
    private Long multiple;
    /**
     * 概率
     */
    private BigDecimal probability;
    /**
     * 一个奖级可能有多种匹配情况，每种匹配情况下可能会有多个奖级（复试的时候）
     */
    private Map<RedBlueBall, List<AwardAnalysis>> children = new HashMap<>();

    /**
     * 避免概率科学计数法无法显示的问题
     * @return 概率的非科学计数法字符串值
     */
    public String getProbabilityStr() {
        return ObjectUtils.isEmpty(probability) ? "" : this.probability.multiply(BigDecimal.valueOf(100)).toPlainString() + "%";
    }

}
