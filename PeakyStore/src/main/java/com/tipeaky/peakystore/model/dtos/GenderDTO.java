package com.tipeaky.peakystore.model.dtos;

import com.tipeaky.peakystore.model.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GenderDTO {
    private UUID id;
    private GenderEnum genderEnum;
}
