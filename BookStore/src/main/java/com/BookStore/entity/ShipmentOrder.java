package com.BookStore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "出货单表") // 映射到数据库中的表名
public class ShipmentOrder {
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

    // Getters and Setters
    public Long getShipmentOrderId() {
        return shipmentOrderId;
    }

    public void setShipmentOrderId(Long shipmentOrderId) {
        this.shipmentOrderId = shipmentOrderId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getShipmentQuantity() {
        return shipmentQuantity;
    }

    public void setShipmentQuantity(Integer shipmentQuantity) {
        this.shipmentQuantity = shipmentQuantity;
    }

    public java.util.Date getShipmentTime() {
        return shipmentTime;
    }

    public void setShipmentTime(java.util.Date shipmentTime) {
        this.shipmentTime = shipmentTime;
    }
}