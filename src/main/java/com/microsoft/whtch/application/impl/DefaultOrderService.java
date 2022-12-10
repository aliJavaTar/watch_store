package com.microsoft.whtch.application.impl;


import com.microsoft.whtch.domain.*;
import com.microsoft.whtch.application.OrderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DefaultOrderService implements OrderService {

    private final WatchRepository repository;
    private final OrderPriceCalculator calculator;

    public DefaultOrderService(
            @Qualifier("inMemoryWatchRepository") WatchRepository repository, OrderPriceCalculator calculator) {
        this.repository = repository;
        this.calculator = calculator;
    }

    public Long getOrderTotalPrice(List<Long> ids) {
        Map<Long, Integer> orderCountPerItem = new HashMap<>();

        ids.forEach(id -> {
            int count = Collections.frequency(ids, id);
            orderCountPerItem.put(id, count);
        });

        Order order = new Order();

        orderCountPerItem.forEach((id, count) -> {
            Watch watch = repository.findById(id);
            order.addOrderLine(watch, count);
        });

        // We can call totalPrice from entity directly
//        Money totalPrice = order.totalPrice().getAmount();
//        OrderPriceCalculator calculator = new PureOrderPriceCalculator();

        // or we can use a domain service (pure or impure)
        Money totalPrice = calculator.calculateTotalPrice(order);

        return totalPrice.getAmount();
    }

}