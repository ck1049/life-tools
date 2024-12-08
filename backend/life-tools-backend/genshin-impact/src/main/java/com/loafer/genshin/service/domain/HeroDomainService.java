package com.loafer.genshin.service.domain;

import com.loafer.genshin.api.request.HeroSaveRequest;
import com.loafer.genshin.api.response.CommonResponse;

/**
 * 英雄业务服务接口
 * @author loafer
 * @since 2024-12-08 18:17:10
 **/
public interface HeroDomainService {

    /**
     * 保存英雄信息
     * @param heroSaveRequest 请求对象
     * @return 保存结果
     */
    CommonResponse<String> save(HeroSaveRequest heroSaveRequest);
}
