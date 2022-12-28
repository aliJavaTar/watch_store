package com.microsoft.whtch.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderTest {

    @Test
    void shouldCreateAnOrder() {
        Watch rolex = Watch.create(1L, "Rolex", Money.of(100L));
        rolex.applyDiscount(Discount.create(Money.of(200L), 3));

        Watch mk = Watch.create(2L, "Michael Kors", Money.of(80L));
        mk.applyDiscount(Discount.create(Money.of(120L), 2));

        Order order = Order.place(1L);
        order.addOrderLine(rolex, 7); // 500
        order.addOrderLine(mk, 3); // 200

        assertThat(order.totalPrice().equals(Money.of(700L))).isTrue();
        assertThat(order.getStatus()).isEqualTo(OrderStatus.NEW);
    }

    @Test
    void shouldAcceptAnOrder() {
        Watch rolex = Watch.create(1L, "Rolex", Money.of(100L));

        Order order = Order.place(1L);
        order.addOrderLine(rolex, 2);

        order.accept();

        assertThat(order.getStatus()).isEqualTo(OrderStatus.ACCEPTED);
    }

    @Test
    void shouldRejectAnOrder() {
        Watch rolex = Watch.create(1L, "Rolex", Money.of(100L));

        Order order = Order.place(1L);
        order.addOrderLine(rolex, 2);

        order.reject();

        assertThat(order.getStatus()).isEqualTo(OrderStatus.REJECTED);
    }

    @Test
    void should_Change_Status_Of_Order_To_NEW() {
        Order order = Order.place(1L);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.NEW);
    }

    @Test
    void should_Give_IllegalStateException_when_money_IsMoreThan_2000() {
        Throwable thrown = catchThrowable(() -> Order.place(1L));
        assertThat(thrown)
                .isInstanceOf(IllegalStateException.class);
    }


}