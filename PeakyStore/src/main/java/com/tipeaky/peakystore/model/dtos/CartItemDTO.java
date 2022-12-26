package com.tipeaky.peakystore.model.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY) //Não mostrará atributos nulos e nem vazios
public class CartItemDTO {
    private UUID id;
    private Integer quantity;
    private ProductDTO product;
    private BigDecimal totalPrice;
}
