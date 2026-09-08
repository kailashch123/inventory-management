package com.inventory.product.service;

import com.inventory.product.dto.ProductRequest;
import com.inventory.product.dto.ProductResponse;
import com.inventory.product.entity.Product;
import com.inventory.product.exception.ProductNotFountException;
import com.inventory.product.mapper.ProductMapper;
import com.inventory.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFountException(id));
        return productMapper.toResponse(product);
    }

    public List<ProductResponse> findAll() {
        List<Product> productList = productRepository.findAll();
        return productMapper.toResponseList(productList);
    }

    public ProductResponse save(ProductRequest request) {
        Product product = productMapper.toEntity(request);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponse(savedProduct);
    }

    public ProductResponse update(Long id, ProductRequest request) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFountException(id));
        productMapper.updateEntity(request, product);
        Product updatedProduct = productRepository.save(product);
        return productMapper.toResponse(updatedProduct);

    }

    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFountException(id));
        productRepository.delete(product);
    }

}
