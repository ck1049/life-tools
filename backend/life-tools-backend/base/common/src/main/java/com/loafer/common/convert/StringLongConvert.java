package com.loafer.common.convert;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

/**
 * @author loafer
 * @since 2023-11-04 23:53:15
 **/
public class StringLongConvert implements Converter<String, Long> {

    private static final StringLongConvert INSTANCE = new StringLongConvert();

    @Override
    public Long convert(MappingContext<String, Long> context) {
        if (context.getSource()!= null && !context.getSource().replaceAll("[,-]", "").isEmpty()) {
            return Long.valueOf(context.getSource().replaceAll("[,-]", ""));
        }
        return null;
    }

    public static StringLongConvert getInstance() {
        return INSTANCE;
    }
}
