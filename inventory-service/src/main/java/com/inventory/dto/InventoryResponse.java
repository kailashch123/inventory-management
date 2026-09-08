package com.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse {

    private Long id;

    private Long productId;

    private Integer quantity;

    private Integer reservedQuanity;

    private Integer availableQuantity;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
