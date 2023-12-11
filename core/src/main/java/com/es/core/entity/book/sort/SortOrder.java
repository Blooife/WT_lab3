package com.es.core.entity.book.sort;

import java.util.Arrays;

public enum SortOrder {
    ASC,
    DESC;
    public static SortOrder getValue(String name) {
        if (name == null){
            return null;
        }
        return Arrays.stream(SortOrder.values())
                .filter(value -> value.name().equals(name.toUpperCase()))
                .findAny()
                .orElse(null);
    }
}
