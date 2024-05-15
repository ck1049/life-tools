package com.loafer.lottery.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "奖金等级信息表")
public class LottoPrizeLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "开奖信息主键")
    private Long lottoId;

    @Schema(description = "期号")
    private String lotteryDrawNum;

    @Schema(description = "奖励类型")
    private Integer awardType;

    @Schema(description = "奖金组")
    private String groupName;

    @Schema(description = "奖金等级")
    private String prizeLevel;

    @Schema(description = "排序顺序")
    private Integer sort;

    @Schema(description = "中奖金额")
    private BigDecimal stakeAmount;

    @Schema(description = "格式化的中奖金额")
    private String stakeAmountFormat;

    @Schema(description = "中奖数量")
    private Integer stakeCount;

    @Schema(description = "总奖金金额")
    private BigDecimal totalPrizeAmount;
}
