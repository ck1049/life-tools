package com.loafer.lottery.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "奖项信息")
public class TwoColorBallPrizeGrade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "双色球开奖信息主键")
    private Long twoColorBallId;

    @Schema(description = "期号")
    private String code;

    @Schema(description = "类型")
    private Integer type;

    @Schema(description = "类型数量")
    private Integer typeNum;

    @Schema(description = "类型金额")
    private Integer typeMoney;
}
