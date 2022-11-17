package com.microsoft.whtch.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderLineTest {

    @Test
    void shouldCalculatePriceForAnOrderLine() {
        Watch rolex = Watch.create(1L, "Rolex", 100L);
        rolex.applyDiscount(Discount.create(200L, 3));

        OrderLine orderLine = new OrderLine(rolex, 3);

        assertThat(orderLine.calculatePrice()).isEqualTo(200L);
    }

    @Test
    void shouldCalculatePriceForAnOrderLineWithoutDiscount() {
        Watch rolex = Watch.create(1L, "Rolex", 100L);

        OrderLine orderLine = new OrderLine(rolex, 1);

        assertThat(orderLine.calculatePrice()).isEqualTo(100L);
    }

}