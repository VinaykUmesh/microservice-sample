package com.example.order.service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private Long id;
    private Long productId;
    private Integer quantity;
    private Double totalPrice;
    private String productName;
    private Double productPrice;
}
