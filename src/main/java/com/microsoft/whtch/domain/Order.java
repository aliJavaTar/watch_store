package com.microsoft.whtch.domain;

import java.util.HashSet;
import java.util.Set;

public class Order {
    private final Set<OrderLine> orderLines = new HashSet<>();

    public void addOrderLine(Watch watch, int count) {
        this.orderLines.add(new OrderLine(watch, count));
    }

    public Set<OrderLine> getOrderLines() {
        return orderLines;
    }

    public Money totalPrice() {
        Money total = Money.of(0L);

        for (OrderLine orderLine : this.orderLines) {
            total = total.add(orderLine.calculatePrice());
        }

        return total;
    }
}
