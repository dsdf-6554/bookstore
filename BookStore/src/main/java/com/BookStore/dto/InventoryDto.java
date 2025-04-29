// InventoryDto.java
package com.BookStore.dto;

import lombok.Data;

@Data
public class InventoryDto {
    private Long inventoryId;
    private Long bookId;
    private String bookTitle;
    private Integer stockQuantity;
    private String lastUpdateTime;
}
