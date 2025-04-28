package com.BookStore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "日志表") // 映射到数据库中的表名
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "日志ID") // 映射到数据库中的字段名
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "用户ID", nullable = false) // 映射到数据库中的字段名
    private User user;

    @Column(name = "操作类型", nullable = false, length = 50)
    private String operationType;

    @Column(name = "操作时间", nullable = false, updatable = false)
    private java.util.Date operationTime;

    @Column(name = "操作详情", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String operationDetails;

    // Getters and Setters
    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public java.util.Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(java.util.Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperationDetails() {
        return operationDetails;
    }

    public void setOperationDetails(String operationDetails) {
        this.operationDetails = operationDetails;
    }
}