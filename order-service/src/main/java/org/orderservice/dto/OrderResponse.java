package org.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderResponse {

    private Long orderId;

    private String message;

    private String status;

    private LocalDateTime timestamp;
}