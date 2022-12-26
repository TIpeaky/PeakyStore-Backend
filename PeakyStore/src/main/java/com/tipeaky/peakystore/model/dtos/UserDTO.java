package com.tipeaky.peakystore.model.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.tipeaky.peakystore.model.entities.Gender;
import com.tipeaky.peakystore.model.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY) //Não mostrará atributos nulos e nem vazios
public class UserDTO {
    private UUID id;
    private String cpf;
    private String name;
    private String email;
    //private Longblob avatar; imagem do usuário
    private List<GenderDTO> genderDTOList;
    private LocalDate birthDate;
    private Boolean notification;
    private List<AddressDTO> addressDTOList;

}
