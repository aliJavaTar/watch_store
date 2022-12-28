package com.microsoft.whtch.application.impl;


import com.microsoft.whtch.application.OrderService;
import com.microsoft.whtch.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DefaultOrderService implements OrderService {

    private final WatchRepository watchRepository;

    private final OrderRepository orderRepository;
    private final OrderPriceCalculator calculator;

    public DefaultOrderService(
            WatchRepository watchRepository,
            OrderRepository orderRepository,
            OrderPriceCalculator calculator) {
        this.watchRepository = watchRepository;
        this.orderRepository = orderRepository;
        this.calculator = calculator;
    }

    public Long getOrderTotalPrice(List<Long> ids) {
        Map<Long, Integer> orderCountPerItem = new HashMap<>();

        ids.forEach(id -> {
            int count = Collections.frequency(ids, id);
            orderCountPerItem.put(id, count);
        });

        Order order = Order.place(orderRepository.nextId());

        orderCountPerItem.forEach((id, count) -> {
            Watch watch = watchRepository.findById(id);
            order.addOrderLine(watch, count);
        });

        // We can call totalPrice from entity directly
//        Money totalPrice = order.totalPrice().getAmount();
//        OrderPriceCalculator calculator = new PureOrderPriceCalculator();

        // or we can use a domain service (pure or impure)
        Money totalPrice = calculator.calculateTotalPrice(order);

        return totalPrice.getAmount();
    }

    public void placeOrder(List<Long> ids) {
        Map<Long, Integer> orderCountPerItem = new HashMap<>();

        ids.forEach(id -> {
            int count = Collections.frequency(ids, id);
            orderCountPerItem.put(id, count);
        });

        Order order = Order.place(orderRepository.nextId());

        orderCountPerItem.forEach((id, count) -> {
            Watch watch = watchRepository.findById(id);
            order.addOrderLine(watch, count);
        });

        orderRepository.save(order);

//        try {
            // transactionManger->beginTransaction();
            // orderRepository.save(order);
            // transactionManger->commit();
//        } catch (Exception e) {
            // transactionManger->rollback()
//        }

    }

    @Override
    public void accept(String id) {
        Order order = orderRepository.findById(Long.valueOf(id));
        order.accept();
        orderRepository.save(order);
    }

}