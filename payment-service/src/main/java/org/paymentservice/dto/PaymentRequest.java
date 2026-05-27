package org.paymentservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    @Positive(message = "Order ID must be positive")
    private Long orderId;

    @Positive(message = "Amount must be positive")
    private double amount;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;
}