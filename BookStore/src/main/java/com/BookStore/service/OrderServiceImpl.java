package com.BookStore.service;

import com.BookStore.dto.OrderCheckoutRequest;
import com.BookStore.dto.OrderDto;
import com.BookStore.entity.*;
import com.BookStore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TransactionRecordRepository transactionRecordRepository;

    @Autowired
    private ThirdPartyAccountRepository thirdPartyAccountRepository;

    @Transactional
    public void checkout(OrderCheckoutRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("用户不存在！"));

        List<ShoppingCart> shoppingCartItems = shoppingCartRepository.findByUser(user);

        if (shoppingCartItems.isEmpty()) {
            throw new RuntimeException("购物车为空！");
        }

        double totalAmount = 0.0;

        // 遍历购物车生成订单
        for (ShoppingCart item : shoppingCartItems) {
            Order order = new Order();
            order.setUser(user);
            order.setBook(item.getBook());
            order.setQuantity(item.getQuantity());
            order.setOrderTime(new Date());
            order.setOrderStatus("待支付");
            orderRepository.save(order);

            // 计算总金额
            totalAmount += item.getBook().getPrice() * item.getQuantity();
        }

        // 根据支付方式处理
        if ("线下支付".equals(request.getPaymentMethod())) {
            // 线下支付，直接更新订单状态
            List<Order> orders = orderRepository.findAll();
            for (Order order : orders) {
                if (order.getUser().getUserId().equals(user.getUserId()) && "待支付".equals(order.getOrderStatus())) {
                    order.setOrderStatus("已支付");
                    orderRepository.save(order);
                }
            }
        } else if ("第三方支付".equals(request.getPaymentMethod())) {
            // 第三方支付
            ThirdPartyAccount account = thirdPartyAccountRepository.findByUserAndThirdPartyType(user, request.getThirdPartyType())
                    .orElseThrow(() -> new RuntimeException("第三方账号未绑定！"));

            // 假设跳转到第三方支付并成功
            TransactionRecord transaction = new TransactionRecord();
            transaction.setUser(user);
            transaction.setTransactionAmount(totalAmount);
            transaction.setTransactionTime(new Date());
            transaction.setTransactionType(request.getThirdPartyType());
            transactionRecordRepository.save(transaction);

            // 更新订单状态
            List<Order> orders = orderRepository.findAll();
            for (Order order : orders) {
                if (order.getUser().getUserId().equals(user.getUserId()) && "待支付".equals(order.getOrderStatus())) {
                    order.setOrderStatus("已支付");
                    orderRepository.save(order);
                }
            }
        } else {
            throw new RuntimeException("支付方式不正确！");
        }

        // 清空购物车
        shoppingCartRepository.deleteByUser(user);
    }
    @Override
    public List<OrderDto> getAllOrders(String permissionLevel) {
        checkPermission(permissionLevel);
        return orderRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDto updateOrderStatus(Long orderId, String newStatus, String permissionLevel) {
        checkPermission(permissionLevel);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("订单不存在"));
        order.setOrderStatus(newStatus);
        return convertToDto(orderRepository.save(order));
    }

    private void checkPermission(String permissionLevel) {
        if (!"书城管理员".equals(permissionLevel)) {
            throw new SecurityException("无权限操作订单管理功能");
        }
    }

    private OrderDto convertToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setOrderId(order.getOrderId());
        dto.setUserId(order.getUser().getUserId());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setOrderTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getOrderTime()));
        return dto;
    }
}
