package com.BookStore.repository;


import com.BookStore.entity.Book;
import com.BookStore.entity.ShoppingCart;
import com.BookStore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByUser(User user);
    List<ShoppingCart> findByBook(Book book);
    // 根据购物车ID和用户查询
    Optional<ShoppingCart> findByShoppingCartIdAndUser(Long shoppingCartId, User user);
    // 删除指定用户所有购物车记录
    void deleteByUser(User user);
}