package com.BookStore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "进货单表") // 映射到数据库中的表名
public class PurchaseOrder {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "进货单ID") // 映射到数据库中的字段名
    private Long purchaseOrderId;

    @ManyToOne
    @JoinColumn(name = "书籍ID", nullable = false) // 映射到数据库中的字段名
    private Book book;

    @Column(name = "进货数量", nullable = false)
    private Integer purchaseQuantity;

    @Column(name = "进货时间", nullable = false, updatable = false)
    private java.util.Date purchaseTime;

}