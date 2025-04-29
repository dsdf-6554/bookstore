package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminUpdatePasswordRequest {
    private Long adminId;   // 管理员ID
    private String oldPassword;
    private String newPassword;

    // Getters and Setters
}
