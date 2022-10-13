package com.microsoft.whtch.infra.specification.impl;

import com.microsoft.whtch.domain.Watch;
import com.microsoft.whtch.infra.specification.DiscountSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiscountSpecificationsImpl implements DiscountSpecifications {
    @Override
    public Specification<Watch> findAll(List<Long> ids) {
        return (root, query, criteriaBuilder) -> root.get("id").in(ids);
    }
}
