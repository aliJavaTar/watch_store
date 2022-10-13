package com.microsoft.whtch.domain;

import org.junit.jupiter.api.Test;

public class WatchTest {

    @Test
    void shouldCreateAWatch() {
        Discount discount = Discount.create(1L, 200L, 3L);
        Watch watch = Watch.create(1L, "Rolex", 100L);
    }

}
