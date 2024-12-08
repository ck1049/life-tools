package com.loafer.genshin.service.domain;

import com.loafer.genshin.api.request.HeroSaveRequest;
import com.loafer.genshin.api.response.CommonResponse;
import org.springframework.data.elasticsearch.core.SearchHit;
import java.util.HashMap;
import java.util.List;

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

    /**
     * 根据姓名查询分页信息
     * @param name 姓名
     * @param lastId 上次查询最后一条数据的id
     * @return 查询结果
     */
    CommonResponse<List<SearchHit<HashMap>>> searchAfterByName(String name, Long lastId);

    /**
     * 根据姓名查询分页信息(使用script)
     * @param name 姓名
     * @param lastId 上次查询最后一条数据的id
     * @return 查询结果
     */
    CommonResponse<List<SearchHit<HashMap>>> searchAfterByNameUseScript(String name, Long lastId);
}
