package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminReplyFeedbackRequest {
    private Long feedbackId;
    private Long adminId;
    private String replyContent;

    // Getters and Setters

}
