package org.orderservice.controller;


import org.orderservice.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public String placeOrder(@RequestBody OrderRequest orderRequest) {

        // STEP 1: Check Inventory

        String inventoryUrl =
                "http://localhost:8081/inventory/" +
                        orderRequest.getProductId();

        InventoryResponse inventoryResponse =
                restTemplate.getForObject(
                        inventoryUrl,
                        InventoryResponse.class
                );

        if(inventoryResponse == null ||
                !inventoryResponse.isAvailable()) {

            return "Product Out Of Stock";
        }

        // STEP 2: Payment

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(orderRequest.getAmount());

        PaymentResponse paymentResponse =
                restTemplate.postForObject(
                        "http://localhost:8082/payment",
                        paymentRequest,
                        PaymentResponse.class
                );

        if(paymentResponse == null ||
                !"SUCCESS".equals(paymentResponse.getStatus())) {

            return "Payment Failed";
        }

        // STEP 3: Confirm Order

        return "Order Placed Successfully";
    }
}