package com.inventory.product.controller;

import com.inventory.product.dto.ProductRequest;
import com.inventory.product.dto.ProductResponse;
import com.inventory.product.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping
    public ProductResponse save(@RequestBody ProductRequest request) {
        return productService.save(request);
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id, @RequestBody ProductRequest request) {
        log.info("");
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.delete(id);
    }

}
