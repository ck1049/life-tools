package com.loafer.lottery.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 双色球官方接口返回信息
 * @author loafer
 * @since 2023-11-05 14:57:16
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfficialTwoColorBallInfoVO {
    private Integer state;               // 状态
    private String message;          // 消息
    private Integer total;               // 总数
    private Integer pageNum;             // 页数
    private Integer pageNo;              // 页号
    private Integer pageSize;           // 页大小
    private Integer Tflag;              // T标志
    private List<ResultEntry> result; // 结果列表


    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResultEntry {
        private String name;          // 名称
        private String code;          // 代码
        private String detailsLink;   // 详情链接
        private String videoLink;     // 视频链接
        private String date;          // 日期
        private String week;          // 星期
        private String red;           // 红球
        private String blue;          // 蓝球
        private String blue2;         // 第二个蓝球
        private String sales;         // 销售额
        private String poolmoney;     // 奖池金额
        private String content;       // 内容
        private String addmoney;      // 追加金额
        private String addmoney2;     // 第二个追加金额
        private String msg;           // 消息
        private String z2add;         // Z2追加
        private String m2add;         // M2追加
        private List<PrizeGrade> prizegrades; // 奖项列表
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PrizeGrade {
        private Integer type;           // 类型
        private String typenum;     // 类型数量
        private String typemoney;   // 类型金额
    }

}
