// InventoryController.java
package com.BookStore.controller;

import com.BookStore.dto.InventoryDto;
import com.BookStore.repository.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public List<InventoryDto> getAll(@RequestParam String permissionLevel) {
        return inventoryService.getAllInventory(permissionLevel);
    }

    @PutMapping("/{inventoryId}")
    public InventoryDto updateStock(@PathVariable Long inventoryId,
                                    @RequestParam Integer quantity,
                                    @RequestParam String permissionLevel) {
        return inventoryService.updateInventory(inventoryId, quantity, permissionLevel);
    }

    @GetMapping("/book/{bookId}")
    public InventoryDto getByBookId(@PathVariable Long bookId,
                                    @RequestParam String permissionLevel) {
        return inventoryService.getInventoryByBookId(bookId, permissionLevel);
    }
    /**
     * 更新库存数量，并在从缺货变为有货时通知相关VIP用户。
     * @param bookId 要更新的书籍ID
     * @param newStock 新库存数量
     */
    @PutMapping("/updateStock")
    public String updateStock(@RequestParam Long bookId, @RequestParam int newStock) {
        inventoryService.updateInventory(bookId, newStock);
        return "库存更新成功";
    }
}
