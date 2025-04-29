package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TransactionRecordResponse {
    // Getters and Setters
    private Long transactionRecordId;
    private Double transactionAmount;
    private Date transactionTime;
    private String transactionType;

}
