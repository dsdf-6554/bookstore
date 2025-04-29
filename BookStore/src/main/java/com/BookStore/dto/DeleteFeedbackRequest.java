package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteFeedbackRequest {
    private Long feedbackId;
    private Long userId;

    // Getters and Setters

}
