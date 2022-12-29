package com.tipeaky.peakystore.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

@Slf4j
public class GenericSpecification<T> implements Specification<T> {

    private SearchCriteria searchCriteria;

    public GenericSpecification(final SearchCriteria searchCriteria) {
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Object> arguments = searchCriteria.getArguments();
        Object arg = arguments.get(0);

        switch (searchCriteria.getOperationEnum()) {
            case EQUALITY:
                return criteriaBuilder.equal(root.get(searchCriteria.getKey()), arg);
            case GREATER_THAN:
                return criteriaBuilder.greaterThan(root.get(searchCriteria.getKey()), (Comparable) arg);
        }
        return null;
    }

}













