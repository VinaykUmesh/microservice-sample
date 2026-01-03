package com.example.product.service.controller;

import com.example.product.service.dto.ProductDto;
import com.example.product.service.model.Product;
import com.example.product.service.service.ProductService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<@NonNull ProductDto> addProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
    }

    @GetMapping
    public ResponseEntity<@NonNull List<ProductDto>> getAllProduct() {
        return ResponseEntity.ok(productService.all());
    }

    @GetMapping("/{id}")
    public ResponseEntity<@NonNull ProductDto> getProductById(@PathVariable("id") Long productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

}
