package com.BookStore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "反馈表") // 映射到数据库中的表名
public class Feedback {
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

    // Getters and Setters
    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public java.util.Date getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(java.util.Date feedbackTime) {
        this.feedbackTime = feedbackTime;
    }
}