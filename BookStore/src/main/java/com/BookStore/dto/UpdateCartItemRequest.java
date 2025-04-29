package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateCartItemRequest {
    // Getters 和 Setters
    private Long shoppingCartId;
    private Integer quantity;

}
