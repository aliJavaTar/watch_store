package com.microsoft.whtch.domain;

public interface OrderRepository {
    Order findById(Long id);

    void save(Order order);

    Long nextId();
}
