package com.inventory.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private String sku;

    private BigDecimal price;

    private Integer quantity;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
