package com.tipeaky.peakystore.model.entities;

import com.tipeaky.peakystore.model.enums.CardTypeEnum;
import com.tipeaky.peakystore.model.enums.FlagEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private LocalDate expirationDate;
    @Column(nullable = false)
    private String securityCode;
    @Column(nullable = false)
    private FlagEnum flag;
    @Column(nullable = false)
    private CardTypeEnum cardType;

}
