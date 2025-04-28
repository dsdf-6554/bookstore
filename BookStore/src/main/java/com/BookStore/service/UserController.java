package com.BookStore.service;

import com.BookStore.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

//    // 用户注册
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestParam String phoneNumber,
//                                          @RequestParam String password,
//                                          @RequestParam String nickname,
//                                          @RequestParam String email) {
//        try {
//            User user = userService.registerUser(phoneNumber, password, nickname, email);
//            ApiResponse response = new ApiResponse("User registered successfully", user);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            ApiResponse response = new ApiResponse(e.getMessage(), null);
//            return ResponseEntity.badRequest().body(response);
//        }
//    }

    // 用户登录
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.loginUser(loginRequest.getPhoneNumber(), loginRequest.getPassword());
            ApiResponse response = new ApiResponse("User logged in successfully", user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 修改用户密码
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestParam Long userId,
                                            @RequestParam String oldPassword,
                                            @RequestParam String newPassword) {
        try {
            userService.changePassword(userId, oldPassword, newPassword);
            ApiResponse response = new ApiResponse("Password changed successfully", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 绑定手机号
    @PostMapping("/bind-phone")
    public ResponseEntity<?> bindPhoneNumber(@RequestParam Long userId, @RequestParam String newPhoneNumber) {
        try {
            userService.bindPhoneNumber(userId, newPhoneNumber);
            ApiResponse response = new ApiResponse("Phone number bound successfully", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 获取用户信息
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            ApiResponse response = new ApiResponse("User retrieved successfully", user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 删除用户
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            ApiResponse response = new ApiResponse("User deleted successfully", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }
}