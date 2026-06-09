package com.amalvadkar.ums.common.aspects;

import java.util.stream.Stream;

public final class AspectArgumentUtils {

    public static <T> T findArgument(Object[] args, Class<T> type) {
        return Stream.of(args)
                .filter(type::isInstance)
                .map(type::cast)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(type.getSimpleName() + " not found"));
    }
}