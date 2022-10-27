package com.microsoft.whtch.domain;

import com.microsoft.whtch.domain.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "discount")
public class Discount extends BaseEntity<Long> {
    private Long amountDiscount;
    private Long count;

    private Discount(Long id, Long amountDiscount, Long count) {
        super(id);
        this.amountDiscount = amountDiscount;
        this.count = count;
    }

    public static Discount create(Long id, Long amountDiscount, Long count) {
        return new Discount(id, amountDiscount, count);
    }

    @OneToOne(mappedBy = "discount")
    private Watch watch;

    public void setWatch(Watch watch) {
        this.watch = watch;
    }
}
