package com.BookStore.controller;

import com.BookStore.dto.*;
import com.BookStore.entity.Admin;
import com.BookStore.service.AdminServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("/register")
    public String registerAdmin(@RequestBody AdminRegisterRequest request) {
        adminService.registerAdmin(request);
        return "管理员注册成功";
    }
    @PostMapping("/login")
    public String login(@RequestBody AdminLoginRequest request) {
        Admin admin = adminService.login(request);
        return "登录成功，欢迎管理员：" + admin.getUsername();
    }

    @PostMapping("/recover")
    public String recoverAccount(@RequestBody AdminRecoveryRequest request) {
        return adminService.recoverAccount(request);
    }
    @PostMapping("/updatePassword")
    public String updatePassword(@RequestBody AdminUpdatePasswordRequest request) {
        adminService.updatePassword(request);
        return "密码更新成功";
    }

    @PostMapping("/updateInfo")
    public String updateInfo(@RequestBody AdminUpdateInfoRequest request) {
        adminService.updateInfo(request);
        return "管理员信息更新成功";
    }

    @PostMapping("/deleteAccount")
    public String deleteAccount(@RequestBody AdminDeleteAccountRequest request) {
        adminService.deleteAccount(request);
        return "管理员账号已注销";
    }
    @PostMapping("/query")
    public Page<Admin> queryAdmins(@RequestBody AdminQueryRequest request) {
        return adminService.queryAdmins(request);
    }
    @PostMapping("/resetUserPassword")
    public String resetUserPassword(@RequestBody AdminResetUserPasswordRequest request) {
        adminService.resetUserPassword(request);
        return "密码已重置并发送给用户";
    }

    @PostMapping("/freezeUser")
    public String freezeUserAccount(@RequestBody AdminFreezeUserRequest request) {
        adminService.freezeUserAccount(request);
        return "用户账号已冻结";
    }

    @PostMapping("/unfreezeUser")
    public String unfreezeUserAccount(@RequestBody AdminUnfreezeUserRequest request) {
        adminService.unfreezeUserAccount(request);
        return "用户账号已解冻";
    }

}