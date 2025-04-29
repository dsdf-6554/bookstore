package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResetPasswordRequest {
    // Getters and Setters
    private String phoneNumber;
    private String newPassword;
    private String verificationCode;

}
