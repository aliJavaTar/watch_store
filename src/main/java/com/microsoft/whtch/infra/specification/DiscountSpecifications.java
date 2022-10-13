package com.microsoft.whtch.infra.specification;

import com.microsoft.whtch.domain.Watch;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface DiscountSpecifications {
    Specification<Watch> findAll(List<Long> ids);
}
