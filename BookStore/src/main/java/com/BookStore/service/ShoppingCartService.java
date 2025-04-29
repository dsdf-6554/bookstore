package com.BookStore.service;

import com.BookStore.dto.AddToCartRequest;
import com.BookStore.dto.ShoppingCartItemResponse;
import com.BookStore.entity.Book;
import com.BookStore.entity.ShoppingCart;
import com.BookStore.entity.User;
import com.BookStore.repository.BookRepository;
import com.BookStore.repository.ShoppingCartRepository;
import com.BookStore.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository,
                               UserRepository userRepository,
                               BookRepository bookRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void addToCart(Long userId, AddToCartRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在！"));
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("书籍不存在！"));

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setBook(book);
        shoppingCart.setQuantity(request.getQuantity());

        shoppingCartRepository.save(shoppingCart);
    }

    public List<ShoppingCartItemResponse> getUserCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在！"));

        List<ShoppingCart> cartList = shoppingCartRepository.findByUser(user);

        return cartList.stream().map(cart -> {
            ShoppingCartItemResponse response = new ShoppingCartItemResponse();
            response.setShoppingCartId(cart.getShoppingCartId());
            response.setBookTitle(cart.getBook().getTitle());
            response.setQuantity(cart.getQuantity());
            response.setBookPrice(cart.getBook().getPrice());
            return response;
        }).collect(Collectors.toList());
    }
    @Transactional
    public void deleteCartItem(Long userId, Long shoppingCartId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在！"));

        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findByShoppingCartIdAndUser(shoppingCartId, user);
        if (optionalCart.isEmpty()) {
            throw new RuntimeException("购物车记录不存在或不属于该用户！");
        }

        shoppingCartRepository.delete(optionalCart.get());
    }
    //更新数量
    @Transactional
    public void updateCartItemQuantity(Long userId, Long shoppingCartId, Integer newQuantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在！"));

        ShoppingCart cartItem = shoppingCartRepository.findByShoppingCartIdAndUser(shoppingCartId, user)
                .orElseThrow(() -> new RuntimeException("购物车记录不存在或不属于该用户！"));

        if (newQuantity <= 0) {
            throw new RuntimeException("数量必须大于0！");
        }

        cartItem.setQuantity(newQuantity);
        shoppingCartRepository.save(cartItem);
    }
    //清空购物车
    @Transactional
    public void clearShoppingCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在！"));

        shoppingCartRepository.deleteByUser(user);
    }

}
