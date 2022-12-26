package com.tipeaky.peakystore.model.forms;

import com.tipeaky.peakystore.model.entities.Address;
import com.tipeaky.peakystore.model.enums.IsDelivered;
import com.tipeaky.peakystore.model.enums.PaymentEnum;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PurchaseForm {
    @NotNull
    private PaymentEnum payment;
    @NotNull
    private Boolean isDelivered;
    @NotEmpty
    private List<CartItemForm> cartItemList;
    private AddressFormId deliveryAddress;
    //private Address deliveryAddress;
}
