package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResetUserPasswordRequest {
    private Long adminId; // 操作人（管理员ID）
    private Long userId;  // 要重置的普通用户ID

    // Getters and Setters
}
