package com.loafer.lottery.convert;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

/**
 * @author loafer
 * @since 2023-11-04 23:53:15
 **/
@Component
public class StringLongConvert implements Converter<String, Long> {

    @Override
    public Long convert(MappingContext<String, Long> context) {
        if (context.getSource()!= null && !"".equals(context.getSource().replaceAll("[,-]", ""))) {
            return Long.valueOf(context.getSource().replaceAll("[,-]", ""));
        }
        return null;
    }
}
