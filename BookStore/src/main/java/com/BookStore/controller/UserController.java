package com.BookStore.controller;

import com.BookStore.entity.User;
import com.BookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

//    @GetMapping("/{phoneNumber}")
//    public User getUserByPhoneNumber(@PathVariable String phoneNumber) {
//        return userService.getUserByPhoneNumber(phoneNumber).orElse(null);
//    }
}