package org.inventoryservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Integer productId;

    private String productName;

    private int quantity;
}