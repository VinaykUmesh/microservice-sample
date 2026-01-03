package com.example.order.service.controller;

import com.example.order.service.dto.OrderResponseDto;
import com.example.order.service.dto.ProductDto;
import com.example.order.service.model.Order;
import com.example.order.service.service.OrderService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final WebClient.Builder webclientBuilder;

    @PostMapping("/placeOrders")
    public Mono<@NonNull ResponseEntity<@NonNull OrderResponseDto>> placeOrder(@RequestBody Order order) {
        return webclientBuilder.build().get()
                .uri("http://PRODUCT-SERVICE/api/products/" + order.getProductId())
                .retrieve()
                .bodyToMono(ProductDto.class).map(productDto -> {
                    Order saved = orderService.placeOrder(order);
                    return ResponseEntity.ok().body(OrderResponseDto.builder()
                            .id(saved.getId())
                            .productId(saved.getProductId())
                            .quantity(saved.getQuantity())
                            .productName(productDto.getProductName())
                            .productPrice(productDto.getProductPrice())
                            .totalPrice(saved.getQuantity() * productDto.getProductPrice())
                            .build());
                });
    }

    @GetMapping
    public ResponseEntity<@NonNull List<Order>> getAll() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }
}
