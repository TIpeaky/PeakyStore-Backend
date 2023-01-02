package com.tipeaky.peakystore.model.forms;

import com.tipeaky.peakystore.model.entities.ProductImage;
import com.tipeaky.peakystore.model.enums.CategoryEnum;
import com.tipeaky.peakystore.model.enums.ColorEnum;
import com.tipeaky.peakystore.model.enums.SectionEnum;
import com.tipeaky.peakystore.model.enums.SizeEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    @NotEmpty
    private MultipartFile[] images;

}
