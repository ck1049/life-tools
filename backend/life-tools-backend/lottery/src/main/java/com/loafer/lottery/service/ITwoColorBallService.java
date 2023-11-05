package com.loafer.lottery.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.loafer.lottery.model.TwoColorBall;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;

/**
 * <p>
 * 双色球开奖信息 服务类
 * </p>
 *
 * @author loafer
 * @since 2023-11-05
 */
public interface ITwoColorBallService extends IService<TwoColorBall> {

    Boolean saveFromOfficialWebsite(Integer pageNo, Integer pageSize) throws IOException;
}
