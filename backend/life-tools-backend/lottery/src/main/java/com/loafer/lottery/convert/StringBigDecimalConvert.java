package com.loafer.lottery.convert;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.math.BigDecimal;

/**
 * @author loafer
 * @since 2023-11-04 23:53:15
 **/
public class StringBigDecimalConvert implements Converter<String, BigDecimal> {

    @Override
    public BigDecimal convert(MappingContext<String, BigDecimal> context) {
        if (context.getSource()!= null && !"".equals(context.getSource().replaceAll("[,-]", ""))) {
            return new BigDecimal(context.getSource().replaceAll("[,-]", ""));
        }
        return null;
    }
}
