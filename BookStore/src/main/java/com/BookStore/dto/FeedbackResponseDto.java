package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class FeedbackResponseDto {
    // Getters and Setters
    private Long feedbackId;
    private Long userId;
    private String feedbackContent;
    private Date feedbackTime;

}
