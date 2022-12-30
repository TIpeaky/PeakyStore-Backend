package com.tipeaky.peakystore.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvaliationDTO {
    private UUID id;
    private Integer stars;
    private String comment;
    private LocalDateTime sent;
    private String userName;

}

