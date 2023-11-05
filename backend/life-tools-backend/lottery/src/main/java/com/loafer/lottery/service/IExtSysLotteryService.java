package com.loafer.lottery.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.loafer.lottery.vo.OfficialLottoInfoVO;
import com.loafer.lottery.vo.OfficialTwoColorBallInfoVO;

import java.io.IOException;
import java.util.List;

/**
 * 大乐透开奖信息查询
 * @author loafer
 * @since 2023-11-04 18:38:41
 **/
public interface IExtSysLotteryService {

    /**
     * 大乐透开奖信息查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<OfficialLottoInfoVO> getLottoInfo(Integer pageNo, Integer pageSize) throws InterruptedException;

    /**
     * 双色球开奖信息查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    OfficialTwoColorBallInfoVO getTwoColorBallInfo(Integer pageNo, Integer pageSize) throws IOException;
}
