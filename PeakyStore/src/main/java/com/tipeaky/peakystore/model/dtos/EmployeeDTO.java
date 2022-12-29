package com.tipeaky.peakystore.model.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tipeaky.peakystore.model.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmployeeDTO {

    private UUID id;
    private String cpf;
    private String name;
    private String email;
    private GenderEnum gender;
    private LocalDate birthDate;
    private String genderDescription;
}
