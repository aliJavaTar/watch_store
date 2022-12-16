package com.microsoft.whtch.domain;

import java.util.HashSet;
import java.util.Set;

public class Order {

    private final Long id;

    private OrderStatus status;

    private final Set<OrderLine> orderLines = new HashSet<>();

    private Order(Long id) {
        this.id = id;
    }

    public static Order place(Long id) {
        Order order = new Order(id);
        order.status = OrderStatus.NEW;
        return order;
    }

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

    public void accept() {
        this.status = OrderStatus.ACCEPTED;
    }

    public void reject() {
        this.status = OrderStatus.REJECTED;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }
}
