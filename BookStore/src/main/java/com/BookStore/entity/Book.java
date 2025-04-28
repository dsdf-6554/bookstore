package com.BookStore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "书籍表") // 映射到数据库中的表名
public class Book {
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

    @Column(name = "价格", nullable = false, precision = 10, scale = 2)
    private Double price;

    @Column(name = "库存", nullable = false)
    private Integer stock;

    @Column(name = "状态", nullable = false, length = 20)
    private String status;

    @Column(name = "类别", nullable = false, length = 50)
    private String category;

    // Getters and Setters
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}