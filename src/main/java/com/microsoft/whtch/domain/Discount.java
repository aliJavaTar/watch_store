package com.microsoft.whtch.domain;

import lombok.Getter;

@Getter
public class Discount {
    private final Money amountDiscount;
    private final int count;

    private Discount(Money amountDiscount, int count) {
        this.amountDiscount = amountDiscount;
        this.count = count;
    }

    public static Discount create(Money amountDiscount, int count) {
        return new Discount(amountDiscount, count);
    }

    public Money calculatePriceOf(int itemCount, Money unitPrice) {
        int numberOfDiscountedItems = Math.abs(itemCount / this.count);
        int numberOfItemsWithoutDiscount = Math.abs(itemCount % this.count);

        return this.amountDiscount
                .multiplyBy(numberOfDiscountedItems)
                .add(unitPrice.multiplyBy(numberOfItemsWithoutDiscount));
    }
}
