package com.microsoft.whtch.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DiscountTest {

    @Test
    void shouldCalculateDiscountedPriceWithExactNumberOfDiscountItem() {
        Discount discount = Discount.create(Money.of(120L), 2);

        int itemCount = 2;
        Money unitPrice = Money.of(80L);

        Money discountedPrice = discount.calculatePriceOf(itemCount, unitPrice);

        assertThat(discountedPrice.equals(Money.of(120L))).isTrue();
    }

    @Test
    void shouldCalculateDiscountedPriceWithMoreThanNumberOfDiscountItem() {
        Discount discount = Discount.create(Money.of(120L), 2);

        int itemCount = 3;
        Money unitPrice = Money.of(80L);

        Money discountedPrice = discount.calculatePriceOf(itemCount, unitPrice);

        assertThat(discountedPrice.equals(Money.of(200L))).isTrue();
    }

    @Test
    void shouldCalculateDiscountedPriceWithLessThanNumberOfDiscountItem() {
        Discount discount = Discount.create(Money.of(120L), 2);

        int itemCount = 1;
        Money unitPrice = Money.of(80L);

        Money discountedPrice = discount.calculatePriceOf(itemCount, unitPrice);

        assertThat(discountedPrice.equals(Money.of(80L))).isTrue();
    }

}