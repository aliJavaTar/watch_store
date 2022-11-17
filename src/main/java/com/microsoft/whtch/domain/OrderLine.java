package com.microsoft.whtch.domain;

public class OrderLine {
    private final Watch watch;
    private final int count;

    public OrderLine(Watch watch, int count) {
        this.watch = watch;
        this.count = count;
    }

    public Long calculatePrice() {
        Discount discount = watch.getDiscount();

        if (discount == null) {
            return watch.getPrice() * count;
        }

        return discount.calculatePriceOf(count, watch.getPrice());
    }
}
