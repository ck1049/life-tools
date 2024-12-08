package com.loafer.genshin;

import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author loafer
 * @since 2024-12-08 20:14:37
 **/
@Component
public class MapperUtils {

    public static ModelMapper modelMapper;

    @Resource
    public void setModelMapper(ModelMapper modelMapper) {
        MapperUtils.modelMapper = modelMapper;
    }

    public static <T> List<T> mapList(Collection<?> source, Class<T> targetType) {
        return source.stream().map(item -> modelMapper.map(item, targetType)).collect(Collectors.toList());
    }
}
