package com.tipeaky.peakystore.model.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.lang.reflect.Field;

@Getter
@Setter
public class AddressRegisterForm {
    @NotBlank
    private String zipCode;
    @NotBlank
    private String publicPlace;
    @NotNull
    @Positive
    private Integer number;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    @NotBlank
    private String country;

    private String complement;

    public boolean checkNull() {
        for (Field f : getClass().getDeclaredFields()) {
            try {
                if (f.get(this) != null)
                    return false;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
