package org.orderservice.controller;

import jakarta.validation.Valid;
import org.orderservice.dto.OrderRequest;
import org.orderservice.dto.OrderResponse;
import org.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public OrderResponse placeOrder(
            @Valid @RequestBody OrderRequest request) {

        return orderService.placeOrder(request);
    }
}