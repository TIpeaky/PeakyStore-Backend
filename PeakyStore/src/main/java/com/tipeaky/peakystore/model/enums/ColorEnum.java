package com.tipeaky.peakystore.model.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ColorEnum {
    BLUE("BL", "Blue"),
    GREEN("GR", "Green"),
    YELLOW("YE", "Yellow"),
    PURPLE("PU", "Purple"),
    PINK("PI", "Pink"),
    RED("RE", "Red"),
    ORANGE("OR", "Orange"),
    BROWN("BR", "Brown"),
    GREY("GR", "Grey"),
    WHITE("WH", "White"),
    BLACK("BA", "Black");

    private String key;
    private String description;

    ColorEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public static final Map<String, ColorEnum> colorEnumMap = new HashMap<String, ColorEnum>();
    static {
        for (ColorEnum colorEnum : EnumSet.allOf(ColorEnum.class)) {
            colorEnumMap.put(colorEnum.getKey(), colorEnum);
        }
    }

    public static ColorEnum get(String string) {
        return colorEnumMap.get(string);
    }

}
