package com.loafer.lottery.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 奖级
 * @author loafer
 * @since 2024-05-16 19:04:55
 **/
@Data
@AllArgsConstructor
public class PrizeLevel {

    /**
     * 奖级
     */
    private Long level;
    /**
     * 奖金
     */
    private Long award;
    /**
     * 奖级对应的红蓝球条件
     */
    private List<RedBlueBall> awardConditionList;
}
