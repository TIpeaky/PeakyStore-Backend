package com.tipeaky.peakystore.model.forms;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CartItemForm {
    @Min(1)
    private Integer quantity;
    @NotNull
    private ProductIdForm product;
}
