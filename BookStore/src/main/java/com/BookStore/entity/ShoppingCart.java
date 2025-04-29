package com.BookStore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "购物车表") // 映射到数据库中的表名
public class ShoppingCart {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "购物车ID") // 映射到数据库中的字段名
    private Long shoppingCartId;

    @ManyToOne
    @JoinColumn(name = "用户ID", nullable = false) // 映射到数据库中的字段名
    private User user;

    @ManyToOne
    @JoinColumn(name = "书籍ID", nullable = false) // 映射到数据库中的字段名
    private Book book;

    @Column(name = "数量", nullable = false)
    private Integer quantity;

}