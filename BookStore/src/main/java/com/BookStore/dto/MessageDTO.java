package com.BookStore.dto;

import lombok.Data;
import java.util.Date;

@Data
public class MessageDTO {
    private Long messageId;
    private Long userId;
    private Long senderId;
    private String content;
    private Date sendTime;
}
