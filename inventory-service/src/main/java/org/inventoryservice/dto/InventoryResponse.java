package org.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InventoryResponse {

    private Integer productId;

    private String productName;

    private boolean available;

    private int quantity;
}