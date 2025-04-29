package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TransactionRecordQueryRequest {
    // Getters and Setters
    private Long userId;
    private String transactionType;
    private Date startTime;
    private Date endTime;

}
