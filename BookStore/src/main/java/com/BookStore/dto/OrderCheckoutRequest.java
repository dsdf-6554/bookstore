package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderCheckoutRequest {
    // Getters and Setters
    private Long userId;
    private String paymentMethod; // 线下支付 or 第三方支付
    private String thirdPartyType; // 如果是第三方支付，需要指定类型（如支付宝、微信等）

}
