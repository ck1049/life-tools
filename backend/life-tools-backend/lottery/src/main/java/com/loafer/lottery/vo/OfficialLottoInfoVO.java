package com.loafer.lottery.vo;

import lombok.Data;

import java.util.List;

/**
 * @author loafer
 * @since 2023-11-04 17:17:56
 **/
@Data
public class OfficialLottoInfoVO {

    private String dataFrom; // 数据来源
    private boolean emptyFlag; // 是否为空标志
    private String errorCode; // 错误代码
    private String errorMessage; // 错误消息
    private boolean success; // 是否成功标志
    private Value value; // 值对象

    @Data
    public static class Value {
        private LastPoolDraw lastPoolDraw; // 最后一次奖池抽奖
        private int pageNo; // 页码
        private int pageSize; // 每页大小
        private int pages; // 总页数
        private int total; // 总记录数
    }

    @Data
    public static class LastPoolDraw {
        private String lotteryDrawNum; // 期号
        private String lotteryDrawResult; // 中奖号码
        private String lotteryDrawTime; // 开奖日期
        private String lotteryGameName; // 彩票游戏名称
        private String lotteryGameNum; // 彩票游戏编号
        private String poolBalanceAfterdraw; // 抽奖后奖池余额
        private List<PrizeLevel> prizeLevelList; // 奖金等级列表
    }

    @Data
    public static class PrizeLevel {
        private Integer awardType; // 奖励类型
        private String group; // 奖金组
        private String lotteryCondition; // 彩票条件
        private String prizeLevel; // 奖金等级
        private Integer sort; // 排序
        private String stakeAmount; // 下注金额
        private String stakeAmountFormat; // 格式化的下注金额
        private String stakeCount; // 下注数量
        private String totalPrizeamount; // 总奖金金额
    }

}
