package com.loafer.lottery.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 双色球开奖信息
 * </p>
 *
 * @author loafer
 * @since 2023-11-05
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("two_color_ball")
@Schema(description = "双色球开奖信息")
public class TwoColorBall implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "期号")
    private String code;

    @Schema(description = "详情链接")
    private String detailsLink;

    @Schema(description = "视频链接")
    private String videoLink;

    @Schema(description = "日期")
    private String date;

    @Schema(description = "星期")
    private String week;

    @Schema(description = "红球")
    private String red;

    @Schema(description = "蓝球")
    private String blue;

    @Schema(description = "第二个蓝球")
    private String blue2;

    @Schema(description = "销售额")
    private Long sales;

    @Schema(description = "奖池金额")
    private Long poolMoney;

    @Schema(description = "内容")
    private String content;
}
