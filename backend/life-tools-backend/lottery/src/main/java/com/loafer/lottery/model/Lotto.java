package com.loafer.lottery.model;

import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "Lotto对象", description = "彩票开奖信息")
public class Lotto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("期号")
    private String lotteryDrawNum;

    @ApiModelProperty("中奖号码")
    private String lotteryDrawResult;

    @ApiModelProperty("开奖日期")
    private String lotteryDrawTime;

    @ApiModelProperty("彩票游戏名称")
    private String lotteryGameName;

    @ApiModelProperty("彩票游戏编号")
    private String lotteryGameNum;

    @ApiModelProperty("抽奖后奖池余额")
    private BigDecimal poolBalanceAfterdraw;
}
