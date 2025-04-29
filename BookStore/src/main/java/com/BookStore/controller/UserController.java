package com.BookStore.controller;

import com.BookStore.entity.User;
import com.BookStore.dto.*;
import com.BookStore.service.ApiResponse;
import com.BookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // 用户注册
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        // 调用用户服务进行注册
        User registeredUser = userService.registerUser(
                userRegisterRequest.getPhoneNumber(),
                userRegisterRequest.getPassword(),
                userRegisterRequest.getNickname(),
                userRegisterRequest.getEmail(),
                userRegisterRequest.getVerificationCode()
        );
        return ResponseEntity.ok(registeredUser);
    }

    // 用户登录
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest loginRequest) {
        User loggedInUser = userService.loginUser(loginRequest.getPhoneNumber(), loginRequest.getPassword());
        return ResponseEntity.ok(loggedInUser);
    }
    //用户登出
    @PostMapping("/logout")
    public void logout(@RequestParam Long userId) {
        userService.logoutUser(userId);
    }
// 用户重置密码
    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        userService.recoverPassword(
                resetPasswordRequest.getPhoneNumber(),
                resetPasswordRequest.getNewPassword(),
                resetPasswordRequest.getVerificationCode()
        );
        return ResponseEntity.ok("密码已重置");
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
    // 获取用户距离升级的时间
    @GetMapping("/upgradeTime/{userId}")
    public UserUpgradeTimeDTO getUpgradeTime(@PathVariable Long userId) {
        return userService.calculateTimeToUpgrade(userId);
    }

    // 用户充值并升级
    @PostMapping("/upgrade")
    public String upgradeUser(@RequestBody UserUpgradeRequestDTO requestDTO) {
        return userService.upgradeUserWithPayment(requestDTO);
    }

    // 用户换绑手机号
    @PutMapping("/{userId}/bind-phone")
    public ResponseEntity<String> bindPhoneNumber(@PathVariable Long userId,
                                                  @RequestBody BindPhoneNumberRequest bindPhoneNumberRequest) {
        try {
            // 调用服务层的换绑手机号方法
            userService.bindPhoneNumber(userId, bindPhoneNumberRequest.getNewPhoneNumber(),
                    bindPhoneNumberRequest.getVerificationCode());
            return ResponseEntity.ok("手机号绑定成功！");
        } catch (RuntimeException e) {
            // 处理错误
            return ResponseEntity.badRequest().body("错误: " + e.getMessage());
        }
    }
    // 发送验证码接口
    @PostMapping("/{userId}/send-verification-code")
    public ResponseEntity<String> sendVerificationCode(@PathVariable Long userId,
                                                       @RequestParam String phoneNumber) {
        try {
            // 调用服务层发送验证码
            userService.sendVerificationCode(phoneNumber);
            return ResponseEntity.ok("验证码已发送！");
        } catch (RuntimeException e) {
            // 处理错误
            return ResponseEntity.badRequest().body("错误: " + e.getMessage());
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

    // 注销账号接口
    @DeleteMapping("/deactivate/{userId}")
    public String deactivateAccount(@PathVariable Long userId) {
        try {
            userService.deactivateAccount(userId);
            return "账号已成功注销";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}