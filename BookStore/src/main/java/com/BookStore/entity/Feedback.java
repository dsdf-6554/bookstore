package com.BookStore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "反馈表") // 映射到数据库中的表名
public class Feedback {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "反馈ID") // 映射到数据库中的字段名
    private Long feedbackId;

    @ManyToOne
    @JoinColumn(name = "用户ID", nullable = false) // 映射到数据库中的字段名
    private User user;

    @Column(name = "反馈内容", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String feedbackContent;

    @Column(name = "反馈时间", nullable = false, updatable = false)
    private java.util.Date feedbackTime;

}