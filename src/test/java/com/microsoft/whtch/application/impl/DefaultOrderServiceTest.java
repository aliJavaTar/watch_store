package com.microsoft.whtch.application.impl;

import com.microsoft.whtch.domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DefaultOrderServiceTest {

    @Test
    void shouldGetOrderTotalPriceWithPureOrderCalculatorService() {
        WatchRepository watchRepository = mock(WatchRepository.class);

        Watch rolex = Watch.create(1L, "Rolex", Money.of(100L));
        doReturn(rolex).when(watchRepository).findById(1L);

        Watch michael = Watch.create(2L, "Michael", Money.of(80L));
        doReturn(michael).when(watchRepository).findById(2L);

        Watch swatch = Watch.create(3L, "Swatch", Money.of(50L));
        doReturn(swatch).when(watchRepository).findById(3L);

        Watch casio = Watch.create(4L, "Casio", Money.of(30L));
        doReturn(casio).when(watchRepository).findById(4L);

        OrderRepository orderRepository = mock(OrderRepository.class);

        OrderPriceCalculator calculator = new PureOrderPriceCalculator();

        DefaultOrderService service = new DefaultOrderService(watchRepository, orderRepository, calculator);

        List<Long> itemIds = List.of(1L, 2L, 1L, 4L, 3L);
        Long totalPrice = service.getOrderTotalPrice(itemIds);

        assertThat(totalPrice).isEqualTo(360);
    }

    @Test
    void shouldGetOrderTotalPriceWithImpureOrderCalculatorService() {
        WatchRepository watchRepository = mock(WatchRepository.class);

        Watch rolex = Watch.create(1L, "Rolex", Money.of(100L));
        doReturn(rolex).when(watchRepository).findById(1L);

        Watch michael = Watch.create(2L, "Michael", Money.of(80L));
        doReturn(michael).when(watchRepository).findById(2L);

        Watch swatch = Watch.create(3L, "Swatch", Money.of(50L));
        doReturn(swatch).when(watchRepository).findById(3L);

        Watch casio = Watch.create(4L, "Casio", Money.of(30L));
        doReturn(casio).when(watchRepository).findById(4L);

        OrderRepository orderRepository = mock(OrderRepository.class);

        OrderPriceCalculator calculator = mock(PureOrderPriceCalculator.class);
        doReturn(Money.of(360L)).when(calculator).calculateTotalPrice(any());

        DefaultOrderService service = new DefaultOrderService(watchRepository, orderRepository, calculator);

        List<Long> itemIds = List.of(1L, 2L, 1L, 4L, 3L);
        Long totalPrice = service.getOrderTotalPrice(itemIds);

        assertThat(totalPrice).isEqualTo(360);
    }

    @Test
    void shouldAcceptAnOrder() {
        WatchRepository watchRepository = mock(WatchRepository.class);

        Watch rolex = Watch.create(1L, "Rolex", Money.of(100L));
        doReturn(rolex).when(watchRepository).findById(1L);

        Order order = Order.place(1L);
        order.addOrderLine(rolex, 2);

        OrderRepository orderRepository = mock(OrderRepository.class);
        doReturn(order).when(orderRepository).findById(any());
        doNothing().when(orderRepository).save(any());
        doReturn(1L).when(orderRepository).nextId();

        OrderPriceCalculator calculator = mock(PureOrderPriceCalculator.class);
        doReturn(Money.of(200L)).when(calculator).calculateTotalPrice(any());

        DefaultOrderService service = new DefaultOrderService(watchRepository, orderRepository, calculator);

        List<Long> itemIds = List.of(1L);
        Long totalPrice = service.getOrderTotalPrice(itemIds);

        assertThat(totalPrice).isEqualTo(200);
    }

}