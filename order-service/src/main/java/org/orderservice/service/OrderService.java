package org.orderservice.service;

import org.orderservice.dto.*;
import org.orderservice.entity.Order;
import org.orderservice.entity.OrderStatus;
import org.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderRepository orderRepository;

    public String placeOrder(OrderRequest orderRequest) {

        // STEP 1: Create order object

        Order order = new Order();

        order.setProductId(orderRequest.getProductId());
        order.setAmount(orderRequest.getAmount());
        order.setCreatedAt(LocalDateTime.now());

        // initially pending
        order.setStatus(OrderStatus.PENDING);

        // save initial order
        orderRepository.save(order);

        // STEP 2: Check inventory

        String inventoryUrl =
                "http://localhost:8081/inventory/"
                        + orderRequest.getProductId();

        InventoryResponse inventoryResponse =
                restTemplate.getForObject(
                        inventoryUrl,
                        InventoryResponse.class
                );

        // inventory failed
        if(inventoryResponse == null ||
                !inventoryResponse.isAvailable()) {

            order.setStatus(OrderStatus.FAILED);

            orderRepository.save(order);

            return new OrderResponse(
        order.getId(),
        "Product Out Of Stock",
        "FAILED",
        LocalDateTime.now()
);
        }

        // STEP 3: Payment

        PaymentRequest paymentRequest =
                new PaymentRequest();

        paymentRequest.setAmount(
                orderRequest.getAmount()
        );

        PaymentResponse paymentResponse =
                restTemplate.postForObject(
                        "http://localhost:8082/payment",
                        paymentRequest,
                        PaymentResponse.class
                );

        // payment failed
        if(paymentResponse == null ||
                !"SUCCESS".equals(
                        paymentResponse.getStatus())) {

            order.setStatus(OrderStatus.FAILED);

            orderRepository.save(order);

            return new OrderResponse(
        order.getId(),
        "Payment Failed",
        "FAILED",
        LocalDateTime.now()
);
        }

        // STEP 4: Final success

        order.setStatus(OrderStatus.SUCCESS);

        orderRepository.save(order);

        return new OrderResponse(
        order.getId(),
        "Order placed successfully",
        "SUCCESS",
        LocalDateTime.now()
);
    }
}