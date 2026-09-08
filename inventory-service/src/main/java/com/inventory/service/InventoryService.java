package com.inventory.service;

import com.inventory.exception.InsufficientInventoryException;
import com.inventory.exception.InventoryNotFoundException;
import com.inventory.dto.InventoryRequest;
import com.inventory.dto.InventoryResponse;
import com.inventory.entity.Inventory;
import com.inventory.mapper.InventoryMapper;
import com.inventory.repository.InventoryRepository;
import com.inventory.util.InventoryConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public InventoryResponse findById(Long id) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new InventoryNotFoundException(InventoryConstants.INVENTORY_NOT_FOUND + id));
        return inventoryMapper.toResponse(inventory);
    }

    public List<InventoryResponse> findAll() {
        List<Inventory> productList = inventoryRepository.findAll();
        return inventoryMapper.toResponseList(productList);
    }

    public InventoryResponse save(InventoryRequest request) {
        Inventory product = inventoryMapper.toEntity(request);
        Inventory savedProduct = inventoryRepository.save(product);
        return inventoryMapper.toResponse(savedProduct);
    }

    public InventoryResponse update(Long id, InventoryRequest request) {
        Inventory product = inventoryRepository.findById(id).orElseThrow(() -> new InventoryNotFoundException(InventoryConstants.INVENTORY_NOT_FOUND + id));
        inventoryMapper.updateEntity(request, product);
        Inventory updatedProduct = inventoryRepository.save(product);
        return inventoryMapper.toResponse(updatedProduct);

    }

    public void delete(Long id) {
        Inventory product = inventoryRepository.findById(id).orElseThrow(() -> new InventoryNotFoundException(InventoryConstants.INVENTORY_NOT_FOUND+ id));
        inventoryRepository.delete(product);
    }


    public InventoryResponse stockIn(Long productId, @NotNull @Positive int quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow(() -> new InventoryNotFoundException(InventoryConstants.PRODUCT_NOT_FOUND +  productId));
        inventory.setQuantity(inventory.getAvailableQuantity() + quantity);
        inventoryRepository.save(inventory);
        return inventoryMapper.toResponse(inventory);
    }

    public InventoryResponse stockOut(Long productId, @NotNull @Positive int quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow(() -> new InventoryNotFoundException(InventoryConstants.PRODUCT_NOT_FOUND + productId));
        if (inventory.getAvailableQuantity() < quantity) {
            throw new InsufficientInventoryException(InventoryConstants.INCUFFICIENT_INVENTORY + productId);
        }
        inventory.setQuantity(inventory.getAvailableQuantity() - quantity);
        inventoryRepository.save(inventory);
        return inventoryMapper.toResponse(inventory);
    }
}
