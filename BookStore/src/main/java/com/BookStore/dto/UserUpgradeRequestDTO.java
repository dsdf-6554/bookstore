package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserUpgradeRequestDTO {

    // Getters and Setters
    private Long userId;
    private double amount;

    // Constructor
    public UserUpgradeRequestDTO(Long userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }

}
