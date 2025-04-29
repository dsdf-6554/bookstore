package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserUpgradeTimeDTO {

    // Getters and Setters
    private Long userId;
    private String currentLevel;
    private long yearsUntilUpgrade;

    // Constructor
    public UserUpgradeTimeDTO(Long userId, String currentLevel, long yearsUntilUpgrade) {
        this.userId = userId;
        this.currentLevel = currentLevel;
        this.yearsUntilUpgrade = yearsUntilUpgrade;
    }

}
