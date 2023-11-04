package com.loafer.lottery.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.loafer.common.utils.SnowflakedUtils;
import com.loafer.lottery.service.IExtSysLottoService;
import com.loafer.lottery.model.Lotto;
import com.loafer.lottery.mapper.LottoMapper;
import com.loafer.lottery.model.LottoPrizeLevel;
import com.loafer.lottery.service.ILottoPrizeLevelService;
import com.loafer.lottery.service.ILottoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loafer.lottery.vo.OfficialLottoInfoVO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 彩票开奖信息 服务实现类
 * </p>
 *
 * @author loafer
 * @since 2023-11-04
 */
@Service
@AllArgsConstructor
public class LottoServiceImpl extends ServiceImpl<LottoMapper, Lotto> implements ILottoService {

    private IExtSysLottoService IExtSysLottoService;
    private ModelMapper modelMapper;
    private ILottoPrizeLevelService lottoPrizeLevelService;

    @Override
    @Transactional
    public Boolean saveFromOfficialWebsite(Integer pageNo, Integer pageSize) throws InterruptedException {
        List<OfficialLottoInfoVO> lottoInfoList = IExtSysLottoService.getLottoInfo(pageNo, pageSize);
        lottoInfoList.sort(Comparator.comparing(item -> item.getValue().getLastPoolDraw().getLotteryDrawNum()));

        List<Lotto> lottoList = new ArrayList<>();
        List<LottoPrizeLevel> lottoPrizeLevelList = new ArrayList<>();

        for (OfficialLottoInfoVO officialLottoInfoVO : lottoInfoList) {
            if (officialLottoInfoVO != null && officialLottoInfoVO.getValue() != null && officialLottoInfoVO.getValue().getLastPoolDraw() != null) {
                OfficialLottoInfoVO.LastPoolDraw lastPoolDraw = officialLottoInfoVO.getValue().getLastPoolDraw();
                Lotto lotto = modelMapper.map(lastPoolDraw, Lotto.class);
                long lottoId = SnowflakedUtils.nextId();
                lotto.setId(lottoId);
                lottoList.add(lotto);

                List<OfficialLottoInfoVO.PrizeLevel> prizeLevelList = lastPoolDraw.getPrizeLevelList();
                if (!CollectionUtils.isEmpty(prizeLevelList)) {
                    lottoPrizeLevelList.addAll(prizeLevelList.stream()
                            .sorted(Comparator.comparing(OfficialLottoInfoVO.PrizeLevel::getSort))
                            .map(item -> {
                                LottoPrizeLevel level = modelMapper.map(item, LottoPrizeLevel.class);
                                level.setId(SnowflakedUtils.nextId());
                                level.setLottoId(lottoId);
                                level.setLotteryDrawNum(lotto.getLotteryDrawNum());
                                return level;
                            })
                            .toList());
                }
            }
        }

        // 根据期号删除lotto、lotto_prize_level表数据
        List<String> lotteryDrawNumList = lottoList.stream().map(Lotto::getLotteryDrawNum).toList();
        remove(Wrappers.lambdaQuery(Lotto.class).in(Lotto::getLotteryDrawNum, lotteryDrawNumList));
        lottoPrizeLevelService.remove(Wrappers.lambdaQuery(LottoPrizeLevel.class).in(LottoPrizeLevel::getLotteryDrawNum, lotteryDrawNumList));

        return saveBatch(lottoList, 50) && lottoPrizeLevelService.saveBatch(lottoPrizeLevelList, 50);
    }

}
