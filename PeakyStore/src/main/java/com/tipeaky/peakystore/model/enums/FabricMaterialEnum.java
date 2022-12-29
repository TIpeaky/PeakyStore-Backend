package com.tipeaky.peakystore.model.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum FabricMaterialEnum {
    COTTON("CO", "Cotton"),
    WOOL("WO", "Wool"),
    SILK("SI", "Silk"),
    LINEN("LI", "Linen"),
    POLYESTER("PO", "Polyester"),
    ELASTANE("EL", "Elastane"),
    UNSPECIFIED("UN", "Unspecified");

    private final String key;
    private final String description;

    FabricMaterialEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public static final Map<String, FabricMaterialEnum> fabricMaterialMap = new HashMap<>();
    static {
        for (FabricMaterialEnum fabricMaterialEnum : EnumSet.allOf(FabricMaterialEnum.class)) {
            fabricMaterialMap.put(fabricMaterialEnum.getKey(), fabricMaterialEnum);
        }
    }

    public static FabricMaterialEnum get(String string) {
        return fabricMaterialMap.get(string);
    }
}