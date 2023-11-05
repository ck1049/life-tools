package com.loafer.lottery.config;

import com.loafer.lottery.convert.StringBigDecimalConvert;
import com.loafer.lottery.convert.StringIntegerConvert;
import com.loafer.lottery.convert.StringLongConvert;
import com.loafer.lottery.model.Lotto;
import com.loafer.lottery.model.LottoPrizeLevel;
import com.loafer.lottery.model.TwoColorBall;
import com.loafer.lottery.model.TwoColorBallPrizeGrade;
import com.loafer.lottery.vo.OfficialLottoInfoVO;
import com.loafer.lottery.vo.OfficialTwoColorBallInfoVO;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * modelMapper配置类
 */
@Configuration
public class MapperConfig {

    private static StringBigDecimalConvert stringBigDecimalConvert;
    private static StringLongConvert stringLongConvert;
    private static StringIntegerConvert stringIntegerConvert;

    @Resource
    public void setStringBigDecimalConvert(StringBigDecimalConvert stringBigDecimalConvert) {
        MapperConfig.stringBigDecimalConvert = stringBigDecimalConvert;
    }

    @Resource
    public void setStringLongConvert(StringLongConvert stringLongConvert) {
        MapperConfig.stringLongConvert = stringLongConvert;
    }

    @Resource
    public void setStringIntegerConvert(StringIntegerConvert stringIntegerConvert) {
        MapperConfig.stringIntegerConvert = stringIntegerConvert;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        createTypeMap(modelMapper);
        return modelMapper;
    }

    private void createTypeMap(ModelMapper modelMapper) {
        modelMapper.createTypeMap(OfficialLottoInfoVO.LastPoolDraw.class, Lotto.class)
                .addMappings(config -> config.using(stringBigDecimalConvert)
                        .map(OfficialLottoInfoVO.LastPoolDraw::getPoolBalanceAfterdraw, Lotto::setPoolBalanceAfterdraw));
        modelMapper.createTypeMap(OfficialLottoInfoVO.PrizeLevel.class, LottoPrizeLevel.class)
                .addMappings(config -> config.using(stringBigDecimalConvert)
                        .map(OfficialLottoInfoVO.PrizeLevel::getStakeAmount, LottoPrizeLevel::setStakeAmount))
                .addMappings(config -> config.using(stringIntegerConvert)
                        .map(OfficialLottoInfoVO.PrizeLevel::getStakeCount, LottoPrizeLevel::setStakeCount))
                .addMappings(config -> config.using(stringBigDecimalConvert)
                        .map(OfficialLottoInfoVO.PrizeLevel::getTotalPrizeamount, LottoPrizeLevel::setTotalPrizeAmount))
                .addMapping(OfficialLottoInfoVO.PrizeLevel::getGroup, LottoPrizeLevel::setGroupName);
        modelMapper.createTypeMap(OfficialTwoColorBallInfoVO.ResultEntry.class, TwoColorBall.class)
               .addMappings(config -> config.using(stringLongConvert)
                       .map(OfficialTwoColorBallInfoVO.ResultEntry::getSales, TwoColorBall::setSales))
                .addMappings(config -> config.using(stringLongConvert)
                        .map(OfficialTwoColorBallInfoVO.ResultEntry::getPoolmoney, TwoColorBall::setPoolMoney));
        modelMapper.createTypeMap(OfficialTwoColorBallInfoVO.PrizeGrade.class, TwoColorBallPrizeGrade.class)
                .addMappings(config -> config.using(stringIntegerConvert)
                        .map(OfficialTwoColorBallInfoVO.PrizeGrade::getTypenum, TwoColorBallPrizeGrade::setTypeNum))
                .addMappings(config -> config.using(stringIntegerConvert)
                        .map(OfficialTwoColorBallInfoVO.PrizeGrade::getTypemoney, TwoColorBallPrizeGrade::setTypeMoney));
    }
}
