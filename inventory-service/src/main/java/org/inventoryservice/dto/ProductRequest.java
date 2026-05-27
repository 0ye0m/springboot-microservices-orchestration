package org.inventoryservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @Positive(message = "Product ID must be positive")
    private Integer productId;

    @NotBlank(message = "Product name is required")
    private String productName;

    @Positive(message = "Quantity must be positive")
    private int quantity;
}