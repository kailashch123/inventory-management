package com.inventory.product.exception;

public class ProductNotFountException extends RuntimeException {

    public ProductNotFountException(Long id) {
        super("Product not found with id " + id);
    }
}
