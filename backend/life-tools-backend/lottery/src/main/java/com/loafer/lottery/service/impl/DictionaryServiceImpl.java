package com.loafer.lottery.service.impl;

import com.loafer.lottery.model.Dictionary;
import com.loafer.lottery.mapper.DictionaryMapper;
import com.loafer.lottery.service.IDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典表，用于存储常见配置选项，包括模块信息 服务实现类
 * </p>
 *
 * @author loafer
 * @since 2023-11-04
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {

}
