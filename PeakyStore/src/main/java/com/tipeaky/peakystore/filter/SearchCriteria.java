package com.tipeaky.peakystore.filter;

import com.tipeaky.peakystore.model.enums.OperationEnum;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {

    private String key;
    private OperationEnum operationEnum;
    private boolean isOrOperation;
    private List<Object> arguments;

}
