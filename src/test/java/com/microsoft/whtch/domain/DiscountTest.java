package com.microsoft.whtch.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DiscountTest {

    @Test
    void shouldCalculateDiscountedPriceWithExactNumberOfDiscountItem() {
        Discount discount = Discount.create(120L, 2);

        int itemCount = 2;
        Long unitPrice = 80L;

        Long discountedPrice = discount.calculatePriceOf(itemCount, unitPrice);

        assertThat(discountedPrice).isEqualTo(120L);
    }

    @Test
    void shouldCalculateDiscountedPriceWithMoreThanNumberOfDiscountItem() {
        Discount discount = Discount.create(120L, 2);

        int itemCount = 3;
        Long unitPrice = 80L;

        Long discountedPrice = discount.calculatePriceOf(itemCount, unitPrice);

        assertThat(discountedPrice).isEqualTo(200L);
    }

    @Test
    void shouldCalculateDiscountedPriceWithLessThanNumberOfDiscountItem() {
        Discount discount = Discount.create(120L, 2);

        int itemCount = 1;
        Long unitPrice = 80L;

        Long discountedPrice = discount.calculatePriceOf(itemCount, unitPrice);

        assertThat(discountedPrice).isEqualTo(80L);
    }

}