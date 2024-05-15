package com.loafer.lottery.util;

import lombok.Getter;

/**
 * @author loafer
 * @since 2024-05-16 02:05:34
 **/
@Getter
public class RedBlueBall {

    private Long redBallNum;
    private Long blueBallNum;

    public RedBlueBall(long redBallNum, long blueBallNum) {
        this.redBallNum = redBallNum;
        this.blueBallNum = blueBallNum;
    }

}