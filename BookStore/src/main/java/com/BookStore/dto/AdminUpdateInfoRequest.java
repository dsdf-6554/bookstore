package com.BookStore.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUpdateInfoRequest {
    private Long adminId;
    private String email;
    private String permissionLevel;

    // Getters and Setters
}
