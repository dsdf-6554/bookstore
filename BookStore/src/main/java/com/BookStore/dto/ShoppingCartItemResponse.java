package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShoppingCartItemResponse {
    // Getters and Setters
    private Long shoppingCartId;
    private String bookTitle;
    private Integer quantity;
    private Double bookPrice;

}
