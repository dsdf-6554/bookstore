package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisterRequest {
    // Getters and Setters
    private String phoneNumber;
    private String password;
    private String nickname;
    private String email;
    private String verificationCode;

}
