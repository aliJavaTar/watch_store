package com.microsoft.whtch.domain;

import com.microsoft.whtch.domain.base.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "watch")
public class Watch extends BaseEntity<Long> {

    private String name;
    private Long price;

    private Watch(Long id, String name, Long price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public static Watch create(Long id, String name, Long price) {
        return new Watch(id, name, price);
    }

    @OneToOne
    @JoinColumn(name = "discount_id", referencedColumnName = "id")
    private Discount discount;

    public void applyDiscount(Discount discount) {
        this.discount = discount;
    }

}
