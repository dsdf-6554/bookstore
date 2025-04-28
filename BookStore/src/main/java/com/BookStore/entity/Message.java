package com.BookStore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "消息表") // 映射到数据库中的表名
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "消息ID") // 映射到数据库中的字段名
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "用户ID", nullable = false) // 映射到数据库中的字段名
    private User user;

    @Column(name = "消息内容", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String content;

    @Column(name = "发送时间", nullable = false, updatable = false)
    private java.util.Date sendTime;

    @ManyToOne
    @JoinColumn(name = "发送者ID", nullable = false) // 映射到数据库中的字段名
    private User sender;

    // Getters and Setters
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public java.util.Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(java.util.Date sendTime) {
        this.sendTime = sendTime;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}