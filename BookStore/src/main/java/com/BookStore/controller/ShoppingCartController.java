package com.BookStore.controller;

import com.BookStore.dto.AddToCartRequest;
import com.BookStore.dto.ShoppingCartItemResponse;
import com.BookStore.dto.UpdateCartItemRequest;
import com.BookStore.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    // 用户添加书籍到购物车
    @PostMapping("/user/{userId}/add")
    public ResponseEntity<String> addToCart(@PathVariable Long userId, @RequestBody AddToCartRequest request) {
        shoppingCartService.addToCart(userId, request);
        return ResponseEntity.ok("添加到购物车成功！");
    }

    // 用户查看自己的购物车
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ShoppingCartItemResponse>> getUserCart(@PathVariable Long userId) {
        List<ShoppingCartItemResponse> cartItems = shoppingCartService.getUserCart(userId);
        return ResponseEntity.ok(cartItems);
    }
    // 用户删除购物车中的一项
    @DeleteMapping("/user/{userId}/delete/{shoppingCartId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable Long userId, @PathVariable Long shoppingCartId) {
        shoppingCartService.deleteCartItem(userId, shoppingCartId);
        return ResponseEntity.ok("删除购物车项成功！");
    }
    // 用户修改购物车中的数量
    @PutMapping("/user/{userId}/update")
    public ResponseEntity<String> updateCartItemQuantity(
            @PathVariable Long userId,
            @RequestBody UpdateCartItemRequest request) {
        shoppingCartService.updateCartItemQuantity(userId, request.getShoppingCartId(), request.getQuantity());
        return ResponseEntity.ok("购物车数量更新成功！");
    }
    // 用户清空购物车
    @DeleteMapping("/user/{userId}/clear")
    public ResponseEntity<String> clearShoppingCart(@PathVariable Long userId) {
        shoppingCartService.clearShoppingCart(userId);
        return ResponseEntity.ok("购物车已清空！");
    }

}
