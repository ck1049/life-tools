package com.loafer.common.convert;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

/**
 * @author loafer
 * @since 2023-11-04 23:53:15
 **/
public class StringIntegerConvert implements Converter<String, Integer> {

    private static final StringIntegerConvert INSTANCE = new StringIntegerConvert();

    @Override
    public Integer convert(MappingContext<String, Integer> context) {
        if (context.getSource()!= null && !context.getSource().replaceAll("[,-]", "").isEmpty()) {
            return Integer.valueOf(context.getSource().replaceAll("[,-]", ""));
        }
        return null;
    }

    public static StringIntegerConvert getInstance() {
        return INSTANCE;
    }
}
