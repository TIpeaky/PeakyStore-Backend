package com.tipeaky.peakystore.filter;

import com.tipeaky.peakystore.model.enums.OperationEnum;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GenericSpecificationsBuilder<T> {

    private final List<SearchCriteria> params;
    private final List<Specification<T>> specifications;

    public GenericSpecificationsBuilder() {
        this.params = new ArrayList<>();
        this.specifications = new ArrayList<>();
    }

    public final GenericSpecificationsBuilder<T> with(final String key, final OperationEnum operationEnum, final List<Object> arguments) {
        return with(key, operationEnum, false, arguments);
    }

    public final GenericSpecificationsBuilder<T> with(final String key, final OperationEnum operationEnum, final boolean isOrOperation, final List<Object> arguments) {
        params.add(new SearchCriteria(key, operationEnum, isOrOperation, arguments));
        return this;
    }

    public final GenericSpecificationsBuilder<T> with(Specification<T> specification) {
        specifications.add(specification);
        return this;
    }

    public Specification<T> build() {
        Specification<T> result = null;
        if (!params.isEmpty()) {
            result = new GenericSpecification<>(params.get(0));
            for (int index = 1; index < params.size(); ++index) {
                SearchCriteria searchCriteria = params.get(index);

                System.out.println("Critério de busca: " + searchCriteria);

//                // ?
//
//                for(int i = 0; i < searchCriteria.getArguments().size(); i++) {
//
//                }
//
//                //

                result = searchCriteria.isOrOperation() ? Specification.where(result).
                        or(new GenericSpecification<>(searchCriteria)) : Specification.where(result).
                        and(new GenericSpecification<>(searchCriteria));
            }
        }
        if (!specifications.isEmpty()) {
            int index = 0;
            if (Objects.isNull(result)) {
                result = specifications.get(index++);
                System.out.println("Result 1: " + result.toString());
            }
            while (index < specifications.size()) {
                result = Specification.where(result).and(specifications.get(index));
                System.out.println("Result 2: " + result);
                ++index;
            }
        }
        return result;
    }

}
