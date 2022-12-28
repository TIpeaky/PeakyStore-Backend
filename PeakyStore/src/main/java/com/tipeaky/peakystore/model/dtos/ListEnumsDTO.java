package com.tipeaky.peakystore.model.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ListEnumsDTO {

    private List<EnumsDTO> productBrand;
    private List<EnumsDTO> color;
    private List<EnumsDTO> size;
    private List<EnumsDTO> category;
    private List<EnumsDTO> section;
}
