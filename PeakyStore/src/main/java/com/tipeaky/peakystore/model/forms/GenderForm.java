package com.tipeaky.peakystore.model.forms;

import com.tipeaky.peakystore.model.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GenderForm {

    @NotNull
    private GenderEnum genderEnum;

}
