package com.loafer.lottery.model;

import lombok.*;

/**
 * @author loafer
 * @since 2024-05-16 02:05:34
 **/
@Data
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

    public RedBlueBall(long redBallNum, long blueBallNum) {
        this.redBallNum = redBallNum;
        this.blueBallNum = blueBallNum;
    }

}