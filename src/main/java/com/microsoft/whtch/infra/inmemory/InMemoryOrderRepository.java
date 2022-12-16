package com.microsoft.whtch.infra.inmemory;

import com.microsoft.whtch.domain.Order;
import com.microsoft.whtch.domain.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private final Map<Long, Order> orders = new HashMap<>();

    @Override
    public Order findById(Long id) {
        return orders.get(id);
    }

    @Override
    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    @Override
    public Long nextId() {
        return new Random().nextLong();
    }
}
