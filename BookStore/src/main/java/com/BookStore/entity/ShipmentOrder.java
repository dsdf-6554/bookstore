package com.BookStore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "出货单表") // 映射到数据库中的表名
public class ShipmentOrder {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "出货单ID") // 映射到数据库中的字段名
    private Long shipmentOrderId;

    @ManyToOne
    @JoinColumn(name = "书籍ID", nullable = false) // 映射到数据库中的字段名
    private Book book;

    @Column(name = "出货数量", nullable = false)
    private Integer shipmentQuantity;

    @Column(name = "出货时间", nullable = false, updatable = false)
    private java.util.Date shipmentTime;

}