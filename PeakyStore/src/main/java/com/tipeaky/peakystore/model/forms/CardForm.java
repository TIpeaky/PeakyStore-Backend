package com.tipeaky.peakystore.model.forms;

import com.tipeaky.peakystore.model.enums.CardTypeEnum;
import com.tipeaky.peakystore.model.enums.FlagEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
public class CardForm {

        @NotBlank
        private String number;
        @NotBlank
        private LocalDate expirationDate;
        @NotBlank
        private String securityCode;
        @NotBlank
        private FlagEnum flag;
        @NotBlank
        private CardTypeEnum cardType;


}

