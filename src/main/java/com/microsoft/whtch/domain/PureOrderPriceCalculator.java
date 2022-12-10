package com.microsoft.whtch.domain;

public class PureOrderPriceCalculator implements OrderPriceCalculator {
    public Money calculateTotalPrice(Order order) {
        Money total = Money.of(0L);

        for (OrderLine orderLine : order.getOrderLines()) {
            total = total.add(orderLine.calculatePrice());
        }

        // we are not allowed to contact infra layer since it is a pure service

        return total;
    }
}
