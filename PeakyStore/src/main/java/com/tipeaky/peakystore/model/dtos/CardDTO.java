package com.tipeaky.peakystore.model.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tipeaky.peakystore.model.enums.CardTypeEnum;
import com.tipeaky.peakystore.model.enums.FlagEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CardDTO {

    private UUID id;
    private String number;
    private FlagEnum flag;
    private CardTypeEnum cardType;
}
