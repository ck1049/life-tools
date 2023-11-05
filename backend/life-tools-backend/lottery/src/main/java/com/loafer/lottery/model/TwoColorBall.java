package com.loafer.lottery.model;

import cn.hutool.cache.Cache;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "TwoColorBall对象", description = "双色球开奖信息")
public class TwoColorBall implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("期号")
    private String code;

    @ApiModelProperty("详情链接")
    private String detailsLink;

    @ApiModelProperty("视频链接")
    private String videoLink;

    @ApiModelProperty("日期")
    private String date;

    @ApiModelProperty("星期")
    private String week;

    @ApiModelProperty("红球")
    private String red;

    @ApiModelProperty("蓝球")
    private String blue;

    @ApiModelProperty("第二个蓝球")
    private String blue2;

    @ApiModelProperty("销售额")
    private Long sales;

    @ApiModelProperty("奖池金额")
    private Long poolMoney;

    @ApiModelProperty("内容")
    private String content;
}
