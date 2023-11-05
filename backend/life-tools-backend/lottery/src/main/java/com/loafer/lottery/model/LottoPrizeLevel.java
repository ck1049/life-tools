package com.loafer.lottery.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 奖金等级信息表
 * </p>
 *
 * @author loafer
 * @since 2023-11-04
 */
@Getter
@Setter
@TableName("lotto_prize_level")
@ApiModel(value = "LottoPrizeLevel对象", description = "奖金等级信息表")
public class LottoPrizeLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("开奖信息主键")
    private Long lottoId;

    @ApiModelProperty("期号")
    private String lotteryDrawNum;

    @ApiModelProperty("奖励类型")
    private Integer awardType;

    @ApiModelProperty("奖金组")
    private String groupName;

    @ApiModelProperty("奖金等级")
    private String prizeLevel;

    @ApiModelProperty("排序顺序")
    private Integer sort;

    @ApiModelProperty("中奖金额")
    private BigDecimal stakeAmount;

    @ApiModelProperty("格式化的中奖金额")
    private String stakeAmountFormat;

    @ApiModelProperty("中奖数量")
    private Integer stakeCount;

    @ApiModelProperty("总奖金金额")
    private BigDecimal totalPrizeAmount;
}
