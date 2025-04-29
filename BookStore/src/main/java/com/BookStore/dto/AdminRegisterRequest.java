package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminRegisterRequest {
    // Getters and Setters
    private String username;
    private String password;
    private String email;
    private String permissionLevel; // 权限等级（如：超级管理员/普通管理员）

}
