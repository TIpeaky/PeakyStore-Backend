package com.tipeaky.peakystore.model.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tipeaky.peakystore.model.entities.Address;
import com.tipeaky.peakystore.model.entities.CartItem;
import com.tipeaky.peakystore.model.entities.User;
import com.tipeaky.peakystore.model.enums.PaymentEnum;
import com.tipeaky.peakystore.model.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY) //Não mostrará atributos nulos e nem vazios
public class PurchaseDTO {
    private UUID id;
    private LocalDateTime orderMadeDateTime;
    private LocalDateTime paymentConfirmedDateTime;
    private LocalDateTime orderDispatchedDateTime;
    private LocalDateTime orderDeliveredDateTime;
    private LocalDateTime orderReturnedDateTime;
    private BigDecimal totalValue;
    private PaymentEnum payment;
    private StatusEnum status;
    private boolean isDelivered;
    private UserDTO user;
    private List<CartItemDTO> cartItemList;
    private AddressDTO deliveryAddress;
}
