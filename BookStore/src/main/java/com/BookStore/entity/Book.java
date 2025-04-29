package com.BookStore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "书籍表") // 映射到数据库中的表名
public class Book {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "书籍ID") // 映射到数据库中的字段名
    private Long bookId;

    @Column(name = "书名", nullable = false, length = 100)
    private String title;

    @Column(name = "作者", nullable = false, length = 100)
    private String author;

    @Column(name = "出版社", nullable = false, length = 100)
    private String publisher;

    @Column(name = "价格", nullable = false, precision = 10)
    private Double price;

    @Column(name = "库存", nullable = false)
    private Integer stock;

    @Column(name = "状态", nullable = false, length = 20)
    private String status;

    @Column(name = "类别", nullable = false, length = 50)
    private String category;

    @Column(name = "简介", nullable = false, length = 500)
    private String description;

}