package com.loafer.lottery.service;

import com.loafer.lottery.vo.OfficialLottoInfoVO;

import java.util.List;

/**
 * 大乐透开奖信息查询
 * @author loafer
 * @since 2023-11-04 18:38:41
 **/
public interface IExtSysLottoService {

    /**
     * 大乐透开奖信息查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<OfficialLottoInfoVO> getLottoInfo(Integer pageNo, Integer pageSize) throws InterruptedException;
}
