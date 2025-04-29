// InventoryService.java
package com.BookStore.repository;

import com.BookStore.dto.InventoryDto;

import java.util.List;

public interface InventoryService {
    List<InventoryDto> getAllInventory(String permissionLevel);
    InventoryDto updateInventory(Long inventoryId, Integer newQuantity, String permissionLevel);
    InventoryDto getInventoryByBookId(Long bookId, String permissionLevel);
    void updateInventory(Long bookId, int newStock);
}
