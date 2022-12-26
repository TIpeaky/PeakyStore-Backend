package com.tipeaky.peakystore.model.forms;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class NewPasswordForm {
    @NotBlank
    @Length(min = 6, max = 50)
    private String password;

    @NotBlank
    @Length(min = 6, max = 50)
    private String newPassword;
}
