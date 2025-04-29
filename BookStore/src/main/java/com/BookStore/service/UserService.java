package com.BookStore.service;

import com.BookStore.dto.UserUpgradeRequestDTO;
import com.BookStore.dto.UserUpgradeTimeDTO;
import com.BookStore.entity.User;
import com.BookStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    // 注册用户
    public User registerUser(String phoneNumber, String password, String nickname, String email, String verificationCode) {
        // 验证手机号格式
        if (!isPhoneNumberValid(phoneNumber)) {
            throw new RuntimeException("手机号格式不正确！");
        }

        // 检查手机号是否已经被注册
        if (userRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            throw new RuntimeException("手机号已经被注册！");
        }

        // 检查邮箱是否已经被绑定
        if (email != null && userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("邮箱已经被绑定！");
        }

        // 验证验证码
        String key = "VERIFICATION_CODE:" + phoneNumber;
        String cachedVerificationCode = redisTemplate.opsForValue().get(key);

        if (cachedVerificationCode == null || !cachedVerificationCode.equals(verificationCode)) {
            throw new RuntimeException("验证码无效或已过期，请重新获取！");
        }

        // 验证码验证通过，创建用户对象
        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setPassword(passwordEncoder.encode(password));
        user.setNickname(nickname);
        if (email != null && !email.isEmpty()) {
            user.setEmail(email);
        }
        user.setRegistrationTime(new Date());
        user.setPermissionLevel("普通用户");

        // 保存用户信息
        return userRepository.save(user);
    }

    // 发送验证码
    public void sendVerificationCode(String phoneNumber) {
        // 检查手机号是否已经被注册
        String key = "USER_PHONE_NUMBER:" + phoneNumber;
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            throw new RuntimeException("请勿频繁请求验证码，请稍后再试！");
        }
        // 生成验证码
        String verificationCode = VerificationCodeUtil.generateCaptcha(6);

        // 存储验证码到 Redis，有效期 1 分钟
        redisTemplate.opsForValue().set(key, verificationCode, 1, TimeUnit.MINUTES);

        // 模拟发送验证码
        System.out.println("已向手机号 " + phoneNumber + " 发送验证码：" + verificationCode);
    }
    // 校验手机号格式
    private boolean isPhoneNumberValid(String phoneNumber) {
        String regex = "^(\\+?\\d{1,3})?\\d{10}$"; // 根据需求调整正则表达式
        return phoneNumber.matches(regex);
    }

    // 用户登录
    public User loginUser(String phoneNumber, String password) {
        // 通过手机号查找用户
        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 验证密码
            if (passwordEncoder.matches(password, user.getPassword())) {
                user.setLastLoginTime(new Date()); // 更新最后登录时间
                user.setUserStatus("已登录");
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
    // 用户登出
    public void logoutUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("用户未找到"));
        user.setUserStatus("未登录"); // 登出时更新用户状态为未登录
        userRepository.save(user);
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
    public void bindPhoneNumber(Long userId, String newPhoneNumber, String verificationCode) {
        // 通过用户ID查找用户
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // 验证手机号格式
        if (!isPhoneNumberValid(newPhoneNumber)) {
            throw new RuntimeException("手机号格式不正确！");
        }

        // 检查新手机号是否已存在
        Optional<User> userOptional = userRepository.findByPhoneNumber(newPhoneNumber);
        if (userOptional.isPresent()) {
            throw new RuntimeException("手机号已经被绑定！");
        }

        // 验证验证码
        String key = "VERIFICATION_CODE:" + newPhoneNumber;
        String cachedVerificationCode = redisTemplate.opsForValue().get(key);
        if (cachedVerificationCode == null || !cachedVerificationCode.equals(verificationCode)) {
            throw new RuntimeException("验证码无效或已过期，请重新获取！");
        }

        // 更新手机号
        user.setPhoneNumber(newPhoneNumber);
        userRepository.save(user);
    }
    // 找回密码
    public void recoverPassword(String phoneNumber, String verificationCode, String newPassword) {
        // 验证手机号格式
        if (!isPhoneNumberValid(phoneNumber)) {
            throw new RuntimeException("手机号格式不正确！");
        }

        // 检查手机号是否已经注册
        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("手机号未注册！");
        }

        // 校验验证码
        String key = "VERIFICATION_CODE:" + phoneNumber;
        String cachedVerificationCode = redisTemplate.opsForValue().get(key);
        if (cachedVerificationCode == null || !cachedVerificationCode.equals(verificationCode)) {
            throw new RuntimeException("验证码无效或已过期，请重新获取！");
        }

        // 更新密码
        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));  // 使用密码加密
        userRepository.save(user);

        System.out.println("密码已成功重置！");
    }

    // 获取用户信息
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }
    // 根据用户注册时间计算剩余时间
    public UserUpgradeTimeDTO calculateTimeToUpgrade(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("用户未找到"));
        Date registrationTime = user.getRegistrationTime();
        Date currentTime = new Date();

        long diffInMillis = currentTime.getTime() - registrationTime.getTime();
        long yearsPassed = TimeUnit.MILLISECONDS.toDays(diffInMillis) / 365;

        // 根据用户当前等级和注册时间判断是否可以升级
        String currentLevel = user.getPermissionLevel();
        long yearsUntilUpgrade = 0;

        if ("普通用户".equals(currentLevel)) {
            if (yearsPassed < 1) {
                yearsUntilUpgrade = 1 - yearsPassed;
            } else {
                user.setPermissionLevel("中级会员"); // 升级为中级会员
                yearsUntilUpgrade = 2 - (yearsPassed - 1);
            }
        } else if ("中级会员".equals(currentLevel)) {
            if (yearsPassed < 3) {
                yearsUntilUpgrade = 3 - yearsPassed;
            } else {
                user.setPermissionLevel("高级会员"); // 升级为高级会员
            }
        }

        userRepository.save(user); // 更新用户信息

        return new UserUpgradeTimeDTO(userId, currentLevel, yearsUntilUpgrade);
    }

    // 用户充值并升级
    public String upgradeUserWithPayment(UserUpgradeRequestDTO requestDTO) {
        Long userId = requestDTO.getUserId();
        double amount = requestDTO.getAmount();

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("用户未找到"));

        if (amount >= 100) { // 充值100元以上可升为中级会员
            user.setPermissionLevel("中级会员");
        }
        if (amount >= 200) { // 充值200元以上可升为高级会员
            user.setPermissionLevel("高级会员");
        }

        userRepository.save(user); // 更新用户信息
        return "升级成功，当前会员等级：" + user.getPermissionLevel();
    }
    // 删除用户
    public void deactivateAccount(Long userId) {
        // 查找用户
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        // 将用户状态更新为已注销
        userRepository.deleteById(userId);

        userRepository.save(user);
    }

}