package com.BookStore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "交易记录表") // 映射到数据库中的表名
public class TransactionRecord {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "交易记录ID") // 映射到数据库中的字段名
    private Long transactionRecordId;

    @ManyToOne
    @JoinColumn(name = "用户ID", nullable = false) // 映射到数据库中的字段名
    private User user;

    @Column(name = "交易金额", nullable = false, precision = 10)
    private Double transactionAmount;

    @Column(name = "交易时间", nullable = false, updatable = false)
    private java.util.Date transactionTime;

    @Column(name = "交易类型", nullable = false, length = 20)
    private String transactionType;

}