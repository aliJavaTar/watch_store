package com.microsoft.whtch.presentation.backoffice;

import com.microsoft.whtch.application.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backoffice/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("{id}/accept")
    public ResponseEntity<Long> acceptOrder(@PathVariable String id) {
        orderService.accept(id);
        return ResponseEntity.ok().build();
    }

}
