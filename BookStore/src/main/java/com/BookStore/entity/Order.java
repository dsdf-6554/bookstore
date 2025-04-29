package com.BookStore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "订单表") // 映射到数据库中的表名
public class Order {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "订单ID") // 映射到数据库中的字段名
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "用户ID", nullable = false) // 映射到数据库中的字段名
    private User user;

    @ManyToOne
    @JoinColumn(name = "书籍ID", nullable = false) // 映射到数据库中的字段名
    private Book book;

    @Column(name = "数量", nullable = false)
    private Integer quantity;

    @Column(name = "订单时间", nullable = false, updatable = false)
    private java.util.Date orderTime;

    @Column(name = "订单状态", nullable = false, length = 20)
    private String orderStatus;

}