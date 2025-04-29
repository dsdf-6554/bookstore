// OrderDto.java
package com.BookStore.dto;

import lombok.Data;

@Data
public class OrderDto {
    private Long orderId;
    private Long userId;
    private String orderStatus;
    private String orderTime;
}
