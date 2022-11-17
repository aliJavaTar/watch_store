package com.microsoft.whtch.domain;

import lombok.Getter;

@Getter
public class Discount {
    private final Long amountDiscount;
    private final int count;

    private Discount(Long amountDiscount, int count) {
        this.amountDiscount = amountDiscount;
        this.count = count;
    }

    public static Discount create(Long amountDiscount, int count) {
        return new Discount(amountDiscount, count);
    }

    public Long calculatePriceOf(int itemCount, Long unitPrice) {
        int numberOfDiscountedItems = Math.abs(itemCount / this.count);
        int numberOfItemsWithoutDiscount = Math.abs(itemCount % this.count);

        return numberOfDiscountedItems * this.amountDiscount + numberOfItemsWithoutDiscount * unitPrice;
    }
}
