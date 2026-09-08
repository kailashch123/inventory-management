package com.inventory.mapper;

import com.inventory.dto.InventoryRequest;
import com.inventory.dto.InventoryResponse;
import com.inventory.entity.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    Inventory toEntity(InventoryRequest request);

    @Mapping(
            target = "availableQuantity",
            expression = "java(getAvailableQuantity())"
    )
    InventoryResponse toResponse(Inventory inventory);

    List<InventoryResponse> toResponseList(List<Inventory> productList);

    void updateEntity(InventoryRequest productRequest, @MappingTarget Inventory product);
}
