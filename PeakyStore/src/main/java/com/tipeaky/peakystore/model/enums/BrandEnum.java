package com.tipeaky.peakystore.model.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BrandEnum {

    NIKE("NI", "Nike"),
    ADIDAS("AD", "Adidas"),
    PUMA("PU", "Puma"),
    POLO_WEAR("PO", "Polo Wear"),
    FILA("FI", "Fila"),
    LACOSTE("LA", "Lacoste"),
    GUCCI("GU", "Gucci"),
    LUPO("LU", "Lupo");

    private String key;
    private String description;

    BrandEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public static final Map<String, BrandEnum> brandEnumMap = new HashMap<String, BrandEnum>();
    static {
        for (BrandEnum brandEnum : EnumSet.allOf(BrandEnum.class)) {
            brandEnumMap.put(brandEnum.getKey(), brandEnum);
        }
    }

    public static BrandEnum get(String string) {
        return brandEnumMap.get(string);
    }

}
