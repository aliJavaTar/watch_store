package com.microsoft.whtch.service.impl;


import com.microsoft.whtch.domain.Order;
import com.microsoft.whtch.domain.Watch;
import com.microsoft.whtch.domain.WatchRepository;
import com.microsoft.whtch.domain.WatchRepository;
import com.microsoft.whtch.service.OrderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DefaultOrderService implements OrderService {

    private final WatchRepository repository;

    public DefaultOrderService(@Qualifier("inMemoryWatchRepository") WatchRepository repository) {
        this.repository = repository;
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

        return order.totalPrice().getAmount();
    }

}