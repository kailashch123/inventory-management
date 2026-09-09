package com.inventory.order.mapper;

import com.inventory.order.dto.OrderItemRequest;
import com.inventory.order.dto.OrderItemResponse;
import com.inventory.order.dto.OrderRequest;
import com.inventory.order.dto.OrderResponse;
import com.inventory.order.entity.Order;
import com.inventory.order.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderItem toOrderItem(OrderItemRequest request);

    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    Order toOrder(OrderRequest request);

    OrderResponse toOrderResponse(Order order);

    void updateEnity(OrderRequest orderRequest, @MappingTarget Order order);

}
