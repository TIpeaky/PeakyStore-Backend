package com.tipeaky.peakystore.model.forms;

import com.tipeaky.peakystore.model.enums.CategoryEnum;
import com.tipeaky.peakystore.model.enums.ColorEnum;
import com.tipeaky.peakystore.model.enums.SectionEnum;
import com.tipeaky.peakystore.model.enums.SizeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private UUID id;
    private String name;
    private String description;
    @Min(0)
    private BigDecimal purchasePrice;
    @Min(0)
    private BigDecimal salePrice;
    @Min(0)
    private Integer stockQuantity;
    private String productBrand;
    private ColorEnum color;
    private SizeEnum size;
    private CategoryEnum category;
    private SectionEnum section;


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
