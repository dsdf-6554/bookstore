package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminUnfreezeUserRequest {
    private Long adminId;
    private Long userId;

    // Getters and Setters
}
