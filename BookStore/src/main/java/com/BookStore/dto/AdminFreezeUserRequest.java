package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class AdminFreezeUserRequest {
    private Long adminId;
    private Long userId;

    // Getters and Setters
}
