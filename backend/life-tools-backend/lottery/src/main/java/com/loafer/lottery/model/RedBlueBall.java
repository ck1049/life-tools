package com.loafer.lottery.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author loafer
 * @since 2024-05-16 02:05:34
 **/
@Getter
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
    @Setter
    private Long totalAward;

    public RedBlueBall(long redBallNum, long blueBallNum) {
        this.redBallNum = redBallNum;
        this.blueBallNum = blueBallNum;
    }

}