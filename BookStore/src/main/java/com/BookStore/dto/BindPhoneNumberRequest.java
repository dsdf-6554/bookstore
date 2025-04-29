package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BindPhoneNumberRequest {
    // Getters and Setters
    private String newPhoneNumber;
    private String verificationCode;

}
