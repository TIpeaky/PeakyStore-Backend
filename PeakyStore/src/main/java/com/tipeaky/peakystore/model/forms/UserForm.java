package com.tipeaky.peakystore.model.forms;

import com.tipeaky.peakystore.model.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class UserForm {
    @CPF @NotBlank
    private String cpf;
    @NotBlank @Length(min = 3, max = 50)
    private String name;
    @Email
    @NotBlank
    private String email;
    @NotBlank @Length(min = 6, max = 50)
    private String password;
    @NotEmpty
    private List<GenderForm> genderFormList = new ArrayList<>();
    @NotNull @Past
    private LocalDate birthDate;
    private Boolean notification;
}
