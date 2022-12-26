package com.tipeaky.peakystore.model.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY) //Não mostrará atributos nulos e nem vazios
public class AddressDTO {
    private UUID id;
    private String zipCode;
    private String publicPlace;
    private Integer number;
    private String city;
    private String state;
    private String country;
    private String complement;
    private UUID userId;
    private String userName;
    private String userCpf;
}
