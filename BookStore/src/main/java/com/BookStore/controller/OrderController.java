package com.BookStore.controller;

import com.BookStore.dto.OrderCheckoutRequest;
import com.BookStore.dto.OrderDto;
import com.BookStore.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    // 用户提交订单
    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestBody OrderCheckoutRequest request) {
        orderService.checkout(request);
        return ResponseEntity.ok("订单提交成功！");
    }
    @GetMapping
    public List<OrderDto> getAllOrders(@RequestParam String permissionLevel) {
        return orderService.getAllOrders(permissionLevel);
    }

    @PutMapping("/{orderId}")
    public OrderDto updateOrderStatus(@PathVariable Long orderId,
                                      @RequestParam String newStatus,
                                      @RequestParam String permissionLevel) {
        return orderService.updateOrderStatus(orderId, newStatus, permissionLevel);
    }
}
