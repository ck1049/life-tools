package com.loafer.lottery.service;

import com.loafer.lottery.vo.OfficialLottoInfoVO;

/**
 * 大乐透开奖信息查询
 * @author loafer
 * @since 2023-11-04 18:38:41
 **/
public interface IExtSysTwoColorBallService {

    /**
     * 大乐透开奖信息查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    OfficialLottoInfoVO getLottoInfo(Integer pageNo, Integer pageSize);
}
