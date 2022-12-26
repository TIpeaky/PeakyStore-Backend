package com.tipeaky.peakystore.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tipeaky.peakystore.model.enums.PaymentEnum;
import com.tipeaky.peakystore.model.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    @Column(nullable = false)
    private LocalDateTime orderMadeDateTime;
    private LocalDateTime paymentConfirmedDateTime;
    private LocalDateTime orderDispatchedDateTime;
    private LocalDateTime orderDeliveredDateTime;
    private LocalDateTime orderReturnedDateTime;
    @Column(nullable = false)
    private BigDecimal totalValue;
    @Column(nullable = false)
    private PaymentEnum payment;
    @Column(nullable = false)
    private StatusEnum status;
    @Column(nullable = false)
    private Boolean isDelivered = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CartItem> cartItemList;

    @OneToOne
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;

}
