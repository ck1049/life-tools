package com.loafer.common.convert;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author loafer
 * @since 2023-11-04 23:53:15
 **/
@Component
public class StringBigDecimalConvert implements Converter<String, BigDecimal> {

    private static final StringBigDecimalConvert INSTANCE = new StringBigDecimalConvert();

    @Override
    public BigDecimal convert(MappingContext<String, BigDecimal> context) {
        if (context.getSource()!= null && !context.getSource().replaceAll("[,-]", "").isEmpty()) {
            return new BigDecimal(context.getSource().replaceAll("[,-]", ""));
        }
        return null;
    }

    public static StringBigDecimalConvert getInstance() {
        return INSTANCE;
    }
}
