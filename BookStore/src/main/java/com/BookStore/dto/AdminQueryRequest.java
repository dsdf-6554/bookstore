package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminQueryRequest {
    private String username;           // 用户名（可选）
    private String permissionLevel;    // 权限等级（可选）
    private Integer pageNumber = 0;    // 页码（默认第0页）
    private Integer pageSize = 10;     // 每页条数（默认10条）

    // Getters and Setters
}
