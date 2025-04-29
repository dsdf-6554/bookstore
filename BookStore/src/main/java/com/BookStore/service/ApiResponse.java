package com.BookStore.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;

    // 默认响应状态（可以通过构造函数调用）
    public ApiResponse(String message, T data) {
        this.code = 200;  // 默认成功状态
        this.message = message;
        this.data = data;
    }

    // 成功响应
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(200);
        response.setMessage("成功");
        response.setData(data);
        return response;
    }

    // 失败响应
    public static <T> ApiResponse<T> failure(String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(400);
        response.setMessage(message);
        response.setData(null);
        return response;
    }
}


