package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginRequest {
    // Getters and Setters
    private String phoneNumber;
    private String password;

}
