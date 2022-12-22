package com.microsoft.whtch.presentation.front;

import com.microsoft.whtch.application.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderFrontController {

    private final OrderService orderService;

    public OrderFrontController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("")
    public ResponseEntity<Long> buyWatch(@RequestBody List<Long> ids) {
        return new ResponseEntity<>(orderService.getOrderTotalPrice(ids), HttpStatus.OK);
    }

}
