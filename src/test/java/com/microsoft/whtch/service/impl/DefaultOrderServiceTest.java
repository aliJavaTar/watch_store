package com.microsoft.whtch.service.impl;

import com.microsoft.whtch.domain.Watch;
import com.microsoft.whtch.domain.WatchRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class DefaultOrderServiceTest {

    @Test
    void shouldGetOrderTotalPrice() {
        WatchRepository watchRepository = mock(WatchRepository.class);

        Watch rolex = Watch.create(1L, "Rolex", 100L);
        doReturn(rolex).when(watchRepository).findById(1L);

        Watch michael = Watch.create(2L, "Michael", 80L);
        doReturn(michael).when(watchRepository).findById(2L);

        Watch swatch = Watch.create(3L, "Swatch", 50L);
        doReturn(swatch).when(watchRepository).findById(3L);

        Watch casio = Watch.create(4L, "Casio", 30L);
        doReturn(casio).when(watchRepository).findById(4L);

        DefaultOrderService service = new DefaultOrderService(watchRepository);

        List<Long> itemIds = List.of(1L, 2L, 1L, 4L, 3L);
        Long totalPrice = service.getOrderTotalPrice(itemIds);

        assertThat(totalPrice).isEqualTo(360);
    }

}