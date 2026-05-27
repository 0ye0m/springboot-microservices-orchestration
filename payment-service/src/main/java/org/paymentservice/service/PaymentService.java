package org.paymentservice.service;

import org.paymentservice.dto.*;
import org.paymentservice.entity.Payment;
import org.paymentservice.entity.PaymentStatus;
import org.paymentservice.exception.PaymentNotFoundException;
import org.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Make Payment
    public PaymentResponse makePayment(
            PaymentRequest request) {

        Payment payment = new Payment();

        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(
                request.getPaymentMethod());

        payment.setTransactionTime(
                LocalDateTime.now());

        // dummy success logic
        if(request.getAmount() > 0) {
            payment.setStatus(
                    PaymentStatus.SUCCESS);
        }
        else {
            payment.setStatus(
                    PaymentStatus.FAILED);
        }

        Payment savedPayment =
                paymentRepository.save(payment);

        return new PaymentResponse(
                savedPayment.getPaymentId(),
                savedPayment.getOrderId(),
                savedPayment.getAmount(),
                savedPayment.getPaymentMethod(),
                savedPayment.getStatus().name(),
                savedPayment.getTransactionTime(),
                "Payment processed successfully"
        );
    }

    // Get Payment By ID
    public PaymentResponse getPayment(
            Long paymentId) {

        Payment payment =
                paymentRepository.findById(paymentId)
                        .orElseThrow(() ->
                                new PaymentNotFoundException(
                                        "Payment not found"
                                ));

        return new PaymentResponse(
                payment.getPaymentId(),
                payment.getOrderId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getStatus().name(),
                payment.getTransactionTime(),
                "Payment fetched successfully"
        );
    }

    // Get All Payments
    public List<Payment> getAllPayments() {

        return paymentRepository.findAll();
    }

    // Delete Payment
    public String deletePayment(Long paymentId) {

        Payment payment =
                paymentRepository.findById(paymentId)
                        .orElseThrow(() ->
                                new PaymentNotFoundException(
                                        "Payment not found"
                                ));

        paymentRepository.delete(payment);

        return "Payment deleted successfully";
    }
}