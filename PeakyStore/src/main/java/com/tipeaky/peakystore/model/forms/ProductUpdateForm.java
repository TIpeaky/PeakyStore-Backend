package com.tipeaky.peakystore.model.forms;

import com.tipeaky.peakystore.model.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class ProductUpdateForm {
    @NotNull
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    @Min(0)
    private BigDecimal purchasePrice;
    @NotNull
    @Min(0)
    private BigDecimal salePrice;
    @NotNull
    @Min(0)
    private Integer stockQuantity;
    @NotNull
    private BrandEnum productBrand;
    @NotNull
    private ColorEnum color;
    @NotNull
    private SizeEnum size;
    @NotNull
    private CategoryEnum category;
    @NotNull
    private SectionEnum section;
    @NotNull @Min(1900) @Max(2099)
    private Integer releaseYear;
    @NotNull
    private CollectionEnum collection;
    @NotNull
    private FabricMaterialEnum fabricMaterial;


    //verificar se todos os atributos são nulos
    public boolean checkNull() {
        for (Field f : getClass().getDeclaredFields()) {
            try {
                if (f.get(this) != null)
                    return false;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
