package com.BookStore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "交易记录表") // 映射到数据库中的表名
public class TransactionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "交易记录ID") // 映射到数据库中的字段名
    private Long transactionRecordId;

    @ManyToOne
    @JoinColumn(name = "用户ID", nullable = false) // 映射到数据库中的字段名
    private User user;

    @Column(name = "交易金额", nullable = false, precision = 10, scale = 2)
    private Double transactionAmount;

    @Column(name = "交易时间", nullable = false, updatable = false)
    private java.util.Date transactionTime;

    @Column(name = "交易类型", nullable = false, length = 20)
    private String transactionType;

    // Getters and Setters
    public Long getTransactionRecordId() {
        return transactionRecordId;
    }

    public void setTransactionRecordId(Long transactionRecordId) {
        this.transactionRecordId = transactionRecordId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public java.util.Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(java.util.Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}