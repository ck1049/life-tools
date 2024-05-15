package com.loafer.lottery.enums;

import lombok.Getter;

/**
 * @author 18238
 */

@Getter
public enum LotteryType {

    TWO_COLOR_BALL(1, "TWO_COLOR_BALL", 33L, 16L),
    LOTTO(2, "LOTTO", 35L, 12L);

    private final Integer code;
    private final String type;
    private final Long redBallsNum;
    private final Long blueBallsNum;

    LotteryType(Integer code, String type, Long redBallsNum, Long blueBallsNum) {
        this.code = code;
        this.type = type;
        this.redBallsNum = redBallsNum;
        this.blueBallsNum = blueBallsNum;
    }

}
