package com.tipeaky.peakystore.model.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum CollectionEnum {
    WINTER("WI", "Winter"),
    SUMMER("SU", "Summer"),
    SPRING("SP", "Spring"),
    AUTUMN("AU", "Autumn"),
    BASICS("BA", "Basics"),
    MODERN("MO", "Modern"),
    UNSPECIFIED("UN", "Unspecified");

    private final String key;
    private final String description;

    CollectionEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public static final Map<String, CollectionEnum> collectionEnumMap = new HashMap<>();
    static {
        for (CollectionEnum collectionEnum : EnumSet.allOf(CollectionEnum.class)) {
            collectionEnumMap.put(collectionEnum.getKey(), collectionEnum);
        }
    }

    public static CollectionEnum get(String string) {
        return collectionEnumMap.get(string);
    }
}