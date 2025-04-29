package com.BookStore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "库存表") // 映射到数据库中的表名
public class Inventory {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "库存ID") // 映射到数据库中的字段名
    private Long inventoryId;

    @ManyToOne
    @JoinColumn(name = "书籍ID", nullable = false) // 映射到数据库中的字段名
    private Book book;

    @Column(name = "库存数量", nullable = false)
    private Integer stockQuantity;

    @Column(name = "最后更新时间", nullable = false, updatable = false)
    private java.util.Date lastUpdateTime;

}