package com.loafer.genshin.config;

import com.loafer.common.convert.StringIntegerConvert;
import com.loafer.common.convert.StringLocalDateConvert;
import com.loafer.common.convert.StringLongConvert;
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

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addConverter(StringLocalDateConvert.getInstance());
        modelMapper.addConverter(StringLongConvert.getInstance());
        modelMapper.addConverter(StringIntegerConvert.getInstance());
        modelMapper.addConverter(StringLocalDateConvert.getInstance());
        createTypeMap(modelMapper);
        return modelMapper;
    }

    private void createTypeMap(ModelMapper modelMapper) {
        return;
    }
}
