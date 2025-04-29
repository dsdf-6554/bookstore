package com.BookStore.service;

import com.BookStore.dto.*;
import com.BookStore.entity.Admin;
import com.BookStore.entity.User;
import com.BookStore.repository.AdminRepository;
import com.BookStore.repository.AdminService;
import com.BookStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void registerAdmin(AdminRegisterRequest request) {
        // 检查用户名是否存在
        adminRepository.findByUsername(request.getUsername())
                .ifPresent(a -> { throw new RuntimeException("用户名已存在"); });

        // 检查邮箱是否存在
        if (request.getEmail() != null) {
            adminRepository.findByEmail(request.getEmail())
                    .ifPresent(a -> { throw new RuntimeException("邮箱已存在"); });
        }

        // 创建并保存管理员
        Admin admin = new Admin();
        admin.setUsername(request.getUsername());
        admin.setPassword(request.getPassword()); // 正式开发要加密处理！！
        admin.setEmail(request.getEmail());
        admin.setRegistrationTime(new Date());
        admin.setPermissionLevel(request.getPermissionLevel());

        adminRepository.save(admin);
    }

    @Override
    public Admin login(AdminLoginRequest request) {
        Admin admin = adminRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("用户名不存在"));

        // 简化版密码校验
        if (!admin.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        return admin;
    }

    @Override
    public String recoverAccount(AdminRecoveryRequest request) {
        Admin admin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("未找到绑定该邮箱的管理员"));

        return "找回成功，用户名为：" + admin.getUsername();
    }
    @Override
    public void updatePassword(AdminUpdatePasswordRequest request) {
        Admin admin = adminRepository.findById(request.getAdminId())
                .orElseThrow(() -> new RuntimeException("管理员不存在"));

        if (!admin.getPassword().equals(request.getOldPassword())) {
            throw new RuntimeException("旧密码错误");
        }

        admin.setPassword(request.getNewPassword());
        adminRepository.save(admin);
    }

    @Override
    public void updateInfo(AdminUpdateInfoRequest request) {
        Admin admin = adminRepository.findById(request.getAdminId())
                .orElseThrow(() -> new RuntimeException("管理员不存在"));

        if (request.getEmail() != null) {
            admin.setEmail(request.getEmail());
        }
        if (request.getPermissionLevel() != null) {
            admin.setPermissionLevel(request.getPermissionLevel());
        }

        adminRepository.save(admin);
    }

    @Override
    public void deleteAccount(AdminDeleteAccountRequest request) {
        Admin admin = adminRepository.findById(request.getAdminId())
                .orElseThrow(() -> new RuntimeException("管理员不存在"));

        adminRepository.delete(admin); // 物理删除
        // 如果想逻辑删除，可以加一个状态字段 isDeleted
    }
    @Override
    public Page<Admin> queryAdmins(AdminQueryRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by("adminId").ascending());

        // 构建 Example 动态查询
        Admin exampleAdmin = new Admin();
        if (request.getUsername() != null && !request.getUsername().isEmpty()) {
            exampleAdmin.setUsername(request.getUsername());
        }
        if (request.getPermissionLevel() != null && !request.getPermissionLevel().isEmpty()) {
            exampleAdmin.setPermissionLevel(request.getPermissionLevel());
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())   // 包含匹配
                .withMatcher("permissionLevel", ExampleMatcher.GenericPropertyMatchers.exact()) // 完全匹配
                .withIgnorePaths("adminId", "password", "email", "registrationTime"); // 忽略不参与查询的字段

        Example<Admin> example = Example.of(exampleAdmin, matcher);

        return adminRepository.findAll(example, pageable);
    }
    @Autowired
    private UserRepository userRepository;

    // 重置普通用户密码
    @Override
    public void resetUserPassword(AdminResetUserPasswordRequest request) {
        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("用户不存在");
        }

        User user = optionalUser.get();
        String newPassword = generateRandomPassword();
        user.setPassword(newPassword); // 设置新密码
        userRepository.save(user);

        // TODO: 调用短信服务发送新密码给用户手机号
         sendSms(user.getPhoneNumber(), "您的新密码是：" + newPassword);
    }

    // 冻结用户账号
    @Override
    public void freezeUserAccount(AdminFreezeUserRequest request) {
        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("用户不存在");
        }

        User user = optionalUser.get();
        user.setAccountStatus("冻结");
        userRepository.save(user);
    }

    // 解冻用户账号
    @Override
    public void unfreezeUserAccount(AdminUnfreezeUserRequest request) {
        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("用户不存在");
        }

        User user = optionalUser.get();
        user.setAccountStatus("正常");
        userRepository.save(user);
    }

    // 生成随机密码
    private String generateRandomPassword() {
        return "pwd" + (int)(Math.random() * 10000);  // 简单示例
    }
    private void sendSms(String phoneNumber, String message) {
        // TODO: 短信平台接入
        System.out.println("短信发送到：" + phoneNumber + "，内容：" + message);
    }

}
