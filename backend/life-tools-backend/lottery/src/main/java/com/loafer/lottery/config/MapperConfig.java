package com.loafer.lottery.config;

import com.loafer.common.convert.StringBigDecimalConvert;
import com.loafer.common.convert.StringIntegerConvert;
import com.loafer.common.convert.StringLocalDateConvert;
import com.loafer.common.convert.StringLongConvert;
import com.loafer.lottery.model.Lotto;
import com.loafer.lottery.model.LottoPrizeLevel;
import com.loafer.lottery.model.TwoColorBall;
import com.loafer.lottery.model.TwoColorBallPrizeGrade;
import com.loafer.lottery.vo.OfficialLottoInfoVO;
import com.loafer.lottery.vo.OfficialTwoColorBallInfoVO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * modelMapper配置类
 * @author loafer
 */
@Configuration
public class MapperConfig {

    private static final StringBigDecimalConvert STRINGBIGDECIMALCONVERT = StringBigDecimalConvert.getInstance();
    private static final StringLongConvert STRINGLONGCONVERT = StringLongConvert.getInstance();
    private static final StringIntegerConvert STRINGINTEGERCONVERT = StringIntegerConvert.getInstance();

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addConverter(StringBigDecimalConvert.getInstance());
        modelMapper.addConverter(StringLongConvert.getInstance());
        modelMapper.addConverter(StringIntegerConvert.getInstance());
        modelMapper.addConverter(StringLocalDateConvert.getInstance());
        createTypeMap(modelMapper);
        return modelMapper;
    }

    private void createTypeMap(ModelMapper modelMapper) {
        modelMapper.createTypeMap(OfficialLottoInfoVO.LastPoolDraw.class, Lotto.class)
                .addMappings(config -> config.using(STRINGBIGDECIMALCONVERT)
                        .map(OfficialLottoInfoVO.LastPoolDraw::getPoolBalanceAfterdraw, Lotto::setPoolBalanceAfterdraw));
        modelMapper.createTypeMap(OfficialLottoInfoVO.PrizeLevel.class, LottoPrizeLevel.class)
                .addMappings(config -> config.using(STRINGBIGDECIMALCONVERT)
                        .map(OfficialLottoInfoVO.PrizeLevel::getStakeAmount, LottoPrizeLevel::setStakeAmount))
                .addMappings(config -> config.using(STRINGINTEGERCONVERT)
                        .map(OfficialLottoInfoVO.PrizeLevel::getStakeCount, LottoPrizeLevel::setStakeCount))
                .addMappings(config -> config.using(STRINGBIGDECIMALCONVERT)
                        .map(OfficialLottoInfoVO.PrizeLevel::getTotalPrizeamount, LottoPrizeLevel::setTotalPrizeAmount))
                .addMapping(OfficialLottoInfoVO.PrizeLevel::getGroup, LottoPrizeLevel::setGroupName);
        modelMapper.createTypeMap(OfficialTwoColorBallInfoVO.ResultEntry.class, TwoColorBall.class)
               .addMappings(config -> config.using(STRINGLONGCONVERT)
                       .map(OfficialTwoColorBallInfoVO.ResultEntry::getSales, TwoColorBall::setSales))
                .addMappings(config -> config.using(STRINGLONGCONVERT)
                        .map(OfficialTwoColorBallInfoVO.ResultEntry::getPoolmoney, TwoColorBall::setPoolMoney));
        modelMapper.createTypeMap(OfficialTwoColorBallInfoVO.PrizeGrade.class, TwoColorBallPrizeGrade.class)
                .addMappings(config -> config.using(STRINGINTEGERCONVERT)
                        .map(OfficialTwoColorBallInfoVO.PrizeGrade::getTypenum, TwoColorBallPrizeGrade::setTypeNum))
                .addMappings(config -> config.using(STRINGINTEGERCONVERT)
                        .map(OfficialTwoColorBallInfoVO.PrizeGrade::getTypemoney, TwoColorBallPrizeGrade::setTypeMoney));
    }
}
