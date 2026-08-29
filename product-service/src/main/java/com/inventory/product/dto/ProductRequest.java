package com.inventory.product.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequest {

    private String name;

    private String description;

    private String sku;

    private BigDecimal price;

    private Integer quantity;
}
