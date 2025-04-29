// InventoryServiceImpl.java
package com.BookStore.service;

import com.BookStore.dto.InventoryDto;
import com.BookStore.entity.*;
import com.BookStore.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<InventoryDto> getAllInventory(String permissionLevel) {
        checkPermission(permissionLevel);
        return inventoryRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public InventoryDto updateInventory(Long inventoryId, Integer newQuantity, String permissionLevel) {
        checkPermission(permissionLevel);
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("库存不存在"));
        inventory.setStockQuantity(newQuantity);
        inventory.setLastUpdateTime(new Date());
        return convertToDto(inventoryRepository.save(inventory));
    }

    @Override
    public InventoryDto getInventoryByBookId(Long bookId, String permissionLevel) {
        checkPermission(permissionLevel);
        Inventory inventory = inventoryRepository.findByBookBookId(bookId)
                .orElseThrow(() -> new RuntimeException("未找到该书籍库存"));
        return convertToDto(inventory);
    }

    private void checkPermission(String permissionLevel) {
        if (!"仓库管理员".equals(permissionLevel)) {
            throw new SecurityException("无权限访问库存管理功能");
        }
    }

    private InventoryDto convertToDto(Inventory inventory) {
        InventoryDto dto = new InventoryDto();
        dto.setInventoryId(inventory.getInventoryId());
        dto.setBookId(inventory.getBook().getBookId());
        dto.setBookTitle(inventory.getBook().getTitle());
        dto.setStockQuantity(inventory.getStockQuantity());
        dto.setLastUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(inventory.getLastUpdateTime()));
        return dto;
    }
    @Override
    public void updateInventory(Long bookId, int newStock) {
        Inventory inventory = inventoryRepository.findByBookBookId(bookId)
                .orElseThrow(() -> new RuntimeException("未找到库存信息"));

        boolean wasOutOfStock = inventory.getStockQuantity() == 0;
        inventory.setStockQuantity(newStock);
        inventory.setLastUpdateTime(new Date());

        inventoryRepository.save(inventory);

        if (wasOutOfStock && newStock > 0) {
            notifyVipUsersAboutRestock(bookId);
        }
    }

    private void notifyVipUsersAboutRestock(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("书籍不存在"));

        List<ShoppingCart> carts = shoppingCartRepository.findByBook(book);
        Set<Long> notifiedUserIds = new HashSet<>();

        for (ShoppingCart cart : carts) {
            User user = cart.getUser();
            if ("高级用户".equalsIgnoreCase(user.getPermissionLevel()) && !notifiedUserIds.contains(user.getUserId())) {
                Message message = new Message();
                message.setUser(user);
                message.setSender(user); // 你可以在这里换成系统用户
                message.setContent("您好，您购物车中的书籍《" + book.getTitle() + "》已经补货，欢迎购买！");
                message.setSendTime(new Date());

                messageRepository.save(message);
                notifiedUserIds.add(user.getUserId());
            }
        }
    }

}
