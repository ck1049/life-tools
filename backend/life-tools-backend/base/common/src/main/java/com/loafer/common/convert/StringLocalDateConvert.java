package com.loafer.common.convert;

import cn.hutool.core.date.LocalDateTimeUtil;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import java.time.LocalDate;

/**
 * @author loafer
 * @since 2023-11-04 23:53:15
 **/
public class StringLocalDateConvert implements Converter<String, LocalDate> {

    private static final StringLocalDateConvert INSTANCE = new StringLocalDateConvert();

    @Override
    public LocalDate convert(MappingContext<String, LocalDate> context) {
        if (context.getSource()!= null && !context.getSource().isEmpty()) {
            return LocalDateTimeUtil.parseDate(context.getSource(), "yyyy-MM-dd");
        }
        return null;
    }

    public static StringLocalDateConvert getInstance(){
        return INSTANCE;
    }

}
