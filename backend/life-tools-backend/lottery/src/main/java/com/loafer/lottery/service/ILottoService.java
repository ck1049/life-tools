package com.loafer.lottery.service;

import com.loafer.lottery.model.Lotto;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 彩票开奖信息 服务类
 * </p>
 *
 * @author loafer
 * @since 2023-11-04
 */
public interface ILottoService extends IService<Lotto> {
    /**
     * 从官网查询开奖信息并保存
     * @param startIndex 起始位置，不是页码
     * @param pageSize
     * @return
     */
    Boolean saveFromOfficialWebsite(Integer startIndex, Integer pageSize) throws InterruptedException;
}
