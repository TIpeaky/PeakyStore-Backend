package com.tipeaky.peakystore.model.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tipeaky.peakystore.model.enums.StatusEmailEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmailDTO {

    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmailEnum statusEmail;

}
