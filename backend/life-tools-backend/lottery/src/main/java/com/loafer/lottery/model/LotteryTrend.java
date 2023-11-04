package com.loafer.lottery.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "LotteryTrend对象", description = "彩票走势表")
public class LotteryTrend implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("彩票类型")
    private String lotteryType;

    @ApiModelProperty("期次")
    private Integer issue;

    @ApiModelProperty("颜色")
    private String color;

    private String number;

    @ApiModelProperty("间隔数")
    private Integer intervalCount;

    @ApiModelProperty("命中")
    private Boolean hit;
}
