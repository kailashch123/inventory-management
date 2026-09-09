package com.inventory.order.service;

import com.inventory.order.dto.OrderRequest;
import com.inventory.order.dto.OrderResponse;
import com.inventory.order.entity.Order;
import com.inventory.order.exception.OrderNotFoundException;
import com.inventory.order.mapper.OrderMapper;
import com.inventory.order.repository.OrderItemRepository;
import com.inventory.order.repository.OrderRepository;
import com.inventory.order.util.OrderConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toOrderResponse).toList();
    }

    public OrderResponse findOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(OrderConstant.ORDER_NOT_FOUND_FOR_ID + id));
        return orderMapper.toOrderResponse(order);
    }

    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = orderMapper.toOrder(orderRequest);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toOrderResponse(savedOrder);
    }

    public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(OrderConstant.ORDER_NOT_FOUND + id));
        orderMapper.updateEnity(orderRequest, order);
        order.getOrderItems().forEach(item -> item.setOrder(order));
        Order updatedOrder = orderRepository.save(order);
        return orderMapper.toOrderResponse(updatedOrder);
    }

    public void deleteOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(OrderConstant.ORDER_NOT_FOUND_FOR_ID + id));
        orderRepository.delete(order);
    }

}
