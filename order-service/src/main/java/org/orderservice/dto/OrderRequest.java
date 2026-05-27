package org.orderservice.dto;

import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @Positive(message = "Product ID must be positive")
    private int productId;

    @Positive(message = "Amount must be positive")
    private double amount;
}