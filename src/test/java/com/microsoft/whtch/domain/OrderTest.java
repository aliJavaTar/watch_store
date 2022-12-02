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

        Order order = new Order();
        order.addOrderLine(rolex, 7); // 500
        order.addOrderLine(mk, 3); // 200

        assertThat(order.totalPrice().equals(Money.of(700L))).isTrue();
    }

}