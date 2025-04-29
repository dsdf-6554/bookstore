// OrderServiceImpl.java
package com.BookStore.repository;

import com.BookStore.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders(String permissionLevel);
    OrderDto updateOrderStatus(Long orderId, String newStatus, String permissionLevel);
}
