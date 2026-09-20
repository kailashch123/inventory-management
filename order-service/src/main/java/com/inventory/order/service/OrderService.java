package com.inventory.order.service;

import com.inventory.order.client.ProductClient;
import com.inventory.order.dto.OrderRequest;
import com.inventory.order.dto.OrderResponse;
import com.inventory.order.dto.ProductResponse;
import com.inventory.order.entity.Order;
import com.inventory.order.entity.OrderItem;
import com.inventory.order.enums.OrderStatus;
import com.inventory.order.exception.OrderNotFoundException;
import com.inventory.order.mapper.OrderMapper;
import com.inventory.order.repository.OrderRepository;
import com.inventory.order.util.OrderConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductClient productClient;

    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toOrderResponse).toList();
    }

    public OrderResponse findOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(OrderConstant.ORDER_NOT_FOUND_FOR_ID + id));
        return orderMapper.toOrderResponse(order);
    }

    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = orderMapper.toOrder(orderRequest);
        BigDecimal orderTotal = BigDecimal.ZERO;
        for (OrderItem orderItem : order.getOrderItems()) {
            ProductResponse product = productClient.getProduct(orderItem.getProductId());
            BigDecimal unitPrice = product.getPrice();
            BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(orderItem.getQuantity()));
            orderItem.setUnitPrice(unitPrice);
            orderItem.setTotalPrice(totalPrice);
            orderItem.setOrder(order);
            orderTotal = orderTotal.add(totalPrice);
        }
        order.setTotalAmount(orderTotal);
        order.setStatus(OrderStatus.CREATED);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toOrderResponse(savedOrder);
    }

    public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(OrderConstant.ORDER_NOT_FOUND + id));
        orderMapper.updateEntity(orderRequest, order);
        BigDecimal orderTotal = order.getOrderItems().stream().map(item -> {
           ProductResponse product = productClient.getProduct(item.getProductId());
           BigDecimal unitPrice = product.getPrice();
           BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(item.getQuantity()));
           item.setUnitPrice(unitPrice);
           item.setTotalPrice(totalPrice);
           item.setOrder(order);
           return totalPrice;
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(orderTotal);
        Order updatedOrder = orderRepository.save(order);

        return orderMapper.toOrderResponse(updatedOrder);
    }

    public void deleteOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(OrderConstant.ORDER_NOT_FOUND_FOR_ID + id));
        orderRepository.delete(order);
    }

}
