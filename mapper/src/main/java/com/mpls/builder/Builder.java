package com.mpls.builder;


import com.mpls.DtoMapper;
import com.mpls.impl.DtoMapperImpl;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Builder {

    private static DtoMapper dtoMapper = new DtoMapperImpl();
    private static Object orElse;

    private Builder() {

    }

    @SafeVarargs
    public static <T> T map(Object dtoObject, Class<T>... parsingClasses) {
        return parsingClasses[0].cast(dtoMapper.parseFromDTOtoObject(dtoObject, parsingClasses));
    }
    public static <T> List<T> map(List<? extends Object> dtoObject,Class<T> parsingClasses) {
        return dtoObject.stream().map(o -> parsingClasses.cast(dtoMapper.parseFromDTOtoObject(o, parsingClasses))).collect(toList());
    }
}
