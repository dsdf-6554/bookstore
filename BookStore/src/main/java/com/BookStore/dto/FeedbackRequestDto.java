package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeedbackRequestDto {
    // Getters and Setters
    private Long userId;
    private String feedbackContent;

}
