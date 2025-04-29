package com.BookStore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "消息表") // 映射到数据库中的表名
public class Message {
    // Getters and Setters
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

}