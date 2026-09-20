package com.inventory.order.client;

import com.inventory.order.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class ProductClient {

    private final RestClient productRestClient;


    public ProductResponse getProduct(Long productId) {
        return productRestClient
                .get()
                .uri("/products/{id}", productId)
                .retrieve()
                .body(ProductResponse.class);
    }
}
