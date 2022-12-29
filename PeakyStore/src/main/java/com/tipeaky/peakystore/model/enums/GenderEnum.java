package com.tipeaky.peakystore.model.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum GenderEnum {
    MALE("Masculino"),
    FEMALE("Feminino"),
    KIDS("Infantil"),
    UNINFORMED("Outro");

    private String description;

    public String getDescription() {
        return description;
    }

    GenderEnum(String description) {
        this.description = description;
    }

    private static final Map<String, GenderEnum> Lookup = new HashMap<>();

    static {
        for (GenderEnum key : EnumSet.allOf(GenderEnum.class))
            Lookup.put(key.getDescription(), key);
    }

    public static GenderEnum getByDescription(String description) {
        for (GenderEnum key: EnumSet.allOf(GenderEnum.class)) {
            if (key.getDescription().equals(description)) {
                return key;
            }
        }
        return null;
    }
}
