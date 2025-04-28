package com.BookStore.service;

import com.BookStore.entity.User;
import com.BookStore.repository.UserRepository;
import jakarta.persistence.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

   // @Autowired
   // private RedisTemplate<String, String> redisTemplate;


//    // 注册用户
//    public User registerUser(String phoneNumber, String password, String nickname, String email, String verificationCode) {
//        // 检查手机号是否已经被注册
//        if (userRepository.findByPhoneNumber(phoneNumber).isPresent()) {
//            throw new RuntimeException("手机号已经被注册！");
//        }
//        // 检查邮箱是否已经被绑定
//        if (email != null && userRepository.findByEmail(email).isPresent()) {
//            throw new RuntimeException("邮箱已经被绑定！");
//        }
//
//        // 验证验证码
//        String cachedVerificationCode = redisTemplate.opsForValue().get(phoneNumber);
//        if (cachedVerificationCode == null || !cachedVerificationCode.equals(verificationCode)) {
//            throw new RuntimeException("验证码无效或已过期，请重新获取！");
//        }
//
//        // 验证码验证通过，创建用户对象
//        User user = new User();
//        user.setPhoneNumber(phoneNumber);
//        user.setPassword(passwordEncoder.encode(password));
//        user.setNickname(nickname);
//        if (email != null && !email.isEmpty()) {
//            user.setEmail(email);
//        }
//        user.setRegistrationTime(new Date());
//        user.setPermissionLevel("普通用户");
//
//        // 保存用户信息
//        return userRepository.save(user);
//    }

//    // 发送验证码
//    public void sendVerificationCode(String phoneNumber) {
//        // 生成验证码
//        String verificationCode = VerificationCodeUtil.generateCaptcha(6);
//
//        // 存储验证码到 Redis，有效期 1 分钟
//        redisTemplate.opsForValue().set(phoneNumber, verificationCode, 1, TimeUnit.MINUTES);
//
//        // 模拟发送验证码
//        System.out.println("已向手机号 " + phoneNumber + " 发送验证码：" + verificationCode);
//    }

    // 用户登录
    public User loginUser(String phoneNumber, String password) {
        // 通过手机号查找用户
        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 验证密码
            if (passwordEncoder.matches(password, user.getPassword())) {
                user.setLastLoginTime(new Date()); // 更新最后登录时间
                userRepository.save(user); // 保存更新后的用户信息
                return user;
            } else {
                // 如果密码错误，抛出异常
                throw new RuntimeException("密码错误！");
            }
        } else {
            // 如果用户不存在，抛出异常
            throw new RuntimeException("手机号未被注册！");
        }
    }

    // 修改用户密码
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        // 通过用户ID查找用户
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        // 验证旧密码
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }
        // 设置新密码并保存
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    // 绑定手机号
    public void bindPhoneNumber(Long userId, String newPhoneNumber) {
        // 通过用户ID查找用户
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        // 检查新手机号是否已存在
        if (userRepository.findByPhoneNumber(newPhoneNumber).isPresent()) {
            throw new RuntimeException("Phone number already exists");
        }
        // 更新手机号
        user.setPhoneNumber(newPhoneNumber);
        userRepository.save(user);
    }

    // 获取用户信息
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // 获取所有用户信息
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 删除用户
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}