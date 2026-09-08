package com.inventory.product.mapper;

import com.inventory.product.dto.ProductRequest;
import com.inventory.product.dto.ProductResponse;
import com.inventory.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequest productRequest);

    ProductResponse toResponse(Product product);

    List<ProductResponse> toResponseList(List<Product> products);

    void updateEntity(ProductRequest productRequest, @MappingTarget Product product);

}
