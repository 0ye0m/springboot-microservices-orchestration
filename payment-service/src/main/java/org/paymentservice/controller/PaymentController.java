package org.paymentservice.controller;

import jakarta.validation.Valid;
import org.paymentservice.dto.PaymentRequest;
import org.paymentservice.dto.PaymentResponse;
import org.paymentservice.entity.Payment;
import org.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Make Payment
    @PostMapping
    public PaymentResponse makePayment(
            @Valid @RequestBody PaymentRequest request) {

        return paymentService.makePayment(request);
    }

    // Get Payment By ID
    @GetMapping("/{paymentId}")
    public PaymentResponse getPayment(
            @PathVariable Long paymentId) {

        return paymentService.getPayment(paymentId);
    }

    // Get All Payments
    @GetMapping
    public List<Payment> getAllPayments() {

        return paymentService.getAllPayments();
    }

    // Delete Payment
    @DeleteMapping("/{paymentId}")
    public String deletePayment(
            @PathVariable Long paymentId) {

        return paymentService.deletePayment(paymentId);
    }
}