package com.loafer.lottery.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 奖项信息
 * </p>
 *
 * @author loafer
 * @since 2023-11-05
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("two_color_ball_prize_grade")
@ApiModel(value = "TwoColorBallPrizeGrade对象", description = "奖项信息")
public class TwoColorBallPrizeGrade implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("双色球开奖信息主键")
    private Long twoColorBallId;

    @ApiModelProperty("期号")
    private String code;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("类型数量")
    private Integer typeNum;

    @ApiModelProperty("类型金额")
    private Integer typeMoney;
}
