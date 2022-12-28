package com.microsoft.whtch.domain;

import com.microsoft.whtch.domain.base.BaseEntity;
import lombok.Getter;

@Getter
public class Watch extends BaseEntity<Long> {

    private final String name;
    private final Money price;
    private Discount discount;

    private Watch(Long id, String name, Money price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public static Watch create(Long id, String name, Money price) {
        return new Watch(id, name, price);
    }

    public void applyDiscount(Discount discount) {
        this.discount = discount;
    }

}
