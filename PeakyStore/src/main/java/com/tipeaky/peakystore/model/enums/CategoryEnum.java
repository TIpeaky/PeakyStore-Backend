package com.tipeaky.peakystore.model.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum CategoryEnum {
    SHIRT("SHI", "Shirt"),
    TSHIRT("TSH", "Tshirt"),
    BLOUSE("BLO", "Blouse"),
    JACKET("JAC", "Jacket"),
    SWEATER("SWE", "Sweater"),
    SKIRT("SKI", "Skirt"),
    DRESS("DRE", "Dress"),
    PANTS("PAN", "Pants"),
    JEANS("JEA", "Jeans"),
    SHORTS("SHO", "Shorts"),
    SWINSUITS("SWI", "Swingsuits"),
    BATHROBE("BAT", "Bathrobe"),
    PAJAMAS("PAJ", "Pajamas"),
    NIGHTGOWN("NIG", "Nightgown"),
    PANTS_HOSE("PAN", "Pants Hose"),
    UNDERWEAR("UND", "Underwear"),
    COAT("COA", "Coat");

    private String key;
    private String description;

    CategoryEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public static final Map<String, CategoryEnum> categoryEnumMap = new HashMap<String, CategoryEnum>();
    static {
        for (CategoryEnum categoryEnum : EnumSet.allOf(CategoryEnum.class)) {
            categoryEnumMap.put(categoryEnum.getKey(), categoryEnum);
        }
    }

    public static CategoryEnum get(String string) {
        return categoryEnumMap.get(string);
    }

}
