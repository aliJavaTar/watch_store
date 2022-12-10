package com.microsoft.whtch.presentation;

import com.microsoft.whtch.application.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WatchController {

    private final OrderService orderService;

    public WatchController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<Long> buyWatch(@RequestBody List<Long> ids) {
        return new ResponseEntity<>(orderService.getOrderTotalPrice(ids), HttpStatus.OK);
    }
}
