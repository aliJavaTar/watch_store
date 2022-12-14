package com.microsoft.whtch.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class WatchTest {

    @Test
    void shouldCreateAWatch() {
        Discount discount = Discount.create(Money.of(200L), 3);
        Watch watch = Watch.create(1L, "Rolex", Money.of(100L));

        watch.applyDiscount(discount);

        assertThat(watch.getId()).isEqualTo(1L);
        assertThat(watch.getName()).isEqualTo("Rolex");
        assertThat(watch.getPrice()).isEqualTo(Money.of(100L));
        assertThat(watch.getDiscount()).isEqualTo(discount);
    }

}
