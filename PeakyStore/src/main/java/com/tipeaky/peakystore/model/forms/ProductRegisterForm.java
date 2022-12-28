package com.tipeaky.peakystore.model.forms;

import com.tipeaky.peakystore.model.enums.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductRegisterForm {

    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @DecimalMin("0")
    private BigDecimal purchasePrice;
    @DecimalMin("0")
    private BigDecimal salePrice;
    @Min(0)
    private Integer stockQuantity;
    @NotBlank
    private String productBrand;
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
}
