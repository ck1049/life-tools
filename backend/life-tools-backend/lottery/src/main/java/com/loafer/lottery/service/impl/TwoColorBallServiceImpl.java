package com.loafer.lottery.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.loafer.common.utils.SnowflakedUtils;
import com.loafer.lottery.model.TwoColorBall;
import com.loafer.lottery.mapper.TwoColorBallMapper;
import com.loafer.lottery.model.TwoColorBallPrizeGrade;
import com.loafer.lottery.service.IExtSysLotteryService;
import com.loafer.lottery.service.ITwoColorBallPrizeGradeService;
import com.loafer.lottery.service.ITwoColorBallService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loafer.lottery.vo.OfficialTwoColorBallInfoVO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * 双色球开奖信息 服务实现类
 * </p>
 *
 * @author loafer
 * @since 2023-11-05
 */
@Service
@AllArgsConstructor
public class TwoColorBallServiceImpl extends ServiceImpl<TwoColorBallMapper, TwoColorBall> implements ITwoColorBallService {

    private IExtSysLotteryService extSysLotteryService;
    private ModelMapper modelMapper;
    private ITwoColorBallPrizeGradeService twoColorBallPrizeGradeService;

    @Override
    @Transactional
    public Boolean saveFromOfficialWebsite(Integer pageNo, Integer pageSize) throws IOException {

        OfficialTwoColorBallInfoVO officialTwoColorBallInfoVO = extSysLotteryService.getTwoColorBallInfo(pageNo, pageSize);
        if (officialTwoColorBallInfoVO == null || officialTwoColorBallInfoVO.getResult() == null) {
            return false;
        }

        List<TwoColorBall> twoColorBallList = new ArrayList<>();
        List<TwoColorBallPrizeGrade> twoColorBallPrizeGradeList = new ArrayList<>();

        List<OfficialTwoColorBallInfoVO.ResultEntry> resultEntryList = officialTwoColorBallInfoVO.getResult();
        resultEntryList.sort(Comparator.comparing(OfficialTwoColorBallInfoVO.ResultEntry::getCode));

        for (OfficialTwoColorBallInfoVO.ResultEntry resultEntry : resultEntryList) {
            long twoColorBallId = SnowflakedUtils.nextId();
            TwoColorBall twoColorBall = modelMapper.map(resultEntry, TwoColorBall.class);
            twoColorBall.setId(twoColorBallId);
            // 日期格式转换 YYYY-MM-DD(WEEKDAY) -> YYYY-MM-DD
            twoColorBall.setDate(twoColorBall.getDate().substring(0, 10));
            twoColorBallList.add(twoColorBall);

            // 奖金等级
            List<OfficialTwoColorBallInfoVO.PrizeGrade> prizegradeList = resultEntry.getPrizegrades();
            if (!CollectionUtils.isEmpty(prizegradeList)) {
                twoColorBallPrizeGradeList.addAll(prizegradeList.stream().map(item -> {
                    TwoColorBallPrizeGrade prizeGrade = modelMapper.map(item, TwoColorBallPrizeGrade.class);
                    prizeGrade.setId(SnowflakedUtils.nextId());
                    prizeGrade.setTwoColorBallId(twoColorBallId);
                    prizeGrade.setCode(twoColorBall.getCode());
                    return prizeGrade;
                }).toList());
            }
        }

        List<String> codeList = twoColorBallList.stream().map(TwoColorBall::getCode).toList();
        remove(Wrappers.<TwoColorBall>lambdaQuery().in(TwoColorBall::getCode, codeList));
        twoColorBallPrizeGradeService.remove(Wrappers.<TwoColorBallPrizeGrade>lambdaQuery().in(TwoColorBallPrizeGrade::getCode, codeList));

        boolean result = saveBatch(twoColorBallList, 50);

        return CollectionUtils.isEmpty(twoColorBallPrizeGradeList) ? result
                : result && twoColorBallPrizeGradeService.saveBatch(twoColorBallPrizeGradeList, 50);
    }
}
