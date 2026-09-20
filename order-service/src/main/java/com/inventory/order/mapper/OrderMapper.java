package com.inventory.order.mapper;

import com.inventory.order.dto.OrderItemRequest;
import com.inventory.order.dto.OrderItemResponse;
import com.inventory.order.dto.OrderRequest;
import com.inventory.order.dto.OrderResponse;
import com.inventory.order.entity.Order;
import com.inventory.order.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderItem toOrderItem(OrderItemRequest request);

    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    @Mapping(source = "items", target = "orderItems")
    Order toOrder(OrderRequest request);

    @Mapping(source = "status", target = "orderStatus")
    @Mapping(source = "orderItems", target = "items")
    OrderResponse toOrderResponse(Order order);

    void updateEntity(OrderRequest orderRequest, @MappingTarget Order order);
}
