package org.orderservice.dto;

import lombok.Data;

@Data
public class OrderRequest {

    private int productId;
    private double amount;
}