package com.tipeaky.peakystore.model.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tipeaky.peakystore.model.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY) //Não mostrará atributos nulos e nem vazios
public class ProductDTO {

    private UUID id;
    private String sku;
    private String name;
    private String description;
    private BigDecimal purchasePrice;
    private BigDecimal salePrice;
    private Integer stockQuantity;
    private BrandEnum productBrand;
    private LocalDateTime lastUpdateDate;
    private ColorEnum color;
    private SizeEnum size;
    private CategoryEnum category;
    private SectionEnum section;

}
