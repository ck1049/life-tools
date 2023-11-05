package com.loafer.lottery.config;

import com.loafer.lottery.convert.StringBigDecimalConvert;
import com.loafer.lottery.convert.StringIntegerConvert;
import com.loafer.lottery.model.Lotto;
import com.loafer.lottery.model.LottoPrizeLevel;
import com.loafer.lottery.vo.OfficialLottoInfoVO;
import org.modelmapper.ModelMapper;
import org.modelmapper.builder.ReferenceMapExpression;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

/**
 * modelMapper配置类
 */
@Configuration
public class MapperConfig {

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
                .addMappings(config -> config.using(new StringBigDecimalConvert())
                        .map(OfficialLottoInfoVO.LastPoolDraw::getPoolBalanceAfterdraw, Lotto::setPoolBalanceAfterdraw));
        modelMapper.createTypeMap(OfficialLottoInfoVO.PrizeLevel.class, LottoPrizeLevel.class)
                .addMappings(config -> config.using(new StringBigDecimalConvert())
                        .map(OfficialLottoInfoVO.PrizeLevel::getStakeAmount, LottoPrizeLevel::setStakeAmount))
                .addMappings(config -> config.using(new StringIntegerConvert())
                        .map(OfficialLottoInfoVO.PrizeLevel::getStakeCount, LottoPrizeLevel::setStakeCount))
                .addMappings(config -> config.using(new StringBigDecimalConvert())
                        .map(OfficialLottoInfoVO.PrizeLevel::getTotalPrizeamount, LottoPrizeLevel::setTotalPrizeAmount))
                .addMapping(OfficialLottoInfoVO.PrizeLevel::getGroup, LottoPrizeLevel::setGroupName);

    }
}
