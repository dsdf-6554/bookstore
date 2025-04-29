package com.BookStore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddToCartRequest {
    private Long bookId;
    private Integer quantity;

}
