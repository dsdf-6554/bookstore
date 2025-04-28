package com.BookStore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "进货单表") // 映射到数据库中的表名
public class PurchaseOrder {
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

    // Getters and Setters
    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(Integer purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public java.util.Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(java.util.Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }
}