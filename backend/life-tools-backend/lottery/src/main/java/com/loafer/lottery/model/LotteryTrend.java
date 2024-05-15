package com.loafer.lottery.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 彩票走势表
 * </p>
 *
 * @author loafer
 * @since 2023-11-04
 */
@Getter
@Setter
@TableName("lottery_trend")
@Schema(description = "彩票走势表")
public class LotteryTrend implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "彩票类型")
    private String lotteryType;

    @Schema(description = "期次")
    private Integer issue;

    @Schema(description = "颜色")
    private String color;

    private String number;

    @Schema(description = "间隔数")
    private Integer intervalCount;

    @Schema(description = "命中")
    private Boolean hit;
}
