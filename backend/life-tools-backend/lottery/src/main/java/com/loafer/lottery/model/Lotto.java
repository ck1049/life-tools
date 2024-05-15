package com.loafer.lottery.model;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 彩票开奖信息
 * </p>
 *
 * @author loafer
 * @since 2023-11-04
 */
@Getter
@Setter
@Accessors(chain = true)
@Schema(description = "彩票开奖信息")
public class Lotto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "期号")
    private String lotteryDrawNum;

    @Schema(description = "中奖号码")
    private String lotteryDrawResult;

    @Schema(description = "开奖日期")
    private String lotteryDrawTime;

    @Schema(description = "彩票游戏名称")
    private String lotteryGameName;

    @Schema(description = "彩票游戏编号")
    private String lotteryGameNum;

    @Schema(description = "抽奖后奖池余额")
    private BigDecimal poolBalanceAfterdraw;
}
