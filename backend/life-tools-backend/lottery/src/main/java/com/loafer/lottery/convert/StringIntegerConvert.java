package com.loafer.lottery.convert;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.math.BigDecimal;

/**
 * @author loafer
 * @since 2023-11-04 23:53:15
 **/
public class StringIntegerConvert implements Converter<String, Integer> {

    @Override
    public Integer convert(MappingContext<String, Integer> context) {
        if (context.getSource()!= null && !"".equals(context.getSource().replaceAll("[,-]", ""))) {
            return Integer.valueOf(context.getSource().replaceAll("[,-]", ""));
        }
        return null;
    }
}
