package com.inventory.controller;

import com.inventory.dto.InventoryRequest;
import com.inventory.dto.InventoryResponse;
import com.inventory.dto.StockRequest;
import com.inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/inventories")
@AllArgsConstructor
public class InventoryController {

    private final InventoryService  inventoryService;

    @GetMapping
    public List<InventoryResponse> findAll() {
        return inventoryService.findAll();
    }

    @GetMapping("/{id}")
    public InventoryResponse findById(@PathVariable Long id) {
        return inventoryService.findById(id);
    }

    @PostMapping
    public InventoryResponse save(@RequestBody InventoryRequest request) {
        return inventoryService.save(request);
    }

    @PutMapping("/{id}")
    public InventoryResponse update(@PathVariable Long id, @RequestBody InventoryRequest request) {
        log.info("");
        return inventoryService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        inventoryService.delete(id);
    }

    @PostMapping("/{productId}/stock/in")
    public ResponseEntity<InventoryResponse> stockIn(@PathVariable Long productId, @RequestBody StockRequest stockRequest) {
        InventoryResponse response = inventoryService.stockIn(productId, stockRequest.getQuantity());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{productId}/stock/out")
    public ResponseEntity<InventoryResponse> stockOut(@PathVariable Long productId, @RequestBody StockRequest stockRequest) {
        InventoryResponse response = inventoryService.stockOut(productId, stockRequest.getQuantity());
        return ResponseEntity.ok(response);
    }
}
