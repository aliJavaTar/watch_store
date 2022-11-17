package com.microsoft.whtch.domain;

import java.util.HashSet;
import java.util.Set;

public class Order {
    private final Set<OrderLine> orderLines = new HashSet<>();

    public void addOrderLine(Watch watch, int count) {
        this.orderLines.add(new OrderLine(watch, count));
    }

    public Long totalPrice() {
        Long total = 0L;

        for (OrderLine orderLine : this.orderLines) {
            total += orderLine.calculatePrice();
        }

        return total;
    }
}
